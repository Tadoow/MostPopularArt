package com.example.myapplication.di;

import android.content.SharedPreferences;

import com.example.myapplication.data.store.ArtistStore;
import com.example.myapplication.data.store.ArtistStoreImpl;
import com.example.myapplication.utils.parser.JsonParser;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Module(includes = AppBindModule.class)
public class AppModule {

    private final SharedPreferences mPreferences;

    public AppModule(SharedPreferences preferences) {
        mPreferences = preferences;
    }

    @Provides
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .readTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .addNetworkInterceptor(new HttpLoggingInterceptor())
                .build();
    }

    @Provides
    ArtistStore provideArtistStore(JsonParser parser) {
        return new ArtistStoreImpl(mPreferences, parser);
    }
}
