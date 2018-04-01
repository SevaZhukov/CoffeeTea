package com.mrswimmer.coffeetea.presentation.main.fragment.shop;

import com.mrswimmer.coffeetea.presentation.base.BaseView;
import com.mrswimmer.coffeetea.data.model.Shop;

import java.util.ArrayList;

interface ShopFragmentView extends BaseView {
    void initAdapter(ArrayList<Shop> shops, boolean choose);
}
