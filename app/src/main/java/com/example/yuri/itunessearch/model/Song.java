package com.example.yuri.itunessearch.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

/**
 *
 */
@SuppressWarnings("unused")
public class Song implements ListData, Parcelable {
    private final long songId;
    private final String albumId;
    private final String songName;
    private final String sampleUrl;
    private PlaybackStatus status;

    public Song(long songId, String albumId, String songName, String sampleUrl) {
        this.songId = songId;
        this.albumId = albumId;
        this.songName = songName;
        status = new PlaybackStatus(30, 0);
        this.sampleUrl = sampleUrl;
    }


    public String getSampleUrl() {
        return sampleUrl;
    }

    public
    @NonNull
    PlaybackStatus getStatus() {
        return status;
    }

    public static class PlaybackStatus implements Parcelable {
        private long durationSeconds;
        private long currentProgressSeconds;
        private boolean isPlaying;

        public PlaybackStatus(long durationSeconds, long currentProgressSeconds) {
            this.durationSeconds = durationSeconds;
            this.currentProgressSeconds = currentProgressSeconds;
        }

        public boolean isPlaying() {
            return isPlaying;

        }


        public void setPlaying(boolean playing) {
            isPlaying = playing;
        }

        public long getDurationSeconds() {
            return durationSeconds;
        }

        public void setDurationSeconds(long durationSeconds) {
            this.durationSeconds = Math.abs(durationSeconds);
        }

        @Override
        public String toString() {
            return "PlaybackStatus{" +
                    "durationSeconds=" + durationSeconds +
                    ", currentProgressSeconds=" + currentProgressSeconds +
                    '}';
        }

        public void setCurrentProgressSeconds(long currentProgressSeconds) {
            this.currentProgressSeconds = currentProgressSeconds;
        }

        public long getCurrentProgressSeconds() {
            return currentProgressSeconds;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeLong(this.durationSeconds);
            dest.writeLong(this.currentProgressSeconds);
            dest.writeByte(this.isPlaying ? (byte) 1 : (byte) 0);
        }

        protected PlaybackStatus(Parcel in) {
            this.durationSeconds = in.readLong();
            this.currentProgressSeconds = in.readLong();
            this.isPlaying = in.readByte() != 0;
        }

        public static final Creator<PlaybackStatus> CREATOR = new Creator<PlaybackStatus>() {
            @Override
            public PlaybackStatus createFromParcel(Parcel source) {
                return new PlaybackStatus(source);
            }

            @Override
            public PlaybackStatus[] newArray(int size) {
                return new PlaybackStatus[size];
            }
        };
    }


    public String getAlbumId() {
        return albumId;
    }

    public String getSongName() {
        return songName;
    }

    @Override
    public String toString() {
        return "Song{" +
                "songId=" + songId +
                ", albumId='" + albumId + '\'' +
                ", songName='" + songName + '\'' +
                ", sampleUrl='" + sampleUrl + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song)) return false;

        Song song = (Song) o;

        if (songId != song.songId) return false;
        if (albumId != null ? !albumId.equals(song.albumId) : song.albumId != null) return false;
        if (songName != null ? !songName.equals(song.songName) : song.songName != null)
            return false;
        return sampleUrl != null ? sampleUrl.equals(song.sampleUrl) : song.sampleUrl == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (songId ^ (songId >>> 32));
        result = 31 * result + (albumId != null ? albumId.hashCode() : 0);
        result = 31 * result + (songName != null ? songName.hashCode() : 0);
        result = 31 * result + (sampleUrl != null ? sampleUrl.hashCode() : 0);
        return result;
    }

    public long getSongId() {
        return songId;
    }

    @Override
    public long getId() {
        return songId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.songId);
        dest.writeString(this.albumId);
        dest.writeString(this.songName);
        dest.writeString(this.sampleUrl);
        dest.writeParcelable(this.status, flags);
    }

    protected Song(Parcel in) {
        this.songId = in.readLong();
        this.albumId = in.readString();
        this.songName = in.readString();
        this.sampleUrl = in.readString();
        this.status = in.readParcelable(PlaybackStatus.class.getClassLoader());
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel source) {
            return new Song(source);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };
}
