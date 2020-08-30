package com.http;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * 合并请求测试类，模拟controller
 *
 * @author jianhua.luo
 * @date 2020/8/29
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:frameworkContext.xml"})
public class MergeRequestServiceTest {

    private static final int THREAD_NUM = 1000;

    private CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUM);

    @Autowired
    private MergeRequestService mergeRequestService;

    @Test
    public void userController(){
        // 创建 并不是马上发起请求
        for (int i = 0; i < THREAD_NUM; i++) {
            final int code = i + 1; // 序号
            // 多线程模拟用户查询请求
            Thread thread = new Thread(() -> {
                try {
                    // 代码在这里等待，等待countDownLatch为0，代表所有线程都start，再运行后续的代码
                    countDownLatch.await();
                    // http请求，实际上就是多线程调用这个方法
                    User result = mergeRequestService.queryUserList(Long.valueOf(code));
                    System.out.println(Thread.currentThread().getName() + " 查询结束，结果是：" + result);
                } catch (Exception e) {
                    System.out.println(Thread.currentThread().getName() + " 线程执行出现异常:" + e.getMessage());
                }
            });
            thread.setName("price-thread-" + code);
            thread.start();
            // 田径。启动后，倒计时器倒计数 减一，代表又有一个线程就绪了
            countDownLatch.countDown();
        }
    }

}
