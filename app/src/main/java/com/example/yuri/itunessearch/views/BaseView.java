package com.example.yuri.itunessearch.views;

import com.example.yuri.itunessearch.model.ContextProvider;
import com.example.yuri.itunessearch.model.ErrorAware;
import com.example.yuri.itunessearch.model.ListData;
import com.example.yuri.itunessearch.model.ProgressDisplayer;
import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

/**
 *
 */

public interface BaseView<D extends List<? extends ListData>>
        extends MvpView,
        ProgressDisplayer,
        ContextProvider,
        ErrorAware {
    void displayData(D data);
}
