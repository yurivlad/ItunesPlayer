package com.example.yuri.itunessearch.model;

import android.support.annotation.NonNull;

/**
 *
 */

public abstract class Request<R> {
    final R request;

    public abstract R getRequest();

    public Request(@NonNull R request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "Request{" +
                "request=" + request +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Request)) return false;

        Request<?> request1 = (Request<?>) o;

        return request != null ? request.equals(request1.request) : request1.request == null;

    }

    @Override
    public int hashCode() {
        return request != null ? request.hashCode() : 0;
    }
}
