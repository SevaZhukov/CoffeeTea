package com.mrswimmer.coffeetea.di.module;

import com.mrswimmer.coffeetea.domain.service.FireService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FireModule {
    @Provides
    @Singleton
    FireService providesService() {
        return new FireService();
    }
}
