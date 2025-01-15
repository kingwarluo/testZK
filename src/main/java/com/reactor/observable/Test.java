package com.reactor.observable;

public class Test {

    // 手写RxJava
//    1、关系确立
//    2、观察者上溯 -- subscribe过程
//    3、事件通知  --  onNext过程
    public static void main(String[] args) {
        new Observable<String>() {
            @Override
            public void subscribe(Observer observer) {
                System.out.println("1" + Thread.currentThread());
                observer.onNext("Observable");
            }
        }.flatMap().map(s -> {
            System.out.println("2" + Thread.currentThread());
            return s + "1";
        }).subscribeOn().observeOn().subscribe(new Observer() {
            @Override
            public void onNext(Object value) {
                System.out.println("3" + Thread.currentThread());
                System.out.println("observer的onNext收到数据：" + value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

}
