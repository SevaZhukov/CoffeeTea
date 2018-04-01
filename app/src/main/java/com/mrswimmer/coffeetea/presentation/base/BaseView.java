package com.mrswimmer.coffeetea.presentation.base;

import android.view.MenuItem;

import com.arellomobile.mvp.MvpView;

public interface BaseView extends MvpView {
    void showToast(String message);
    void showDialog(String title, String message);
}
