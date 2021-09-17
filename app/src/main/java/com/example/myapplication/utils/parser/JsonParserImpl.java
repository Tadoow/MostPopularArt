package com.example.myapplication.utils.parser;

import com.example.myapplication.data.model.ObjectResponse;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import javax.inject.Inject;

/**
 * Реализация интерфейса [JsonParser]
 *
 * @author Руслан Кадыров
 */
public class JsonParserImpl implements JsonParser {

    @Inject
    public JsonParserImpl() {
    }

    @Override
    public ObjectResponse fromJsonToObject(String json) throws IOException {
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<ObjectResponse> adapter = moshi.adapter(ObjectResponse.class);
        return adapter.fromJson(json);
    }

    @Override
    public String toJson(ObjectResponse object) {
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<ObjectResponse> adapter = moshi.adapter(ObjectResponse.class);
        return adapter.toJson(object);
    }
}
