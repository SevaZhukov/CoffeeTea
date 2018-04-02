package com.mrswimmer.coffeetea.presentation.main.fragment.order;

import android.content.SharedPreferences;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mrswimmer.coffeetea.App;
import com.mrswimmer.coffeetea.data.model.Order;
import com.mrswimmer.coffeetea.data.model.ProductInBasket;
import com.mrswimmer.coffeetea.data.settings.Screens;
import com.mrswimmer.coffeetea.data.settings.Settings;
import com.mrswimmer.coffeetea.di.qualifier.Local;
import com.mrswimmer.coffeetea.domain.service.FireService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class OrderFragmentPresenter extends MvpPresenter<OrderFragmentView> {
    ArrayList<ProductInBasket> arrayList;
    @Inject
    FireService fireService;
    @Inject
    SharedPreferences settings;
    @Inject
    @Local
    Router localRouter;

    public OrderFragmentPresenter() {
        App.getComponent().inject(this);
    }

    public void setRecycler() {
        String userId = settings.getString(Settings.USER_ID, "0");
        fireService.getOrders(userId, new FireService.OrdersCallback() {
            @Override
            public void onSuccess(List<Order> orders) {
                ArrayList<Order> arrayList = (ArrayList<Order>) orders;
                //Log.i("code", orders.get(0).getId()+"");
                getViewState().initAdapter(arrayList);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

}
