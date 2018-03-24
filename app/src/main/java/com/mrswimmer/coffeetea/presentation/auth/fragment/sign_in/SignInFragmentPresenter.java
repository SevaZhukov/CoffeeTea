package com.mrswimmer.coffeetea.presentation.auth.fragment.sign_in;

import android.content.SharedPreferences;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.mrswimmer.coffeetea.App;
import com.mrswimmer.coffeetea.data.settings.Screens;
import com.mrswimmer.coffeetea.di.qualifier.Global;
import com.mrswimmer.coffeetea.di.qualifier.Local;

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

@InjectViewState
public class SignInFragmentPresenter extends MvpPresenter<SignInFragmentView> {
    @Inject
    @Local
    Router router;

    @Inject
    @Global
    Router globalRouter;

    @Inject
    SharedPreferences settings;

    public SignInFragmentPresenter() {
        App.getComponent().inject(this);
    }

    public void enter(String email, String pass) {

    }

    public void gotoReg() {
        router.navigateTo(Screens.SIGN_UP_SCREEN);
    }
}
