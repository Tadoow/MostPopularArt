package com.example.myapplication.data.model;

import com.squareup.moshi.Json;

import java.util.List;
import java.util.Objects;

/**
 * Модель для отображения данных базового ответа сервера
 *
 * @author Руслан Кадыров
 */
public class ObjectResponse {

    @Json(name = "records")
    private final List<ArtworkResponse> mRecords;
    @Json(name = "info")
    private final InfoResponse mInfo;

    /**
     * Конструктор класса
     *
     * @param records список моделей картин
     * @param info    модель поля info
     */
    public ObjectResponse(List<ArtworkResponse> records, InfoResponse info) {
        mInfo = info;
        mRecords = records;
    }

    public List<ArtworkResponse> getRecords() {
        return mRecords;
    }

    public InfoResponse getInfo() {
        return mInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectResponse that = (ObjectResponse) o;
        return Objects.equals(mRecords, that.mRecords) &&
                Objects.equals(mInfo, that.mInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mRecords, mInfo);
    }
}
