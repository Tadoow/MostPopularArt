package com.example.myapplication.domain.model;

import com.example.myapplication.data.model.ArtistResponse;

import java.util.List;
import java.util.Objects;

/**
 * Модель для отображения данных о картине
 *
 * @author Руслан Кадыров
 */
public class ArtworkDomain {

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
    public ArtworkDomain(String primaryImageUrl, String objectNumber, List<ArtistResponse> people,
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtworkDomain that = (ArtworkDomain) o;
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
