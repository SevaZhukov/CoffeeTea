package com.mrswimmer.coffeetea.presentation.main.fragment.filter;

import android.app.Dialog;

import com.arellomobile.mvp.MvpView;

interface FilterFragmentView extends MvpView {
    void setCountKinds(int countKinds);
    //Dialog onCreateDialog(int id);
}
