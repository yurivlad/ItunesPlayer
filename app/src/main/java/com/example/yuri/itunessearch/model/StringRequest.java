package com.example.yuri.itunessearch.model;

import android.support.annotation.NonNull;

/**
 *
 */

public class StringRequest extends Request<String> {

    public StringRequest(@NonNull String request) {
        super(request);
    }

    @Override
    public String getRequest() {
        return request;
    }
}
