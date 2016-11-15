package com.example.yuri.itunessearch.activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.AnyThread;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.yuri.itunessearch.R;
import com.example.yuri.itunessearch.adapters.SongsAdapter;
import com.example.yuri.itunessearch.controller.SongsController;
import com.example.yuri.itunessearch.model.Album;
import com.example.yuri.itunessearch.model.RequestById;
import com.example.yuri.itunessearch.model.Song;
import com.example.yuri.itunessearch.services.MusicService;
import com.example.yuri.itunessearch.views.BaseView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class SongsActivity
        extends BaseActivity<BaseView<List<Song>>, SongsController>
        implements BaseView<List<Song>>, SongsAdapter.PlayRequest {
    private static final String ALBUM_TAG = "ALBUM_TAG ";
    @SuppressWarnings("unused")
    private static final String TAG = "SongsActivity ";
    private ProgressBar mProgressBar;
    private SongsAdapter mSongsAdapter;
    private ImageView mCoverImageView;
    private Album album;
    private Handler h = new Handler(Looper.getMainLooper());
    private SongPlayingReceiver mSongPlayingReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        album = getIntent().getParcelableExtra(ALBUM_TAG);
        if (getIntent().getParcelableExtra(ALBUM_TAG) == null)
            throw new IllegalStateException("you *must* start activity from static builder");
        super.onCreate(savedInstanceState);
        getPresenter().requestDataAsync(new RequestById(album.getId()));
        setCoverImage(album.getCoverUrl());
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(SongPlayingReceiver.ACTION_LOADING);
        filter.addAction(SongPlayingReceiver.ACTION_PLAYING);
        mSongPlayingReceiver = new SongPlayingReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(mSongPlayingReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mSongPlayingReceiver);
    }

    @Override
    protected void initViews() {
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mSongsAdapter = new SongsAdapter(new ArrayList<Song>(), this));
        Toolbar t = (Toolbar) findViewById(R.id.toolbar);
        t.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        t.setTitle(album.getAuthor());
        t.setSubtitle(album.getTitle());
        @ColorInt int white = ContextCompat.getColor(this, android.R.color.white);
        t.setSubtitleTextColor(white);
        t.setTitleTextColor(white);
        setSupportActionBar(t);
        t.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void play(Song song) {
        Intent i = new Intent(this, MusicService.class);
        i.putExtra(MusicService.SONG_TAG, song);
        startService(i);
    }

    @Override
    public void stop() {
        Intent i = new Intent(this, MusicService.class);
        i.putExtra(MusicService.STOP_TAG, MusicService.STOP_TAG);
        startService(i);

    }

    public static Intent getStartIntent(Album album,
                                        Activity startActivity) {
        Intent i = new Intent(startActivity, SongsActivity.class);
        i.putExtra(ALBUM_TAG, album);
        return i;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stop();
    }

    private void setCoverImage(String url) {
        if (null == mCoverImageView) {
            mCoverImageView = (ImageView) findViewById(R.id.image);
        }
        if (null != url && mCoverImageView != null) {
            Picasso
                    .with(this)
                    .load(url)
                    .into(mCoverImageView, new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {
                            mCoverImageView.setImageResource(android.R.drawable.ic_notification_clear_all);
                        }
                    });
        }
    }


    @AnyThread
    @Override
    public void displayData(final List<Song> data) {
        if (data == null) return;
        h.post(new Runnable() {
            @Override
            public void run() {
                mSongsAdapter.changeItems(data);
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_songs;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @AnyThread
    @Override
    public void onError(final Throwable t) {
        h.post(new Runnable() {
            @Override
            public void run() {
                showErrorToast(t);
            }
        });
    }

    @AnyThread
    @Override
    public void showDownloadProgress(final int progress) {
        h.post(new Runnable() {
            @Override
            public void run() {
                if (progress > 0
                        && progress < 101
                        && mProgressBar.getVisibility() == View.GONE) {
                    showProgress();
                } else {
                    hideProgress();
                }
            }
        });
    }

    private void propogatePlayProgress(Song song) {
        if (null != mSongsAdapter && song != null) mSongsAdapter.setPlayProgress(song);
    }

    @Override
    protected void showProgress() {
        if (null != mProgressBar) mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void hideProgress() {
        if (null != mProgressBar) mProgressBar.setVisibility(View.GONE);
    }

    @NonNull
    @Override
    public SongsController createPresenter() {
        return new SongsController();
    }

    public class SongPlayingReceiver extends BroadcastReceiver {
        public static final String ACTION_LOADING = "ACTION_LOADING";
        public static final String ACTION_PLAYING = "ACTION_PLAYING";

        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(ACTION_LOADING)) {
                showDownloadProgress(1);
            } else if (intent.getAction().equals(ACTION_PLAYING)) {
                hideProgress();
                try {
                    propogatePlayProgress((Song) intent.getParcelableExtra(ACTION_PLAYING));
                } catch (ClassCastException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
