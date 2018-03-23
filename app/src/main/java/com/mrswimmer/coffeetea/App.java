package com.mrswimmer.coffeetea;

import android.app.Application;

import com.mrswimmer.coffeetea.di.AppComponent;
import com.mrswimmer.coffeetea.di.DaggerAppComponent;
import com.mrswimmer.coffeetea.di.module.SharedPreferencesModule;
//add dev branch
public class App extends Application {
    private static AppComponent component;

    public static AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder()
                .sharedPreferencesModule(new SharedPreferencesModule(getApplicationContext()))
                .build();
    }
}
