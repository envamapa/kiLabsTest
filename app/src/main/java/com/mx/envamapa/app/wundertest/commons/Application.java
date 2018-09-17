package com.mx.envamapa.app.wundertest.commons;

import com.mx.envamapa.app.wundertest.commons.di.component.AppComponent;
import com.mx.envamapa.app.wundertest.commons.di.component.DaggerAppComponent;
import com.mx.envamapa.app.wundertest.commons.di.module.AppModule;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Application extends android.app.Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        Realm realm;
        try{
            realm = Realm.getDefaultInstance();

        }catch (Exception e){
            Utils.printLogError(e.getMessage(), true, false);
            // Get a Realm instance for this thread
            RealmConfiguration config = new RealmConfiguration.Builder()
                    .deleteRealmIfMigrationNeeded()
                    .build();
            realm = Realm.getInstance(config);

        }
        realm.close();
        createDaggerInjections();
        Logger.addLogAdapter(new AndroidLogAdapter());

    }

    /**
     * CREAR EL MODULO DE INYECCION DE DAGGER2
     */
    private void createDaggerInjections(){
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        mAppComponent.inject(this);

    }

    public AppComponent getAppComponent(){
        return this.mAppComponent;
    }
}
