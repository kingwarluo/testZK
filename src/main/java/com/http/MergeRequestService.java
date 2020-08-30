package com.http;

import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 合并请求案例
 *
 * @author jianhua.luo
 * @date 2020/8/29
 */
@Service
public class MergeRequestService {

    @Data
    class Request {

        /**
         * 查询的id
         */
        private Long id;

        /**
         * 返回结果
         */
        private CompletableFuture<User> future;
    }

    // 积攒的请求。（每隔N毫秒批量处理一次）
    LinkedBlockingQueue<Request> queue = new LinkedBlockingQueue<>();

    // 定时任务的实现,N秒钟处理一次数据
    @PostConstruct
    public void init() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            // 1、 取出queue的请求，生成一次批量查询
            int size = queue.size();
            if (size == 0) {
                return;
            }
            List<Request> requests = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Request request = queue.poll();
                requests.add(request);
            }
            System.out.println("批量处理数据量:" + size);
            // 2、 组装一个批量查询（一定需要 目的资源能够支持批量查询。 http）
            List<Long> ids = new ArrayList<>();
            for (Request request : requests) {
                ids.add(request.getId());
            }
            if(CollectionUtils.isEmpty(ids)) {
                return;
            }
            // RPC调用或者service调用
            List<User> responses = QueryServiceRemoteCall.queryUserByIdBatch(ids);

            // 3、将结果响应 分发给每一个单独的用户请求。  由定时任务处理线程 --> 1000个用户的请求线程
            // [
            // {"code":"500",star: tony}
            // {"code":"600",star: tony}
            // ]
            Map<Long, User> responseMap = new HashMap<>();
            for (User user : responses) {
                responseMap.put(user.getId(), user);
            }
            for (Request request : requests) {
                // 根据请求中携带的能表示唯一参数，去批量查询的结果中找响应
                User result = responseMap.get(request.getId());
                // 将结果返回到对应的请求线程
                request.future.complete(result);
            }
        }, 0, 10, TimeUnit.MILLISECONDS);
        System.out.println("init完成");
    }

    // 1000 用户请求，1000个线程
    public User queryUserList(Long userId) throws ExecutionException, InterruptedException {
        // 1000次 怎么样才能变成  更少的接口
        // 思路： 将不同用户的同类请求合并起来
        // 并非立刻发起接口调用，请求 收集起来，再进行
        Request request = new Request();
        request.setId(userId);
        // 异步编程： 获取异步处理的结果
        CompletableFuture<User> future = new CompletableFuture<>();
        request.setFuture(future);
        queue.add(request);
        return future.get(); // 此处get方法，会阻塞线程运行，直到future有返回
        // 什么时候返回结果？ 批量查询之后。 怎么进行等待
        // return queryServiceRemoteCall.queryMovieInfoByCode(movieCode);
    }

}
