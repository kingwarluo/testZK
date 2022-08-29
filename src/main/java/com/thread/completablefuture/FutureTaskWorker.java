package com.thread.completablefuture;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 异步执行worker
 * @author kingwarluo
 * @date 2022/8/29 10:32
 */
@AllArgsConstructor
public class FutureTaskWorker<T, R> {

    /**
     * 需要异步处理的任务
     */
    private List<T> taskList;

    /**
     * 需要执行的方法
     */
    private Function<T, CompletableFuture<R>> workFunction;

    /**
     * 搜集执行结果
     * @return
     */
    public List<R> getAllResult() {
        List<CompletableFuture<R>> futureList = taskList.stream().map(workFunction).collect(Collectors.toList());
        CompletableFuture<Void> allOfFuture = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()]));
        return allOfFuture.thenApply(e -> futureList.stream().map(CompletableFuture::join).collect(Collectors.toList())).join();
    }
}
