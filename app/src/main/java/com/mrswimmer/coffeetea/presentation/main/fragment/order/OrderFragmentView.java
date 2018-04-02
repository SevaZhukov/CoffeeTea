package com.mrswimmer.coffeetea.presentation.main.fragment.order;

import com.mrswimmer.coffeetea.data.model.ProductInBasket;
import com.mrswimmer.coffeetea.presentation.base.BaseView;

import java.util.ArrayList;

interface OrderFragmentView extends BaseView {
    void initAdapter(ArrayList<ProductInBasket> products);
    void setEmptyText();
}
