package com.example.yuri.itunessearch.api;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.yuri.itunessearch.model.ItemsHolder;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.QueryMap;

/**
 *
 */
@SuppressWarnings("unused")
public class ApiService {
    private static final String TAG = "ApiService ";
    private static final String ENTITY_SONG = "song";
    private Retrofit.Builder builder;
    private static final String BASE_URL = "https://itunes.apple.com";
    private static ApiService instance;
    private static final String SEARCH_STRING_PARAM = "term";
    private static final String ENTITY_PARAM = "entity";
    private static final String ENTITY_ALBUM = "album";
    private static final String ENTITY_ARTIST = "musicArtist";
    private static final String ENTITY_MUSIC_TRACK = "musicTrack";
    private static final String LOOKUP_ID_PARAM = "id";
    private static final String LIMIT_PARAM = "limit";
    private static final String MEDIA_PARAM = "media";
    private static final String ATTRIBUTES_PARAM = "attribute";
    private static final String ATTRIBUTES_SEARCH_BY_ARTIST_NAME = "artistTerm";


    public static ApiService getInstance() {
        if (instance == null) {
            synchronized (ApiService.class) {
                if (instance == null) instance = new ApiService();
            }
        }
        return instance;
    }

    private ApiService() {
        OkHttpClient client = new OkHttpClient
                .Builder()
                .addNetworkInterceptor(new HttpLoggingInterceptor())
                .build();
        builder = new Retrofit
                .Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(client);
    }

    public Call<ItemsHolder> getAlbums(@NonNull String request, int limit) {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put(LIMIT_PARAM, String.valueOf(Math.abs(limit)));
        requestMap.put(SEARCH_STRING_PARAM, request);
        requestMap.put(ENTITY_PARAM, ENTITY_ALBUM);
        requestMap.put(MEDIA_PARAM, "music");
        requestMap.put(ATTRIBUTES_PARAM, ATTRIBUTES_SEARCH_BY_ARTIST_NAME);
        return queryItunes(requestMap);
    }

    public Call<ItemsHolder> getSongsByAlbumId(long albumId) {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put(LOOKUP_ID_PARAM, String.valueOf(albumId));
        requestMap.put(ENTITY_PARAM, ENTITY_SONG);
        Log.e(TAG, ""+requestMap);
        return lookupItemsById(requestMap);
    }

    private Call<ItemsHolder> queryItunes(@QueryMap Map<String, String> requestMap) {
        return builder
                .build()
                .create(ItunesApi.class)
                .queryItems(requestMap);
    }

    private Call<ItemsHolder> lookupItemsById(@QueryMap Map<String, String> requestMap) {
        return builder
                .build()
                .create(ItunesApi.class)
                .lookupItemsById(requestMap);
    }
}
