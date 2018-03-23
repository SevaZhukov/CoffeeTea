package com.mrswimmer.coffeetea.di.module;

import com.mrswimmer.coffeetea.di.qualifier.Global;
import com.mrswimmer.coffeetea.di.qualifier.Local;

import dagger.Module;
import dagger.Provides;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

@Module
public class NavigatorModule {
    private Cicerone<Router> localCicerone;
    private Cicerone<Router> globalCicerone;

    public NavigatorModule() {
        localCicerone = Cicerone.create();
        globalCicerone = Cicerone.create();
    }

    @Provides
    @Local
    public Router provideLocalRouter() {
        return localCicerone.getRouter();
    }

    @Provides
    @Global
    public Router provideGlobalRouter() {
        return globalCicerone.getRouter();
    }

    @Provides
    @Local
    public NavigatorHolder provideLocalNavigatorHolder() {
        return localCicerone.getNavigatorHolder();
    }

    @Provides
    @Global
    public NavigatorHolder provideGlobalNavigatorHolder() {
        return globalCicerone.getNavigatorHolder();
    }
}
