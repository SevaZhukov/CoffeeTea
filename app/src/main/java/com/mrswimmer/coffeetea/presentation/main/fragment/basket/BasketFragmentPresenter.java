package com.mrswimmer.coffeetea.presentation.main.fragment.basket;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mrswimmer.coffeetea.App;

@InjectViewState
public class BasketFragmentPresenter extends MvpPresenter<BasketFragmentView> {

    public BasketFragmentPresenter() {
        App.getComponent().inject(this);
    }
}
