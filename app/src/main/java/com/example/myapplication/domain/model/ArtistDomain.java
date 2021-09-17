package com.example.myapplication.domain.model;

import java.util.Objects;

/**
 * Модель для отображения данных о художнике
 *
 * @author Руслан Кадыров
 */
public class ArtistDomain {

    private final String mDisplayName;
    private final String mBirthPlace;
    private final String mDisplayDate;
    private final String mDeathPlace;
    private final int mPersonId;

    /**
     * Конструктор класса
     *
     * @param displayName имя автора
     * @param birthPlace  место рождения
     * @param displayDate годы жизни
     * @param deathPlace  место смерти
     * @param personId    id автора
     */
    public ArtistDomain(String displayName, String birthPlace, String displayDate,
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
        ArtistDomain that = (ArtistDomain) o;
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
