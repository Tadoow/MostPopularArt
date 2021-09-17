package com.example.myapplication.utils;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Реализация интерфейса [SchedulersProvider]
 *
 * @author Руслан Кадыров
 */
public class SchedulersProviderImplStub implements SchedulersProvider {

    @Override
    public Scheduler io() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler ui() {
        return Schedulers.trampoline();
    }
}
