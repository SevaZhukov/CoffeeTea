package com.mrswimmer.coffeetea.presentation.main.fragment.catalog;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mrswimmer.coffeetea.App;
import com.mrswimmer.coffeetea.data.settings.Screens;
import com.mrswimmer.coffeetea.di.qualifier.Local;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class CatalogFragmentPresenter extends MvpPresenter<CatalogFragmentView> {
    @Inject
    @Local
    Router router;

    public CatalogFragmentPresenter() {
        App.getComponent().inject(this);
    }

    public void gotoFilters() {
        router.navigateTo(Screens.FILTERS_SCREEN);
    }
}
