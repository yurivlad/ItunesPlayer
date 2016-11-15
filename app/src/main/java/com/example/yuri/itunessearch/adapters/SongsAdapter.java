package com.example.yuri.itunessearch.adapters;

import android.view.ViewGroup;

import com.example.yuri.itunessearch.R;
import com.example.yuri.itunessearch.adapters.viewholders.SongViewHolder;
import com.example.yuri.itunessearch.model.Song;

import java.util.List;

/**
 *
 */

public class SongsAdapter
        extends BaseAdapter<SongViewHolder,
        Song,
        SongsAdapter.PlayRequest,
        List<Song>>
        implements SongViewHolder.UserInputLister {
    @SuppressWarnings("unused")
    private static final String TAG = "SongsAdapter ";

    public SongsAdapter(List<Song> list,
                        SongsAdapter.PlayRequest playRequestReaction) {
        super(list, playRequestReaction);
    }


    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SongViewHolder(parent, R.layout.item_song);
    }

    @Override
    public void onBindViewHolder(SongViewHolder holder, int position) {
        holder.onBind(list.get(position), this);
    }

    @Override
    public void onStartButtonClick(Song incomingSong) {
        int position = list.lastIndexOf(incomingSong);
        if (position > -1) {
            Song.PlaybackStatus status = incomingSong.getStatus();
            status.setPlaying(!incomingSong.getStatus().isPlaying());
            notifyItemChanged(position);
            propogateSongStatus(incomingSong);
            if (incomingSong.getStatus().isPlaying()) {
                for (int i = 0; i < list.size(); i++) {
                    if (i == position) continue;
                    if (list.get(i).getStatus().isPlaying()) {
                        list.get(i).getStatus().setPlaying(false);
                        notifyItemChanged(i);
                    }
                }
            }
        }
    }

    public void setPlayProgress(Song incomingSong) {
        for (Song listSong : list) {
            if (listSong.equals(incomingSong)) {
                listSong.getStatus().setCurrentProgressSeconds(incomingSong.getStatus().getCurrentProgressSeconds());
                notifyItemChanged(list.lastIndexOf(listSong));
            }
        }
    }

    @Override
    public void onDrag(Song incomingSong, int progress) {
        int position = list.lastIndexOf(incomingSong);
        if (position > -1) {
            Song.PlaybackStatus status = incomingSong.getStatus();
            status.setCurrentProgressSeconds(convertProgressToMillis(incomingSong,progress));
            propogateSongStatus(incomingSong);
        }
    }

    private long convertProgressToMillis(Song song, int progress) {
        Song.PlaybackStatus status = song.getStatus();
        double percent = progress / 100;
        return Math.round(status.getDurationSeconds() * percent);
    }

    private void propogateSongStatus(Song song) {
        Song.PlaybackStatus status = song.getStatus();
        if (null != reactor) {
            if (status.isPlaying()) reactor.play(song);
            else reactor.stop();
        }
    }

    public interface PlayRequest extends Aware {
        void play(Song song);

        void stop();
    }
}
