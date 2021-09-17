package com.example.myapplication.presentation.model;

import com.example.myapplication.data.model.ArtistResponse;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Модель для отображения данных о картине
 *
 * @author Руслан Кадыров
 */
public class ArtworkModel implements Serializable {

    private final String mPrimaryImageUrl;
    private final String mObjectNumber;
    private final List<ArtistResponse> mPeople;
    private final String mTitle;
    private final String mClassification;

    /**
     * Конструктор класса
     *
     * @param primaryImageUrl изображение
     * @param objectNumber    номер
     * @param people          автор
     * @param title           название
     * @param classification  тип
     */
    public ArtworkModel(String primaryImageUrl, String objectNumber, List<ArtistResponse> people,
                        String title, String classification) {
        mPrimaryImageUrl = primaryImageUrl;
        mObjectNumber = objectNumber;
        mPeople = people;
        mTitle = title;
        mClassification = classification;
    }

    public String getPrimaryImageUrl() {
        return mPrimaryImageUrl;
    }

    public String getObjectNumber() {
        return mObjectNumber;
    }

    public List<ArtistResponse> getPeople() {
        return mPeople;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getClassification() {
        return mClassification;
    }

    /**
     * Метод склеивает имена художников в одну строку, из поля people
     *
     * @return строка с именами всех художников, работавших над картиной
     */
    public String getArtistName() {
        StringBuilder artist = new StringBuilder(getPeople().get(0).getDisplayName());
        if (getPeople().size() > 1) {
            for (int i = 1; i < getPeople().size(); i++) {
                artist.append(", ").append(getPeople().get(i).getDisplayName());
            }
        }
        return artist.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtworkModel that = (ArtworkModel) o;
        return Objects.equals(mPrimaryImageUrl, that.mPrimaryImageUrl) &&
                Objects.equals(mObjectNumber, that.mObjectNumber) &&
                Objects.equals(mPeople, that.mPeople) &&
                Objects.equals(mTitle, that.mTitle) &&
                Objects.equals(mClassification, that.mClassification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mPrimaryImageUrl, mObjectNumber, mPeople, mTitle, mClassification);
    }
}
