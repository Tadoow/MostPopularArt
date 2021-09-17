package com.example.myapplication.data.store;

import com.example.myapplication.data.model.ArtworkResponse;
import com.example.myapplication.data.model.InfoResponse;

import java.io.IOException;
import java.util.List;

/**
 * Интерфейс для работы с сохраненными данными
 *
 * @author Руслан Кадыров
 */
public interface ArtistStore {

    /**
     * Метод сохраняет, полученные данные о картинах
     *
     * @param info     модель поля info
     * @param artworks список моделей картин
     */
    void saveArtworks(List<ArtworkResponse> artworks, InfoResponse info);

    /**
     * Метод получает данные о картинах из кэша
     *
     * @return список моделей картин
     * @throws IOException ошибка парсинга
     */
    List<ArtworkResponse> getSavedArtworks() throws IOException;

    void clearStore();
}
