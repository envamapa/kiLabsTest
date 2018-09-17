package com.mx.envamapa.app.wundertest.data.sources;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by enya on 17/09/18.
 */

public class MyRealm {

    public static boolean existDB() {
        boolean result;
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("dbWunderTest")
                .schemaVersion(1) // Must be bumped when the schema changes
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        Realm realm = Realm.getInstance(config);
        if (realm.isEmpty()) {
            result = false;
        } else {
            result = true;
        }Realm.getDefaultInstance();
        realm.close();

        return result;
    }
}
