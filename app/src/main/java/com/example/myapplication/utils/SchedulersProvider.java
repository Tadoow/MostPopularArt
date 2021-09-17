package com.example.myapplication.utils;

import io.reactivex.Scheduler;

/**
 * Интерфейс для работы с потоками
 *
 * @author Руслан Кадыров
 */
public interface SchedulersProvider {

    /**
     * Получает инстанс io scheduler
     *
     * @return инстанс scheduler
     */
    Scheduler io();

    /**
     * Получает инстанс ui scheduler
     *
     * @return инстанс scheduler
     */
    Scheduler ui();
}
