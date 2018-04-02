package com.mrswimmer.coffeetea.presentation.main.fragment.shop;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mrswimmer.coffeetea.App;
import com.mrswimmer.coffeetea.data.model.Shop;
import com.mrswimmer.coffeetea.domain.service.FireService;
import com.mrswimmer.coffeetea.presentation.main.fragment.product.ProductFragmentPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@InjectViewState
public class ShopFragmentPresenter extends MvpPresenter<ShopFragmentView> {

    @Inject
    FireService fireService;

    public ShopFragmentPresenter() {
        App.getComponent().inject(this);
    }

    public void setShopsForRecycler(boolean choose) {
        fireService.getShops(new FireService.ShopsCallback() {
            @Override
            public void onSuccess(List<Shop> shops) {
                ArrayList<Shop> array = (ArrayList<Shop>) shops;
                Log.i("code", "shops " + array.size());
                if (choose)
                    filterShops(array);
                else
                    getViewState().initAdapter(array, false);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    void filterShops(ArrayList<Shop> shops) {
        ArrayList<Shop> array = new ArrayList<>();
        for (int i = 0; i < shops.size(); i++) {
            if (ProductFragmentPresenter.keys.contains(shops.get(i).getId()))
                array.add(shops.get(i));
        }
        Log.i("code", "shops choose " + array.size());
        getViewState().initAdapter(array, true);
    }
}
