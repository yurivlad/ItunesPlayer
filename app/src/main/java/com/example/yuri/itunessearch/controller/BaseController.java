package com.example.yuri.itunessearch.controller;

import com.example.yuri.itunessearch.model.ListData;
import com.example.yuri.itunessearch.model.Request;
import com.example.yuri.itunessearch.views.BaseView;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.List;

/**
 *
 */

abstract class BaseController<R extends Request<?>,
        L extends List<? extends ListData>,
        V extends BaseView<L>>
        extends MvpBasePresenter<V> {
    public abstract void requestDataAsync(R request);



    void propogateError(Throwable r) {
        if (getView() != null && isViewAttached()) getView().onError(r);
    }

    void propogateData(L data) {
        if (getView() != null && isViewAttached()) getView().displayData(data);
    }

    void propogateProgress(int progress) {
        if (getView() != null && isViewAttached()) getView().showDownloadProgress(progress);
    }
}
