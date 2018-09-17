package com.mx.envamapa.app.wundertest.commons.di.component;

import com.mx.envamapa.app.wundertest.commons.Application;
import com.mx.envamapa.app.wundertest.commons.di.module.AppModule;
import com.mx.envamapa.app.wundertest.views.presenter.SplashPresenter;
import com.mx.envamapa.app.wundertest.views.view.SplashScreen;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(Application application);

    void inject(SplashScreen splashScreen);

    void inject(SplashPresenter splashPresenter);
}