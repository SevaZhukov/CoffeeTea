package com.mrswimmer.coffeetea.presentation.main.activity;

import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mrswimmer.coffeetea.App;
import com.mrswimmer.coffeetea.R;
import com.mrswimmer.coffeetea.data.settings.Screens;
import com.mrswimmer.coffeetea.di.qualifier.Global;
import com.mrswimmer.coffeetea.di.qualifier.Local;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MainActivityView> {
    public MainActivityPresenter() {
        App.getComponent().inject(this);
    }
    @Inject
    @Local
    Router router;

    @Inject
    @Global
    Router globalRouter;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        router.newRootScreen(Screens.CATALOG_SCREEN);
    }

    public void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                menuItem -> {
                    selectDrawerItem(menuItem);
                    return true;
                });
    }

    private void selectDrawerItem(MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case R.id.nav_catalog_fragment:
                router.replaceScreen(Screens.CATALOG_SCREEN);
                break;
            case R.id.nav_sale_fragment:
                router.replaceScreen(Screens.SALE_SCREEN);
                break;
            case R.id.nav_basket_fragment:
                router.replaceScreen(Screens.BASKET_SCREEN);
                break;
            case R.id.nav_shops_fragment:
                router.replaceScreen(Screens.SHOP_SCREEN);
                break;
            case R.id.nav_orders_fragment:
                router.replaceScreen(Screens.ORDERS_SCREEN);
                break;
            case R.id.nav_settings_fragment:
                router.replaceScreen(Screens.SETTINGS_SCREEN);
                break;
            default:
        }
        menuItem.setChecked(true);
        getViewState().checkDrawerItem(menuItem);
    }

    public void share() {
        globalRouter.navigateTo(Screens.SHARE);
    }
}
