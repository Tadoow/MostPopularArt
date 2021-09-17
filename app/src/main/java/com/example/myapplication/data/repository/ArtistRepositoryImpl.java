package com.example.myapplication.data.repository;

import com.example.myapplication.data.api.artist.ArtistApi;
import com.example.myapplication.data.model.ArtistResponse;
import com.example.myapplication.data.model.ArtworkResponse;
import com.example.myapplication.data.store.ArtistStore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Реализация репозитория, провайдит данные из сети при первом запуске, далее из кэша
 *
 * @author Руслан Кадыров
 */
@Singleton
public class ArtistRepositoryImpl implements ArtistRepository {

    private static final String TAG = ArtistRepositoryImpl.class.getSimpleName();

    private final ArtistApi mArtistApi;
    private final ArtistStore mArtistStore;

    /**
     * Конструктор репозитория
     *
     * @param artistApi   инстанс класса для получения данных по сети
     * @param artistStore инстанс класса для получения данных из кэша
     */
    @Inject
    public ArtistRepositoryImpl(ArtistApi artistApi, ArtistStore artistStore) {
        mArtistApi = artistApi;
        mArtistStore = artistStore;
    }

    @Override
    public List<ArtistResponse> getArtists(Boolean force) throws IOException {
        List<ArtworkResponse> artworks = force ? getArtworksForce() : getArtworks();
        List<ArtistResponse> artists = new ArrayList<>();
        for (ArtworkResponse art : artworks) {
            if (art.getPeople() != null) {
                for (ArtistResponse artist : art.getPeople()) {
                    if (!artists.contains(artist)) {
                        artists.add(artist);
                    }
                }
            }
        }
        return artists;
    }

    @Override
    public List<ArtworkResponse> getArtworks() throws IOException {
        if (mArtistStore.getSavedArtworks() == null) {
            mArtistStore.saveArtworks(mArtistApi.getAllArtworks(1),
                    mArtistApi.getObjectResponse(1).getInfo());
            return mArtistStore.getSavedArtworks();
        }
        return mArtistStore.getSavedArtworks();
    }

    @Override
    public List<ArtworkResponse> getArtworksForce() {
        mArtistStore.clearStore();
        List<ArtworkResponse> artWorks = mArtistApi.getAllArtworks(1);
        mArtistStore.saveArtworks(artWorks,
                mArtistApi.getObjectResponse(1).getInfo());
        return artWorks;
    }
}
