package com.mrswimmer.coffeetea.presentation.main.fragment.product;

import com.arellomobile.mvp.MvpView;
import com.mrswimmer.coffeetea.data.model.product.Product;

interface ProductFragmentView extends MvpView {
    void setProduct(Product product);
}
