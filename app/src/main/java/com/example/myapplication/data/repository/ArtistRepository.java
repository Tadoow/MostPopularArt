package com.example.myapplication.data.repository;

import com.example.myapplication.data.model.ArtistResponse;
import com.example.myapplication.data.model.ArtworkResponse;

import java.io.IOException;
import java.util.List;

/**
 * Интерфейс репозитория данных
 *
 * @author Руслан Кадыров
 */
public interface ArtistRepository {

    /**
     * Метод получает список моделей художников
     *
     * @return список моделей художников
     * @throws IOException ошибка получения
     */
    List<ArtistResponse> getArtists(Boolean force) throws IOException;

    /**
     * Метод получает список моделей картин
     *
     * @return список моделей картин
     * @throws IOException ошибка получения
     */
    List<ArtworkResponse> getArtworks() throws IOException;

    List<ArtworkResponse> getArtworksForce();
}
