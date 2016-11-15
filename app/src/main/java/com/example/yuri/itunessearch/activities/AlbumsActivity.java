package com.example.yuri.itunessearch.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.AnyThread;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.yuri.itunessearch.LateTextWatcher;
import com.example.yuri.itunessearch.R;
import com.example.yuri.itunessearch.adapters.AlbumsAdapter;
import com.example.yuri.itunessearch.controller.AlbumsController;
import com.example.yuri.itunessearch.model.Album;
import com.example.yuri.itunessearch.model.StringRequest;
import com.example.yuri.itunessearch.views.BaseView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.text.TextUtils.isEmpty;

public class AlbumsActivity
        extends BaseActivity<BaseView<List<Album>>, AlbumsController>
        implements BaseView<List<Album>>, AlbumsAdapter.AlbumChooseAware {
    @SuppressWarnings("unused")
    private static final String TAG = "AlbumsActivity ";
    private ProgressDialog mProgressDialog;
    private AlbumsAdapter mAlbumsAdapter;
    private ImageButton mEraseButton;

    protected void initViews() {
        final EditText searchInput = (EditText) findViewById(R.id.search_input);
        View searchButton = findViewById(R.id.search_button);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mEraseButton = (ImageButton) findViewById(R.id.erase);
        searchInput.addTextChangedListener(new LateTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                if (isEmpty(editable)) {
                    mAlbumsAdapter.clear();
                }
            }
        });
        mEraseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(searchInput.getText())) return;
                searchInput.setText("");
                if (null != mAlbumsAdapter) mAlbumsAdapter.clear();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(searchInput, InputMethodManager.SHOW_FORCED);
            }
        });

        recyclerView.setAdapter(mAlbumsAdapter =
                new AlbumsAdapter(new ArrayList<Album>(),
                Picasso.with(this),
                this));
        final Context ctx = this;
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(searchInput.getText())) {
                    Toast.makeText(ctx, R.string.enter_query, Toast.LENGTH_SHORT).show();
                    return;
                }
                getPresenter().requestDataAsync(new StringRequest(searchInput.getText().toString()));
            }
        });

    }

    @Override
    public void onAlbumChoose(Album album) {
        Intent i = SongsActivity.getStartIntent(album, this);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        if (mAlbumsAdapter.getItemCount() > 0) {
            mEraseButton.callOnClick();
        } else {
            super.onBackPressed();
        }
    }

    @AnyThread
    @Override
    public void displayData(final List<Album> data) {
        h.post(new Runnable() {
            @Override
            public void run() {
                if (data != null && mAlbumsAdapter != null) {
                    mAlbumsAdapter.changeItems(data);
                    View view = getCurrentFocus();
                    if (data.size() > 0
                            && view != null
                            && view.getId() == R.id.search_input) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                }
            }
        });
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @NonNull
    @Override
    public AlbumsController createPresenter() {
        return new AlbumsController();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @AnyThread
    @Override
    public void onError(final Throwable t) {
        showErrorToast(t);
    }

    @Override
    @AnyThread
    public void showDownloadProgress(final int progress) {
        h.post(new Runnable() {
            @Override
            public void run() {
                if (progress > 0
                        && progress < 101
                        && mProgressDialog == null) {
                    showProgress();
                } else {
                    hideProgress();
                }
            }
        });
    }

    @Override
    protected void showProgress() {
        if (null != mProgressDialog) hideProgress();
        mProgressDialog = createProgressDialog();
        mProgressDialog.show();
    }

    @Override
    protected void hideProgress() {
        if (null != mProgressDialog) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    private ProgressDialog createProgressDialog() {
        ProgressDialog ProgressDialog = new ProgressDialog(this);
        ProgressDialog.setTitle(getString(R.string.wait_please));
        ProgressDialog.setCancelable(false);
        return ProgressDialog;
    }
}
