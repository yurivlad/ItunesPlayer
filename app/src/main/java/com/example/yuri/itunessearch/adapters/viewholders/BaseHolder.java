package com.example.yuri.itunessearch.adapters.viewholders;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 *
 */

abstract class BaseHolder extends RecyclerView.ViewHolder {
    BaseHolder(ViewGroup parent, @LayoutRes int layoutId) {
        super(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent,false));
    }
}
