package com.example.myapplication.data.store;

import android.content.SharedPreferences;

import com.example.myapplication.data.model.ArtworkResponse;
import com.example.myapplication.data.model.InfoResponse;
import com.example.myapplication.data.model.ObjectResponse;
import com.example.myapplication.utils.parser.JsonParser;

import java.io.IOException;
import java.util.List;

/**
 * Реализация интерфейса [ArtistStore]
 *
 * @author Руслан Кадыров
 */
public class ArtistStoreImpl implements ArtistStore {

    private static final String PAINTINGS_KEY = "PAINTINGS_KEY";

    private final SharedPreferences mPreferences;
    private final JsonParser mParser;

    /**
     * Конструктор класса
     *
     * @param preferences инстанс хранилища
     * @param parser      json парсер
     */
    public ArtistStoreImpl(SharedPreferences preferences, JsonParser parser) {
        mPreferences = preferences;
        mParser = parser;
    }

    @Override
    public void saveArtworks(List<ArtworkResponse> artworks, InfoResponse info) {
        if (!artworks.isEmpty()) {
            mPreferences.edit()
                    .putString(PAINTINGS_KEY, mParser.toJson(new ObjectResponse(artworks, info)))
                    .apply();
        }
    }

    @Override
    public List<ArtworkResponse> getSavedArtworks() throws IOException {
        String cache = mPreferences.getString(PAINTINGS_KEY, null);
        return cache != null ? mParser.fromJsonToObject(cache).getRecords() : null;
    }

    @Override
    public void clearStore() {
        mPreferences.edit()
                .clear()
                .apply();
    }
}
