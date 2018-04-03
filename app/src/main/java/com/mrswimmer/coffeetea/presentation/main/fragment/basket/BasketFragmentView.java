package com.mrswimmer.coffeetea.presentation.main.fragment.basket;

import com.mrswimmer.coffeetea.data.model.ProductInBasket;
import com.mrswimmer.coffeetea.presentation.base.BaseView;

import java.util.ArrayList;

interface BasketFragmentView extends BaseView {
    void initAdapter(ArrayList<ProductInBasket> products, boolean orders);
    void setBottomVisible(boolean visible);
    void setSum(int sum);
    void setEmptyText();
}
