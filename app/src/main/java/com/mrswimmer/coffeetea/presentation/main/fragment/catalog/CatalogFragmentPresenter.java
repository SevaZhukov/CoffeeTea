package com.mrswimmer.coffeetea.presentation.main.fragment.catalog;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mrswimmer.coffeetea.App;
import com.mrswimmer.coffeetea.data.model.Product;
import com.mrswimmer.coffeetea.data.settings.Screens;
import com.mrswimmer.coffeetea.di.qualifier.Local;
import com.mrswimmer.coffeetea.domain.service.FireService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class CatalogFragmentPresenter extends MvpPresenter<CatalogFragmentView> {
    @Inject
    @Local
    Router router;
    @Inject
    FireService fireService;
    public CatalogFragmentPresenter() {
        App.getComponent().inject(this);
    }

    public void gotoFilters() {
        router.navigateTo(Screens.FILTERS_SCREEN);
    }

    public void setProductsForRecycler() {
        ArrayList<Product> productsForRecycler = new ArrayList<>();
        fireService.getProductsWithParam(new FireService.ProductsCallback() {
            @Override
            public void onSuccess(List<Product> products) {
                Log.i("code", "set " + products.size());
                for(int i=0; i<products.size(); i++) {
                    productsForRecycler.add(products.get(i));
                }
                getViewState().initAdapter(productsForRecycler);
                Log.i("code", "setProd " + productsForRecycler.size());
            }

            @Override
            public void onError(Throwable e) {
                Log.i("code", "error get prod "+e);
            }
        });
    }
}
