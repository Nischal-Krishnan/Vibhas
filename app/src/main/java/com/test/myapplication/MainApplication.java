package com.test.myapplication;

import android.app.Application;
import android.arch.core.BuildConfig;
import timber.log.Timber;

public class MainApplication extends Application {
    @Override
    public void onCreate(){
        super.onCreate ();

        if (BuildConfig.DEBUG){
            Timber.plant ( new Timber.DebugTree () );
        }
    }
}
