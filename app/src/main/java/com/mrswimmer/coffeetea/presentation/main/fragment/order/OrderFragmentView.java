package com.mrswimmer.coffeetea.presentation.main.fragment.order;

import com.mrswimmer.coffeetea.data.model.Order;
import com.mrswimmer.coffeetea.presentation.base.BaseView;

import java.util.ArrayList;

interface OrderFragmentView extends BaseView {
    void initAdapter(ArrayList<Order> products);
    void setEmptyText();
}
