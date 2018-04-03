package com.mrswimmer.coffeetea.presentation.auth.activity;

import com.mrswimmer.coffeetea.App;
import com.mrswimmer.coffeetea.R;
import com.mrswimmer.coffeetea.presentation.base.BaseActivity;
import com.mrswimmer.coffeetea.data.settings.Screens;

public class AuthActivity extends BaseActivity {


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
