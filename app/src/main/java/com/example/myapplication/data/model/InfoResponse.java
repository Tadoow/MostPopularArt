package com.example.myapplication.data.model;

import com.squareup.moshi.Json;

import java.util.Objects;

/**
 * Модель данных поля info в ответе сервера
 *
 * @author Руслан Кадыров
 */
public class InfoResponse {

    @Json(name = "next")
    private final String mNext;

    /**
     * Конструктор класса
     *
     * @param next поле содержащее ссылку на следующую страницу ответа
     */
    public InfoResponse(String next) {
        mNext = next;
    }

    public String getNext() {
        return mNext;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfoResponse that = (InfoResponse) o;
        return Objects.equals(mNext, that.mNext);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mNext);
    }
}
