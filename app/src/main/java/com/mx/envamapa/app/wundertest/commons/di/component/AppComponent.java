package com.mx.envamapa.app.wundertest.commons.di.component;

import com.mx.envamapa.app.wundertest.commons.Application;
import com.mx.envamapa.app.wundertest.commons.di.module.AppModule;
import com.mx.envamapa.app.wundertest.views.presenter.listPresenter.CarListPresenter;
import com.mx.envamapa.app.wundertest.views.presenter.splashPresenter.SplashPresenter;
import com.mx.envamapa.app.wundertest.views.view.mainActivity.list.CarListFragment;
import com.mx.envamapa.app.wundertest.views.view.splash.SplashScreen;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(Application application);

    void inject(SplashScreen splashScreen);

    void inject(SplashPresenter splashPresenter);

    void inject(CarListFragment carListFragment);

    void inject(CarListPresenter carListPresenter);
}