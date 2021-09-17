package com.example.myapplication.data.model;

import com.squareup.moshi.Json;

import java.util.Objects;

/**
 * Модель для отображения данных о художнике
 *
 * @author Руслан Кадыров
 */
public class ArtistResponse {

    @Json(name = "displayname")
    private final String mDisplayName;
    @Json(name = "birthplace")
    private final String mBirthPlace;
    @Json(name = "displaydate")
    private final String mDisplayDate;
    @Json(name = "deathplace")
    private final String mDeathPlace;
    @Json(name = "personid")
    private final int mPersonId;

    /**
     * Конструктор модели
     *
     * @param displayName имя автора
     * @param birthPlace  место рождения
     * @param displayDate годы жизни
     * @param deathPlace  место смерти
     * @param personId    id автора
     */
    public ArtistResponse(String displayName, String birthPlace, String displayDate,
                          String deathPlace, int personId) {
        mDisplayName = displayName;
        mBirthPlace = birthPlace;
        mDisplayDate = displayDate;
        mDeathPlace = deathPlace;
        mPersonId = personId;
    }

    public String getDisplayName() {
        return mDisplayName;
    }

    public String getBirthPlace() {
        return mBirthPlace;
    }

    public String getDisplayDate() {
        return mDisplayDate;
    }

    public String getDeathPlace() {
        return mDeathPlace;
    }

    public int getPersonId() {
        return mPersonId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistResponse that = (ArtistResponse) o;
        return mPersonId == that.mPersonId &&
                Objects.equals(mDisplayName, that.mDisplayName) &&
                Objects.equals(mBirthPlace, that.mBirthPlace) &&
                Objects.equals(mDisplayDate, that.mDisplayDate) &&
                Objects.equals(mDeathPlace, that.mDeathPlace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mDisplayName, mBirthPlace, mDisplayDate, mDeathPlace, mPersonId);
    }
}
