package com.mrswimmer.coffeetea.presentation.main.fragment.catalog;

import android.content.SharedPreferences;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mrswimmer.coffeetea.App;
import com.mrswimmer.coffeetea.data.model.Product;
import com.mrswimmer.coffeetea.data.settings.Screens;
import com.mrswimmer.coffeetea.data.settings.Settings;
import com.mrswimmer.coffeetea.di.qualifier.Local;
import com.mrswimmer.coffeetea.domain.service.FilterService;
import com.mrswimmer.coffeetea.domain.service.FireService;
import com.mrswimmer.coffeetea.presentation.main.fragment.filter.FilterFragmentPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.Provides;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class CatalogFragmentPresenter extends MvpPresenter<CatalogFragmentView> {
    ArrayList<Product> productsForRecycler = new ArrayList<>();
    @Inject
    @Local
    Router router;
    @Inject
    FireService fireService;
    @Inject
    FilterService filterService;
    @Inject
    SharedPreferences settings;

    public CatalogFragmentPresenter() {
        App.getComponent().inject(this);
    }

    public void gotoFilters() {
        router.navigateTo(Screens.FILTERS_SCREEN);
    }

    public void setProductsForRecycler() {
        Log.i("code", "sort " + settings.getBoolean(Settings.SORT, false));
        if (settings.getBoolean(Settings.SORT, false)) {
            getViewState().initAdapter(FilterFragmentPresenter.readyList);
            getViewState().showDropButton();
        } else {
            setProductsWithoutFilters();
        }
    }

    public void setProductsWithoutFilters() {
        fireService.getProducts(new FireService.ProductsCallback() {
            @Override
            public void onSuccess(List<Product> products) {
                Log.i("code", "set " + products.size());
                for (int i = 0; i < products.size(); i++) {
                    productsForRecycler.add(products.get(i));
                }
                getViewState().initAdapter(productsForRecycler);
                Log.i("code", "setProd " + productsForRecycler.size());
            }

            @Override
            public void onError(Throwable e) {
                Log.i("code", "error get prod " + e);
            }
        });
        getViewState().hideDropButton();
    }
    public void dropFilters() {
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(Settings.SORT, false);
        editor.apply();
        setProductsWithoutFilters();
    }

    public void gotoProd() {
        router.navigateTo(Screens.PRODUCT_SCREEN);
    }
    //public void getSaleProducts()
}
