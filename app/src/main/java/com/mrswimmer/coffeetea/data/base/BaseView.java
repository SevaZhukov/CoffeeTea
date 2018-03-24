package com.mrswimmer.coffeetea.data.base;

import com.arellomobile.mvp.MvpView;

public interface BaseView extends MvpView {
    void showToast(String message);
    void showDialog(String title, String message);
}
