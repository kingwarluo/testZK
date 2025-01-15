package com.reactor.observable;

public interface Observer<T> {

    void onNext(T value);

    void onError(Throwable e);

    void onComplete();

}
