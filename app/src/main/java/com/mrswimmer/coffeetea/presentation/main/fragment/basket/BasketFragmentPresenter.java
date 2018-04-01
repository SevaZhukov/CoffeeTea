package com.mrswimmer.coffeetea.presentation.main.fragment.basket;

import android.content.SharedPreferences;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mrswimmer.coffeetea.App;
import com.mrswimmer.coffeetea.data.model.ProductInBasket;
import com.mrswimmer.coffeetea.data.settings.Settings;
import com.mrswimmer.coffeetea.domain.service.FireService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@InjectViewState
public class BasketFragmentPresenter extends MvpPresenter<BasketFragmentView> {

    @Inject
    FireService fireService;
    @Inject
    SharedPreferences settings;

    public BasketFragmentPresenter() {
        App.getComponent().inject(this);
    }

    public void setRecycler() {
        String userId = settings.getString(Settings.USER_ID, "0");
        fireService.getBasket(userId, new FireService.BasketCallback() {
            @Override
            public void onSuccess(List<ProductInBasket> products) {
                ArrayList<ProductInBasket> arrayList = (ArrayList<ProductInBasket>) products;
                getViewState().initAdapter(arrayList);
            }

            @Override
            public void onError(Throwable e) {
                getViewState().showToast(e.getMessage());
            }
        });
    }
}
