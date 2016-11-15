package com.example.yuri.itunessearch.adapters.viewholders;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuri.itunessearch.R;
import com.example.yuri.itunessearch.model.Album;
import com.example.yuri.itunessearch.model.ItunesItem;
import com.squareup.picasso.Picasso;

import static android.text.TextUtils.isEmpty;

/**
 *
 */
public class AlbumItemHolder extends BaseHolder {
    private static final String TAG = "AlbumItemHolder ";
    private TextView title;
    private TextView subtitle;
    private TextView description;
    private ImageView imageView;

    public AlbumItemHolder(ViewGroup parent, @LayoutRes int layoutId) {
        super(parent, layoutId);
        title = (TextView) itemView.findViewById(R.id.artist_name);
        subtitle = (TextView) itemView.findViewById(R.id.item_name);
        description = (TextView) itemView.findViewById(R.id.description);
        imageView = (ImageView) itemView.findViewById(R.id.image);
    }

    public void onBind(
            Picasso picasso,
            Album item,
            View.OnClickListener onRowClick) {
        if (item == null) return;
        if (!isEmpty(item.getAuthor())) title.setText(item.getAuthor());
        if (!isEmpty(item.getTitle()))
            description.setText(item.getTitle());
        if (!isEmpty(item.getCoverUrl())) {
            picasso
                    .load(item.getCoverUrl())
                    .fit()
                    .centerCrop()
                    .error(android.R.drawable.ic_menu_close_clear_cancel)
                    .into(imageView);
        }
        if (null != onRowClick) {
            ViewGroup vg = (ViewGroup) itemView;
            vg.setOnClickListener(onRowClick);
            for (int i = 0; i < vg.getChildCount(); i++) {
                vg.getChildAt(i).setOnClickListener(onRowClick);
            }
        }
    }
}
