package com.example.yuri.itunessearch.api;

import com.example.yuri.itunessearch.model.ItemsHolder;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**

 */
interface ItunesApi {
    @GET("/search")
    Call<ItemsHolder> queryItems(@QueryMap Map<String, String> requestParams);
    @GET("/lookup")
    Call<ItemsHolder> lookupItemsById(@QueryMap Map<String, String> requestParams);
}
