package com.mrswimmer.coffeetea.presentation.main.fragment.product;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mrswimmer.coffeetea.App;
import com.mrswimmer.coffeetea.data.model.Review;
import com.mrswimmer.coffeetea.data.model.product.Product;
import com.mrswimmer.coffeetea.data.settings.Screens;
import com.mrswimmer.coffeetea.di.qualifier.Local;
import com.mrswimmer.coffeetea.domain.service.FireService;

import java.util.ArrayList;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class ProductFragmentPresenter extends MvpPresenter<ProductFragmentView> {

    String id;
    @Inject
    FireService fireService;

    @Inject
    @Local
    Router router;

    @Inject
    public ProductFragmentPresenter() {
        App.getComponent().inject(this);
    }

    public void getProduct(String id) {
        this.id = id;
        fireService.getProduct(id, new FireService.ProductCallback() {
            @Override
            public void onSuccess(Product product) {
                Log.i("code", "prod name " + product.getName());
                getViewState().setProduct(product);
            }

            @Override
            public void onError(Throwable e) {
                Log.i("code", "prod name error " + e);
            }
        });
    }

    public void gotoReviews() {

        router.navigateTo(Screens.REVIEWS_SCREEN, id);
    }
}
