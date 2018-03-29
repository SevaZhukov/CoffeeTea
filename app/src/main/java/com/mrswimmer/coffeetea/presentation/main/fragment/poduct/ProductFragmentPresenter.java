package com.mrswimmer.coffeetea.presentation.main.fragment.poduct;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mrswimmer.coffeetea.App;

@InjectViewState
public class ProductFragmentPresenter extends MvpPresenter<ProductFragmentView> {

    public ProductFragmentPresenter() {
        App.getComponent().inject(this);
    }

}
