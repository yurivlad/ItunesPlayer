package com.example.yuri.itunessearch.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yuri on 16.09.2016.
 */
public class ItemsHolder {
    @SerializedName("resultCount")
    private int resultCount;
    private List<ItunesItem> results;

    public ItemsHolder(int resultCount, List<ItunesItem> results) {
        this.resultCount = resultCount;
        this.results = results;
    }

    public int getResultCount() {
        return resultCount;
    }

    public List<ItunesItem> getResults() {
        return results;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public void setResults(List<ItunesItem> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "ItemsHolder{" +
                "results=" + results +
                ", resultCount=" + resultCount +
                '}';
    }
}
