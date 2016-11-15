package com.example.yuri.itunessearch.adapters;

import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 *
 */

public abstract class BaseAdapter<VH extends RecyclerView.ViewHolder,
        O,
        A extends Aware,
        L extends List<O>>
        extends RecyclerView.Adapter<VH> {
    L list;
    A reactor;

    BaseAdapter(L data, A reactor) {
        this.list = data;
        this.reactor = reactor;
    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    public void changeItems(L newList) {
        if (newList == null) return;
        clear();
        list.addAll(newList);
        notifyItemRangeInserted(0, list.size());
    }

    public void clear() {
        notifyItemRangeRemoved(0, list.size());
        list.clear();
    }
}
