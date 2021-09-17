package com.example.myapplication.utils.parser;

import com.example.myapplication.data.model.ObjectResponse;

import java.io.IOException;

/**
 * Интерфейс для сериализации / десериализации данных
 *
 * @author Руслан Кадыров
 */
public interface JsonParser {

    /**
     * Метод десериализует данные из json в ObjectResponse модель
     *
     * @param json строка json
     * @return модель ObjectResponse
     * @throws IOException ошибка парсинга
     */
    ObjectResponse fromJsonToObject(String json) throws IOException;

    /**
     * Метод сериализует данные из ObjectResponse модель в json
     *
     * @param object модель ObjectResponse
     * @return строка json
     */
    String toJson(ObjectResponse object);
}
