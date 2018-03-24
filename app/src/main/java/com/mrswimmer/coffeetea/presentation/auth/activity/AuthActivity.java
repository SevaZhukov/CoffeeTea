package com.mrswimmer.coffeetea.presentation.auth.activity;

import com.mrswimmer.coffeetea.App;
import com.mrswimmer.coffeetea.R;
import com.mrswimmer.coffeetea.data.base.BaseActivity;
import com.mrswimmer.coffeetea.data.settings.Screens;
import com.mrswimmer.coffeetea.di.qualifier.Local;

import javax.inject.Inject;

import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

public class AuthActivity extends BaseActivity {

    @Inject
    @Local
    NavigatorHolder mNavigatorHolder;

    @Inject
    @Local
    Router router;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_auth;
    }

    @Override
    protected int getContainerId() {
        return Screens.CONTAINER_AUTH;
    }

    @Override
    protected void injectDependencies() {
        App.getComponent().inject(this);
    }

}
