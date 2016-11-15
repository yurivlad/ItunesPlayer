package com.example.yuri.itunessearch.adapters.viewholders;

import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.yuri.itunessearch.R;
import com.example.yuri.itunessearch.model.Song;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 *
 */

public class SongViewHolder extends BaseHolder {
    @SuppressWarnings("unused")
    private static final String TAG = "SongViewHolder ";
    private SeekBar mSeekBar;
    private TextView mTitleTextView;
    private TextView mDurationTextView;
    private ImageView mStartButton;
    private static SimpleDateFormat sdf;

    public SongViewHolder(ViewGroup parent, @LayoutRes int layoutId) {
        super(parent, layoutId);
        mSeekBar = (SeekBar) itemView.findViewById(R.id.seeker);
        mTitleTextView = (TextView) itemView.findViewById(R.id.song_title);
        mDurationTextView = (TextView) itemView.findViewById(R.id.song_duration);
        mStartButton = (ImageView) itemView.findViewById(R.id.start_button);
        if (null == sdf) {
            sdf = new SimpleDateFormat("mm:ss", Locale.US);
        }
    }

    public void onBind(final Song song, final UserInputLister lister) {
        if (song == null) return;
        float progress = ((float) song.getStatus().getCurrentProgressSeconds()) / song.getStatus().getDurationSeconds();
        progress *= 100;
        mSeekBar.setProgress((int) progress);
        String formattedTime = sdf.format(new Date(song.getStatus().getCurrentProgressSeconds() * 1000)) +
                "/"
                + sdf.format(new Date(song.getStatus().getDurationSeconds() * 1000));
        mDurationTextView.setText(formattedTime);
        mTitleTextView.setText(song.getSongName());
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != lister) lister.onStartButtonClick(song);
            }
        });
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, final int progress, boolean fromUser) {

                if (null != lister && fromUser) lister.onDrag(song, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        @DrawableRes int d = song.getStatus().isPlaying() ? R.drawable.ic_pause_circle_filled_blue_24dp : R.drawable.ic_play_circle_filled_blue_24dp;
        mStartButton.setImageResource(d);
    }

    public interface UserInputLister {
        void onStartButtonClick(Song s);

        void onDrag(Song song, int progress);
    }
}
