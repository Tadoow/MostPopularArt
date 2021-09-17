package com.example.myapplication.data.api.artist;

import com.example.myapplication.data.model.ArtworkResponse;
import com.example.myapplication.data.model.ObjectResponse;

import java.util.List;

/**
 * Api для работы с данными музея
 *
 * @author Руслан Кадыров
 */
public interface ArtistApi {

    /**
     * Метод для получения данных о картинах, на указанной странице ответа
     *
     * @param pageNumber номер страницы
     * @return модель ответа сервера
     */
    ObjectResponse getObjectResponse(int pageNumber);

    /**
     * Метод для получения данных обо всех картинах, начиная с указанной страницы ответа
     *
     * @param startPage номер страницы
     * @return список моделей картин
     */
    List<ArtworkResponse> getAllArtworks(int startPage);

}
