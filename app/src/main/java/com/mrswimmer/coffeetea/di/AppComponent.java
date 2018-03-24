package com.mrswimmer.coffeetea.di;

import com.mrswimmer.coffeetea.di.module.FireModule;
import com.mrswimmer.coffeetea.di.module.NavigatorModule;
import com.mrswimmer.coffeetea.di.module.SharedPreferencesModule;
import com.mrswimmer.coffeetea.presentation.auth.activity.AuthActivity;
import com.mrswimmer.coffeetea.presentation.auth.fragment.sign_in.SignInFragmentPresenter;
import com.mrswimmer.coffeetea.presentation.main.MainActivity;
import com.mrswimmer.coffeetea.presentation.splash.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {SharedPreferencesModule.class, NavigatorModule.class, FireModule.class})
public interface AppComponent {
    void inject(SplashActivity splashActivity);
    void inject(MainActivity mainActivity);

    void inject(AuthActivity authActivity);

    void inject(SignInFragmentPresenter signInFragmentPresenter);
}
