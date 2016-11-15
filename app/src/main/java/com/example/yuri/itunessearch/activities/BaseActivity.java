package com.example.yuri.itunessearch.activities;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PersistableBundle;
import android.support.annotation.AnyThread;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.Toast;

import com.example.yuri.itunessearch.R;
import com.example.yuri.itunessearch.views.BaseView;
import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

import java.io.IOException;

/**
 *
 */

public abstract class BaseActivity<V extends BaseView<?>, P extends MvpPresenter<V>> extends MvpActivity<V,P> {
    protected static Handler h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initViews();
        if (h == null) h = new Handler(Looper.getMainLooper());
    }

    protected abstract void initViews();

    @LayoutRes
    protected abstract int getLayout();

    protected abstract void showProgress();

    protected abstract void hideProgress();

    @AnyThread
    protected void showErrorToast(final Throwable t) {
        final Context ctx = this;
        h.post(new Runnable() {
            @Override
            public void run() {
                hideProgress();
                if (t instanceof IOException) {
                    Toast.makeText(ctx, R.string.check_connection, Toast.LENGTH_SHORT).show();
                } else {
                    if (null != t.getMessage())
                        Toast.makeText(ctx, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
