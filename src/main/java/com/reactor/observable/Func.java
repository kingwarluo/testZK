package com.reactor.observable;

public interface Func<T, R> {

    R call(T t);

}
