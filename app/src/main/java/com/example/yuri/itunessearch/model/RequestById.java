package com.example.yuri.itunessearch.model;

/**
 *
 */

public class RequestById extends Request<Long> {
    public RequestById(Long request) {
        super(request);
    }

    @Override
    public Long getRequest() {
        return request;
    }
}
