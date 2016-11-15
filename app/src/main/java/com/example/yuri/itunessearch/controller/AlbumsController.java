package com.example.yuri.itunessearch.controller;

import com.example.yuri.itunessearch.api.ApiService;
import com.example.yuri.itunessearch.model.Album;
import com.example.yuri.itunessearch.model.ItemsHolder;
import com.example.yuri.itunessearch.model.ItunesItem;
import com.example.yuri.itunessearch.model.StringRequest;
import com.example.yuri.itunessearch.views.BaseView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 */

public class AlbumsController
        extends BaseController<StringRequest,
        List<Album>,
        BaseView<List<Album>>> {
    @SuppressWarnings("unused")
    private static final String TAG = "AlbumsController ";
    private ApiService mApiService;

    @SuppressWarnings("unused")
    public AlbumsController(ApiService mApiService) {
        this.mApiService = mApiService;
    }

    public AlbumsController() {
        mApiService = ApiService.getInstance();
    }

    public void requestDataAsync(StringRequest request) {
        if (request == null) return;
        propogateProgress(1);
        mApiService
                .getAlbums(request.getRequest(), 100).enqueue(new Callback<ItemsHolder>() {
            @Override
            public void onResponse(Call<ItemsHolder> call, Response<ItemsHolder> response) {
                if (getView() != null && isViewAttached()) getView().showDownloadProgress(100);
                Set<Album> albums = new HashSet<>();
                for (ItunesItem item : response.body().getResults()) {

                    albums.add(new Album(item.getCollectionId(), item.getArtistName(), item.getCollectionName(), item.getArtworkUrl100()));
                }
                propogateData(new ArrayList<>(albums));
            }

            @Override
            public void onFailure(Call<ItemsHolder> call, Throwable t) {
                propogateError(t);
            }
        });
    }
}
