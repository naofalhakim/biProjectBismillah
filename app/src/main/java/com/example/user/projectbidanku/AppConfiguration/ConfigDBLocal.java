package com.example.user.projectbidanku.AppConfiguration;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by user on 10/11/2018.
 */

public class ConfigDBLocal extends Application{

        @Override
        public void onCreate() {
            super.onCreate();
            Realm.init(this);
            RealmConfiguration config = new RealmConfiguration
                    .Builder()
                    .deleteRealmIfMigrationNeeded()
                    .name("MuslimHabitt.realm")
                    .build();
            Realm.setDefaultConfiguration(config);
        }
}
