package com.example.myapplication.di;

import com.example.myapplication.di.fragments.FragmentsComponent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    FragmentsComponent getFragmentsComponent();

}
