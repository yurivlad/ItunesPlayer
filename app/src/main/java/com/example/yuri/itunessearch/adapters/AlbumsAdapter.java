package com.example.yuri.itunessearch.adapters;

import android.view.View;
import android.view.ViewGroup;

import com.example.yuri.itunessearch.R;
import com.example.yuri.itunessearch.adapters.viewholders.AlbumItemHolder;
import com.example.yuri.itunessearch.model.Album;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 *
 */
public class AlbumsAdapter
        extends BaseAdapter<AlbumItemHolder,
        Album,
        AlbumsAdapter.AlbumChooseAware,
        List<Album>> {
    @SuppressWarnings("unused")
    private static final String TAG = "AlbumsAdapter ";
    private Picasso picasso;
    public AlbumsAdapter(List<Album> list,
                         Picasso picasso,
                         AlbumChooseAware albumChooseAware) {
        super(list, albumChooseAware);
        this.picasso = picasso;
    }

    @Override
    public AlbumItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AlbumItemHolder(parent, R.layout.item_album);
    }

    @Override
    public void onBindViewHolder(final AlbumItemHolder holder, int position) {
        Album item = list.get(holder.getAdapterPosition());
        holder.onBind(
                picasso,
                item,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (null != reactor)
                            reactor.onAlbumChoose(list.get(holder.getAdapterPosition()));
                    }
                });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     *
     */

    public static interface AlbumChooseAware extends Aware {
        void onAlbumChoose(Album album);
    }
}
