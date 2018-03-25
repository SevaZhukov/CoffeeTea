package com.mrswimmer.coffeetea.presentation.main.fragment.catalog;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mrswimmer.coffeetea.App;

@InjectViewState
public class CatalogFragmentPresenter extends MvpPresenter<CatalogFragmentView> {

    public CatalogFragmentPresenter() {
        App.getComponent().inject(this);
    }
}
