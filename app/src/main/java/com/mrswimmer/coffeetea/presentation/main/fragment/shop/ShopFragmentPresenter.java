package com.mrswimmer.coffeetea.presentation.main.fragment.shop;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mrswimmer.coffeetea.App;
import com.mrswimmer.coffeetea.data.model.Review;
import com.mrswimmer.coffeetea.data.model.Shop;
import com.mrswimmer.coffeetea.data.model.product.Product;
import com.mrswimmer.coffeetea.domain.service.FireService;
import com.mrswimmer.coffeetea.presentation.main.fragment.product.ProductFragmentPresenter;
import com.mrswimmer.coffeetea.presentation.main.fragment.product.choose_count.ChooseCountDialog;

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

    public void setShopsForRecycler() {
        fireService.getShops(new FireService.ShopsCallback() {
            @Override
            public void onSuccess(List<Shop> shops) {
                ArrayList<Shop> array = (ArrayList<Shop>) shops;
                Log.i("code", "shops " + array.size());
                getViewState().initAdapter(array, false);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void chooseShopsForRecycler() {
        Product product = ProductFragmentPresenter.curProduct;
        ArrayList<Shop> shops = new ArrayList<Shop>();
        final int[] countShop = {0};
        for (int i = 0; i < product.getAvailabilities().size(); i++) {
            if (product.getAvailabilities().get(i).getQuantity() >= ChooseCountDialog.count) {
                int finalI = i;
                fireService.getShopsById(product.getAvailabilities().get(i).getShopId(), new FireService.ShopCallback() {
                    @Override
                    public void onSuccess(Shop shop) {
                        shops.add(shop);
                        countShop[0]++;
                        Log.i("code", "shop #" + finalI + shop.getAdress());
                        if (countShop[0] == ProductFragmentPresenter.countShops) {
                            Log.i("code", "shops " + shops.size());
                            //getViewState().initAdapter(shops, true);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("code", e.getMessage());
                    }
                });
            }
        }


    }
}
