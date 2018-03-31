package com.mrswimmer.coffeetea.presentation.main.fragment.catalog;

import com.arellomobile.mvp.MvpView;
import com.mrswimmer.coffeetea.data.model.product.Product;

import java.util.ArrayList;

interface CatalogFragmentView extends MvpView {
    void initAdapter(ArrayList<Product> products);

    void hideDropButton();

    void showDropButton();

}
