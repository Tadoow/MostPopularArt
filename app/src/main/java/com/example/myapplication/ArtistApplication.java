package com.example.myapplication;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.myapplication.di.AppComponent;
import com.example.myapplication.di.AppModule;
import com.example.myapplication.di.DaggerAppComponent;

public class ArtistApplication extends Application {

    private AppComponent mAppComponent;

    public static AppComponent getAppComponent(Context context) {
        return ((ArtistApplication) context.getApplicationContext()).mAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreferences mPreferences = getSharedPreferences("data_cache", Context.MODE_PRIVATE);

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(mPreferences))
                .build();
    }
}
