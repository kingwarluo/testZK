package com.thread.completablefuture;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author kingwarluo
 * @{description}
 * @date 2022/8/25 20:38
 */
public class CompletableFutureDemo {

    public static void main(String[] args) {
        complete();
    }

    public static void complete() {
        List<String> result = new CopyOnWriteArrayList<>();

        List<CompletableFuture> futureList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                System.out.println("1");
                result.add("1");
            });
            futureList.add(future);
        }
        CompletableFuture<Void> completableFuture = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()]));
        completableFuture.join();
    }

}
