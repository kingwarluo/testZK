package com.reactor.observable;

public abstract class Observable<T> {

    public static <T> Observable<T> create(Observable<T> observable) {
        return observable;
    }

    public abstract void subscribe(Observer<T> observer);

    public Observable<T> flatMap() {
        return new Observable<T>() {
            @Override
            public void subscribe(Observer<T> observer) {
                Observer observerB = new Observer<T>() {

                    @Override
                    public void onNext(T value) {
                        System.out.println("flatmap call");
                        observer.onNext(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        observer.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        observer.onComplete();
                    }
                };
                Observable.this.subscribe(observerB);
            }
        };
    }

    public <R> Observable<R> map(Func<T, R> func) {
        return new Observable<R>() {
            @Override
            public void subscribe(Observer<R> observer) {
                Observer observerB = new Observer<T>() {

                    @Override
                    public void onNext(T t) {
                        System.out.println("map call");
                        R r = func.call(t);
                        observer.onNext(r);
                    }

                    @Override
                    public void onError(Throwable e) {
                        observer.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        observer.onComplete();
                    }
                };
                Observable.this.subscribe(observerB);
            }
        };
    }

    // 发生在观察者上溯
    public Observable<T> subscribeOn() {
        return new Observable<T>() {
            @Override
            public void subscribe(Observer<T> observer) {
                new Thread(() -> {
                    Observer observerB = new Observer<T>() {

                        @Override
                        public void onNext(T t) {
                            observer.onNext(t);
                        }

                        @Override
                        public void onError(Throwable e) {
                            observer.onError(e);
                        }

                        @Override
                        public void onComplete() {
                            observer.onComplete();
                        }
                    };
                    Observable.this.subscribe(observerB);
                }).start();
            }
        };
    }

    // 发生在事件通知
    public Observable<T> observeOn() {
        return new Observable<T>() {
            @Override
            public void subscribe(Observer<T> observer) {
                Observer observerB = new Observer<T>() {

                    @Override
                    public void onNext(T t) {
                        new Thread(() -> observer.onNext(t)).start();
                    }

                    @Override
                    public void onError(Throwable e) {
                        new Thread(() -> observer.onError(e)).start();
                    }

                    @Override
                    public void onComplete() {
                        new Thread(() -> observer.onComplete()).start();
                    }
                };
                Observable.this.subscribe(observerB);
            }
        };
        }

}
