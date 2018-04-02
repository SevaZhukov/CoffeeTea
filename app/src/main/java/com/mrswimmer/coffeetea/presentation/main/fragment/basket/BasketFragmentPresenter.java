package com.mrswimmer.coffeetea.presentation.main.fragment.basket;

import android.content.SharedPreferences;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mrswimmer.coffeetea.App;
import com.mrswimmer.coffeetea.data.model.Order;
import com.mrswimmer.coffeetea.data.model.ProductInBasket;
import com.mrswimmer.coffeetea.data.model.product.Product;
import com.mrswimmer.coffeetea.data.settings.Screens;
import com.mrswimmer.coffeetea.data.settings.Settings;
import com.mrswimmer.coffeetea.di.qualifier.Local;
import com.mrswimmer.coffeetea.domain.service.FireService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class BasketFragmentPresenter extends MvpPresenter<BasketFragmentView> {
    ArrayList<ProductInBasket> arrayList;
    int sum=0;
    @Inject
    FireService fireService;
    @Inject
    SharedPreferences settings;
    @Inject
    @Local
    Router localRouter;

    public BasketFragmentPresenter() {
        App.getComponent().inject(this);
    }

    public void setRecycler() {
        String userId = settings.getString(Settings.USER_ID, "0");
        fireService.getBasket(userId, new FireService.BasketCallback() {
            @Override
            public void onSuccess(List<ProductInBasket> products) {
                arrayList = (ArrayList<ProductInBasket>) products;

                if(products.size()==0) {
                    getViewState().setBottomVisible(false);
                    getViewState().setEmptyText();
                } else {
                    getViewState().initAdapter(arrayList);
                    getViewState().setBottomVisible(true);
                    for(int i=0; i<products.size(); i++) {
                        ProductInBasket product = products.get(i);
                        if(product.getNewCost()>0)
                            sum+=product.getNewCost()*product.getCount();
                        else
                            sum+=product.getCost()*product.getCount();
                    }
                    getViewState().setSum(sum);
                }
            }

            @Override
            public void onError(Throwable e) {
                getViewState().showToast(e.getMessage());
                getViewState().setBottomVisible(false);
                getViewState().setEmptyText();
            }
        });
    }

    public void toOrder() {
        String userId = settings.getString(Settings.USER_ID, "0");
        Date date = new Date();
        Order order = new Order(arrayList, sum, date);
        fireService.makeOrder(userId, order);
        fireService.clearBasket(userId);
        localRouter.replaceScreen(Screens.ORDERS_SCREEN);
    }
}
