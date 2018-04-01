package com.mrswimmer.coffeetea.presentation.main.fragment.basket;

import com.arellomobile.mvp.MvpView;
import com.mrswimmer.coffeetea.data.model.ProductInBasket;
import com.mrswimmer.coffeetea.presentation.base.BaseView;

import java.util.ArrayList;
import java.util.List;

interface BasketFragmentView extends BaseView {
    void initAdapter(ArrayList<ProductInBasket> products);
}
