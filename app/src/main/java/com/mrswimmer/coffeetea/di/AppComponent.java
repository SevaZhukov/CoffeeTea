package com.mrswimmer.coffeetea.di;

import com.mrswimmer.coffeetea.di.module.FireModule;
import com.mrswimmer.coffeetea.di.module.NavigatorModule;
import com.mrswimmer.coffeetea.di.module.SharedPreferencesModule;
import com.mrswimmer.coffeetea.presentation.MainActivity;
import com.mrswimmer.coffeetea.presentation.splash.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {SharedPreferencesModule.class, NavigatorModule.class, FireModule.class})
public interface AppComponent {
    void inject(SplashActivity splashActivity);
    void inject(MainActivity mainActivity);
}
