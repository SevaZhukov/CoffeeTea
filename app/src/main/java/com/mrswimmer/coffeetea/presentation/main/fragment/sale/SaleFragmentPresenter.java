package com.mrswimmer.coffeetea.presentation.main.fragment.sale;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mrswimmer.coffeetea.App;

@InjectViewState
public class SaleFragmentPresenter extends MvpPresenter<SaleFragmentView> {

    public SaleFragmentPresenter() {
        App.getComponent().inject(this);
    }
}
