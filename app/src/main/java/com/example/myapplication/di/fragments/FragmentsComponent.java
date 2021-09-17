package com.example.myapplication.di.fragments;

import com.example.myapplication.presentation.view.fragments.ArtistFragment;
import com.example.myapplication.presentation.view.fragments.ArtworkFragment;
import com.example.myapplication.utils.SchedulersProvider;
import com.example.myapplication.utils.SchedulersProviderImpl;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;

@FragmentsScope
@Subcomponent(modules = FragmentsModule.class)
public interface FragmentsComponent {

    void inject(ArtistFragment artistFragment);

    void inject(ArtworkFragment artworkFragment);
}

@Module
interface FragmentsModule {

    @Binds
    SchedulersProvider bindsSchedulersProviderImpl_to_SchedulersProvider(SchedulersProviderImpl schedulersProvider);
}
