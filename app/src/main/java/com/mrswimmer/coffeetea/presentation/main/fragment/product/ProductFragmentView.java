package com.mrswimmer.coffeetea.presentation.main.fragment.product;

import com.arellomobile.mvp.MvpView;
import com.mrswimmer.coffeetea.data.model.Product;

interface ProductFragmentView extends MvpView {
    void setProduct(Product product);

    void showChooseCountDialog(int max);
}
