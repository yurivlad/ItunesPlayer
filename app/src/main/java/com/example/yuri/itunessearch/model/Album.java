package com.example.yuri.itunessearch.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 */

public class Album implements ListData, Parcelable {
    private final long id;
    private final String author;
    private final String title;
    private final String coverUrl;

    public Album(long id, String author, String title, String coverUrl) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.coverUrl = coverUrl;
    }


    @Override
    public long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Album)) return false;

        Album album = (Album) o;

        if (id != album.id) return false;
        if (author != null ? !author.equals(album.author) : album.author != null) return false;
        return title != null ? title.equals(album.title) : album.title == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", coverUrl='" + coverUrl + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.author);
        dest.writeString(this.title);
        dest.writeString(this.coverUrl);
    }

    protected Album(Parcel in) {
        this.id = in.readLong();
        this.author = in.readString();
        this.title = in.readString();
        this.coverUrl = in.readString();
    }

    public static final Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel source) {
            return new Album(source);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };
}
