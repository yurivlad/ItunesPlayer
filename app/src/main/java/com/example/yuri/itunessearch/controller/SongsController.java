package com.example.yuri.itunessearch.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.yuri.itunessearch.activities.SongsActivity;
import com.example.yuri.itunessearch.api.ApiService;
import com.example.yuri.itunessearch.model.ItemsHolder;
import com.example.yuri.itunessearch.model.ItunesItem;
import com.example.yuri.itunessearch.model.RequestById;
import com.example.yuri.itunessearch.model.Song;
import com.example.yuri.itunessearch.services.MusicService;
import com.example.yuri.itunessearch.views.BaseView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 */

public class SongsController extends BaseController<RequestById,
        List<Song>,
        BaseView<List<Song>>> {
    @SuppressWarnings("unused")
    private static final String TAG = "SongsController ";
    private ApiService mApiService;

    public SongsController() {
        mApiService = ApiService.getInstance();
    }


    @Override
    public void requestDataAsync(final RequestById request) {
        if (request == null) return;
        propogateProgress(1);
        mApiService
                .getSongsByAlbumId(request.getRequest())
                .enqueue(new Callback<ItemsHolder>() {
                    @Override
                    public void onResponse(Call<ItemsHolder> call, Response<ItemsHolder> response) {
                        List<Song> list = new ArrayList<>();
                        if (response.body().getResults() != null) {
                            for (ItunesItem item : response.body().getResults()) {
                                if (item.getKind() != null && item.getKind().equals("song"))
                                    list.add(new Song(
                                            item.getTrackId(),
                                            String.valueOf(item.getCollectionId()),
                                            item.getTrackName(),
                                            item.getPreviewUrl()));
                            }
                        }
                        propogateProgress(100);
                        propogateData(list);
                    }

                    @Override
                    public void onFailure(Call<ItemsHolder> call, Throwable t) {
                        propogateError(t);
                    }
                });
    }

    @SuppressWarnings("unused")
    public SongsController(ApiService mApiService) {
        this.mApiService = mApiService;
    }
}
