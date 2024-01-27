package com.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Flux demo
 * 参考：https://blog.csdn.net/mowushenght/article/details/123535385
 * @author kingwarluo
 * @date 2024/1/27 10:40
 */
public class FluxDemo {

    public static void main(String[] args) {
        // subscribe 才触发数据流动
        Flux.range(1, 3).subscribe(System.out::println);

//        for (int i = 0; i < 2; i++) {
//            Flux.range(i * 10, 2)
//                    .doOnNext(n -> System.out.println(n.toString()))
//                    .subscribeOn(Schedulers.single())
//                    .map(n -> n + "s")
//                    .subscribe(System.out::println);
//        }

        // 注释：
        // publishOn会影响其后的操作符的运行线程，即filter是在single线程运行、flatMap是在boundedElastic线程运行
        // subscribeOn影响源头流的运行线程，即map实在parallel线程运行
//        Flux.range(1, 3)
//                .map(i -> {
//                    System.out.println(i.toString());
//                    return i + 1;
//                })
//                .publishOn(Schedulers.single())
//                .filter(i -> {
//                    System.out.println(i.toString());
//                    return i > 0;
//                })
//                .publishOn(Schedulers.boundedElastic())
//                .flatMap(i -> {
//                    System.out.println(i.toString());
//                    return Flux.just(i * 10);
//                })
//                .subscribeOn(Schedulers.parallel())
//                .subscribe(i -> System.out.println(i.toString()));

        // onErrorReturn和onErrorResume

    }

}
