package com.example.myapplication.utils;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Реализация интерфейса [SchedulersProvider]
 *
 * @author Руслан Кадыров
 */
public class SchedulersProviderImpl implements SchedulersProvider {

    @Inject
    public SchedulersProviderImpl() {
    }

    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }
}
