package com.mrswimmer.coffeetea.domain.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.mrswimmer.coffeetea.data.settings.Screens;
import com.mrswimmer.coffeetea.presentation.auth.fragment.sign_in.SignInFragment;
import com.mrswimmer.coffeetea.presentation.auth.fragment.sign_up.SignUpFragment;
import com.mrswimmer.coffeetea.presentation.base.BaseFragment;
import com.mrswimmer.coffeetea.presentation.main.fragment.basket.BasketFragment;
import com.mrswimmer.coffeetea.presentation.main.fragment.catalog.CatalogFragment;
import com.mrswimmer.coffeetea.presentation.main.fragment.filter.FilterFragment;
import com.mrswimmer.coffeetea.presentation.main.fragment.order.OrderFragment;
import com.mrswimmer.coffeetea.presentation.main.fragment.product.ProductFragment;
import com.mrswimmer.coffeetea.presentation.main.fragment.review.ReviewFragment;
import com.mrswimmer.coffeetea.presentation.main.fragment.shop.ShopFragment;

import java.util.ArrayList;

import ru.terrakok.cicerone.android.SupportFragmentNavigator;

public class LocalNavigator extends SupportFragmentNavigator {

    int currentContainer;

    public LocalNavigator(FragmentManager fragmentManager, int containerId) {
        super(fragmentManager, containerId);
        currentContainer = containerId;
    }

    @Override
    protected Fragment createFragment(String screenKey, Object data) {
        switch (currentContainer) {
            case Screens.CONTAINER_MAIN:
                return mainFragments(screenKey, data);
            case Screens.CONTAINER_AUTH:
                return authFragments(screenKey);
            default:
                return authFragments(screenKey);
        }
    }

    private Fragment mainFragments(String screenKey, Object data) {
        switch (screenKey) {
            case Screens.CATALOG_SCREEN:
                return new CatalogFragment();
            case Screens.SALE_SCREEN:
                CatalogFragment catalogFragment = new CatalogFragment();
                Bundle bundleSale = new Bundle();
                bundleSale.putBoolean("sale", true);
                catalogFragment.setArguments(bundleSale);
                return catalogFragment;
            case Screens.BASKET_SCREEN:
                return new BasketFragment();
            case Screens.FILTERS_SCREEN:
                return new FilterFragment();
            case Screens.PRODUCT_SCREEN:
                ProductFragment productFragment = new ProductFragment();
                Bundle bundle = new Bundle();
                bundle.putString("id", String.valueOf(data));
                productFragment.setArguments(bundle);
                return productFragment;
            case Screens.REVIEWS_SCREEN:
                ReviewFragment reviewFragment = new ReviewFragment();
                Bundle reviewBundle = new Bundle();
                reviewBundle.putString("id", String.valueOf(data));
                reviewFragment.setArguments(reviewBundle);
                return reviewFragment;
            case Screens.REVIEWS_SCREEN_FOR_SHOP:
                ReviewFragment reviewShopFragment = new ReviewFragment();
                Bundle reviewShopBundle = new Bundle();
                reviewShopBundle.putString("id", String.valueOf(data));
                reviewShopBundle.putBoolean("shop", true);
                reviewShopFragment.setArguments(reviewShopBundle);
                return reviewShopFragment;
            case Screens.SHOP_SCREEN:
                return new ShopFragment();
            case Screens.SHOP_SCREEN_CHOOSE:
                ShopFragment shopFragment = new ShopFragment();
                Bundle chooseShopBundle = new Bundle();
                //chooseShopBundle.putStringArrayList("ids", (ArrayList<String>) data);
                chooseShopBundle.putBoolean("choose", true);
                shopFragment.setArguments(chooseShopBundle);
                return shopFragment;
            case Screens.ORDERS_SCREEN:
                return new OrderFragment();
            case Screens.BASKET_OF_ORDER_SCREEN:
                Bundle booBundle = new Bundle();
                booBundle.putString("id", (String) data);
                BasketFragment basketFragment = new BasketFragment();
                basketFragment.setArguments(booBundle);
                return basketFragment;
            default:
                return new CatalogFragment();
        }
    }

    private Fragment authFragments(String screenKey) {
        switch (screenKey) {
            case Screens.SIGN_IN_SCREEN:
                return new SignInFragment();
            case Screens.SIGN_UP_SCREEN:
                return new SignUpFragment();
            default:
                return new SignInFragment();
        }
    }

    @Override
    protected void showSystemMessage(String message) {
    }

    @Override
    protected void exit() {
    }
}


