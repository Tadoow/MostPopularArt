package com.example.myapplication.domain.interactor;

import android.util.Log;

import com.example.myapplication.data.model.ArtistResponse;
import com.example.myapplication.data.model.ArtworkResponse;
import com.example.myapplication.data.repository.ArtistRepository;
import com.example.myapplication.domain.model.ArtistDomain;
import com.example.myapplication.domain.model.ArtworkDomain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * Класс интерактора
 *
 * @author Руслан Кадыров
 */
public class ArtistInteractor {

    private static final String TAG = ArtistInteractor.class.getSimpleName();

    private final ArtistRepository mArtistRepository;

    /**
     * Конструктор класса
     *
     * @param artistRepository инстанс репозитория
     */
    @Inject
    public ArtistInteractor(ArtistRepository artistRepository) {
        mArtistRepository = artistRepository;
    }

    /**
     * Метод получает список моделей художников, не имеющих null поля,
     * а также маппит их в список моделей domain слоя
     *
     * @return список моделей художников
     */
    public List<ArtistDomain> getArtists(Boolean force) {
        List<ArtistDomain> artistsDomain = new ArrayList<>();
        try {
            List<ArtistResponse> artists = mArtistRepository.getArtists(force);
            for (ArtistResponse artist : artists) {
                if (checkEmptyFields(artist)) {
                    artistsDomain.add(new ArtistDomain(
                            artist.getDisplayName(),
                            artist.getBirthPlace(),
                            artist.getDisplayDate(),
                            artist.getDeathPlace(),
                            artist.getPersonId()));
                }
            }
            return artistsDomain;
        } catch (Exception e) {
            Log.e(TAG, "Data request failed: ", e);
            return Collections.emptyList();
        }
    }

    /**
     * Метод получает список моделей картин, по id художника,
     * а также маппит их в список моделей domain слоя
     *
     * @param personId id
     * @return список моделей картин
     */
    public List<ArtworkDomain> getArtworksByArtist(int personId) {
        List<ArtworkDomain> artworkDomain = new ArrayList<>();
        try {
            List<ArtworkResponse> artworks = mArtistRepository.getArtworks();
            for (ArtworkResponse artwork : artworks) {
                if (artwork.getPeople() != null) {
                    for (ArtistResponse artist : artwork.getPeople()) {
                        if (artist.getPersonId() == personId) {
                            artworkDomain.add(new ArtworkDomain(
                                    artwork.getPrimaryImageUrl(),
                                    artwork.getObjectNumber(),
                                    artwork.getPeople(),
                                    artwork.getTitle(),
                                    artwork.getClassification()));
                            break;
                        }
                    }
                }
            }
            return artworkDomain;
        } catch (Exception e) {
            Log.e(TAG, "Data request failed: ", e);
            return Collections.emptyList();
        }
    }

    private boolean checkEmptyFields(ArtistResponse artist) {
        return artist.getBirthPlace() != null && artist.getDeathPlace() != null &&
                artist.getDisplayDate() != null && artist.getDisplayName() != null;
    }
}
