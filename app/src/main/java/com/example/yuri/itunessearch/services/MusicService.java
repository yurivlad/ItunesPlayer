package com.example.yuri.itunessearch.services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.yuri.itunessearch.activities.SongsActivity;
import com.example.yuri.itunessearch.model.Song;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 */
@SuppressWarnings("unused")
public class MusicService extends Service {
    private static final String TAG = "MusicService ";
    public static final String SONG_TAG = "SONG_TAG ";
    public static final String STOP_TAG = "STOP_TAG ";
    private static String SERVICE_CMD = "com.sec.android.app.music.musicservicecommand";
    private static String PAUSE_SERVICE_CMD = "com.sec.android.app.music.musicservicecommand.pause";
    private static String PLAY_SERVICE_CMD = "com.sec.android.app.music.musicservicecommand.play";
    public static final String CMD_NAME = "CMD_NAME";
    public static final String CMD_PAUSE = "CMD_PAUSE";
    public static final String CMD_PLAY = "com.example.android.uamp.ACTION_CMD";
    public static final String CMD_STOP = "CMD_STOP_CASTING";
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private boolean mAudioFocusGranted = false;
    private boolean mAudioIsPlaying = false;
    private MediaPlayer mPlayer;
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener;
    private BroadcastReceiver mIntentReceiver;
    private boolean mReceiverRegistered = false;
    private Song currentSong;
    private Timer timer = new Timer();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getStringExtra(STOP_TAG) != null) {
            stop();
        } else if (intent.getParcelableExtra(SONG_TAG) != null) {
            Song incoming = intent.getParcelableExtra(SONG_TAG);
            if (currentSong != null && incoming != null && !currentSong.equals(incoming)) stop();
            if (null != incoming) currentSong = incoming;
            if (mOnAudioFocusChangeListener == null)
                mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
                    @Override
                    public void onAudioFocusChange(int focusChange) {
                        switch (focusChange) {
                            case AudioManager.AUDIOFOCUS_GAIN:
                                Log.i(TAG, "AUDIOFOCUS_GAIN");
                                play(currentSong);
                                break;
                            case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT:
                                Log.i(TAG, "AUDIOFOCUS_GAIN_TRANSIENT");
                                break;
                            case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK:
                                Log.i(TAG, "AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK");
                                break;
                            case AudioManager.AUDIOFOCUS_LOSS:
                                Log.e(TAG, "AUDIOFOCUS_LOSS");
                                pause();
                                break;
                            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                                Log.e(TAG, "AUDIOFOCUS_LOSS_TRANSIENT");
                                pause();
                                break;
                            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                                Log.e(TAG, "AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
                                break;
                            case AudioManager.AUDIOFOCUS_REQUEST_FAILED:
                                Log.e(TAG, "AUDIOFOCUS_REQUEST_FAILED");
                                break;
                            default:
                        }
                    }
                };
            requestAudioFocus();
            play(currentSong);
        }
        return START_STICKY;
    }

    private void play(final Song song) {
        if (song.getSampleUrl() == null || song.getSampleUrl().length() < 10) return;
        if (!mAudioIsPlaying) {
            if (mPlayer == null) {
                mPlayer = new MediaPlayer();
                try {
                    mPlayer.setDataSource(song.getSampleUrl());
                    mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (!mAudioFocusGranted && requestAudioFocus()) {
                forceMusicStop();
                setupBroadcastReceiver();
            }
            mPlayer.prepareAsync();
            sendLoadingBroadcast();
            mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(final MediaPlayer mp) {
                    long duration = mp.getDuration();
                    double progress = duration * (((double) song.getStatus().getCurrentProgressSeconds()) / 100);
                    mp.seekTo((int) Math.round(progress));
                    mAudioIsPlaying = true;
                    mPlayer.start();
                    currentSong.getStatus().setCurrentProgressSeconds(1);
                    sendPlayingBroadcast(currentSong);
                    TimerTask tt = new TimerTask() {
                        @Override
                        public void run() {
                            song.getStatus().setCurrentProgressSeconds((int) TimeUnit.MILLISECONDS.toSeconds(mp.getCurrentPosition()));
                            sendPlayingBroadcast(song);
                        }
                    };
                    timer = new Timer();
                    timer.schedule(tt, 1000, 1000);
                    mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            stop();
                        }
                    });
                }
            });
        }

    }

    private void sendLoadingBroadcast() {
        Intent i = new Intent(SongsActivity.SongPlayingReceiver.ACTION_LOADING);
        LocalBroadcastManager.getInstance(this).sendBroadcast(i);
    }

    private void sendPlayingBroadcast(Song song) {
        Intent i = new Intent(SongsActivity.SongPlayingReceiver.ACTION_PLAYING);
        i.putExtra(SongsActivity.SongPlayingReceiver.ACTION_PLAYING, song);
        LocalBroadcastManager.getInstance(this).sendBroadcast(i);
    }

    private void pause() {
        if (mAudioFocusGranted && mAudioIsPlaying) {
            mPlayer.pause();
            mAudioIsPlaying = false;
        }
    }

    public void stop() {
        if (mAudioFocusGranted && mAudioIsPlaying) {
            mPlayer.stop();
            mPlayer = null;
            mAudioIsPlaying = false;
            abandonAudioFocus();
            currentSong.getStatus().setCurrentProgressSeconds(0);
            sendPlayingBroadcast(currentSong);
            currentSong = null;
            if (null != timer) {
                timer.cancel();
                timer.purge();
                timer = new Timer();
            }
        }
    }

    private boolean requestAudioFocus() {
        if (!mAudioFocusGranted) {
            AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            int result = am.requestAudioFocus(mOnAudioFocusChangeListener,
                    AudioManager.STREAM_MUSIC,
                    AudioManager.AUDIOFOCUS_GAIN);
            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
                mAudioFocusGranted = true;
            else Log.e(TAG, "failed to get audio focus");
        }
        return mAudioFocusGranted;
    }

    private void abandonAudioFocus() {
        AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int result = am.abandonAudioFocus(mOnAudioFocusChangeListener);
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            mAudioFocusGranted = false;
        } else {
            Log.e(TAG,
                    "failed to drop audio focus");
        }
        mOnAudioFocusChangeListener = null;
    }

    private void setupBroadcastReceiver() {
        mIntentReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                String cmd = intent.getStringExtra(CMD_NAME);
                Log.i(TAG, "mIntentReceiver.onReceive " + action + " / " + cmd);

                if (PAUSE_SERVICE_CMD.equals(action)
                        || (SERVICE_CMD.equals(action) && CMD_PAUSE.equals(cmd))) {
                    play(currentSong);
                }

                if (PLAY_SERVICE_CMD.equals(action)
                        || (SERVICE_CMD.equals(action) && CMD_PLAY.equals(cmd))) {
                    pause();
                }
            }
        };
        if (!mReceiverRegistered) {
            IntentFilter commandFilter = new IntentFilter();
            commandFilter.addAction(SERVICE_CMD);
            commandFilter.addAction(PAUSE_SERVICE_CMD);
            commandFilter.addAction(PLAY_SERVICE_CMD);
            registerReceiver(mIntentReceiver, commandFilter);
            mReceiverRegistered = true;
        }
    }

    private void forceMusicStop() {
        AudioManager am = (AudioManager)
                getSystemService(Context.AUDIO_SERVICE);
        if (am.isMusicActive()) {
            Intent intentToStop = new Intent(SERVICE_CMD);
            intentToStop.putExtra(CMD_NAME, CMD_STOP);
            sendBroadcast(intentToStop);
        }
    }
}
