package com.example.myapplication.presentation.view.adapter;

import com.example.myapplication.presentation.model.ArtistModel;
import com.example.myapplication.presentation.model.ArtworkModel;

/**
 * Интерфейс для обработки клика по элементу списка
 *
 * @author Руслан Кадыров
 */
public interface ItemClickListener {

    /**
     * Метод-обработчик клика по элементу из списка моделей художников
     *
     * @param artistModel модель художника, по которому был совершен клик
     */
    void onArtistClickListener(ArtistModel artistModel);

    /**
     * Метод-обработчик клика по элементу из списка моделей картин
     *
     * @param artworkModel модель картины, по которой был совершен клик
     */
    void onArtworkClickListener(ArtworkModel artworkModel);
}
