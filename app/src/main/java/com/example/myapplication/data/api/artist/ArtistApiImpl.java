package com.example.myapplication.data.api.artist;

import android.util.Log;

import com.example.myapplication.data.api.MuseumUrls;
import com.example.myapplication.data.model.ArtworkResponse;
import com.example.myapplication.data.model.ObjectResponse;
import com.example.myapplication.utils.parser.JsonParser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Класс реализующий интерфейс [ArtistApi]
 *
 * @author Руслан Кадыров
 */
public class ArtistApiImpl implements ArtistApi {

    private static final String TAG = ArtistApiImpl.class.getSimpleName();

    private final OkHttpClient mHttpClient;
    private final JsonParser mParser;

    /**
     * Конструктор класса
     *
     * @param httpClient клиент для работы с сетью
     * @param parser     json парсер
     */
    @Inject
    public ArtistApiImpl(OkHttpClient httpClient, JsonParser parser) {
        mHttpClient = httpClient;
        mParser = parser;
    }

    @Override
    public ObjectResponse getObjectResponse(int pageNumber) {
        Request request = createGetRequest(MuseumUrls.PAINTINGS.getUrl() + pageNumber);
        try (Response response = mHttpClient.newCall(request).execute()) {
            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                return body != null ? mParser.fromJsonToObject(body.string()) : null;
            } else {
                Log.d(TAG, "Response code: " + response.code());
                return null;
            }
        } catch (Exception e) {
            Log.e(TAG, "Request failed: ", e);
            return null;
        }
    }

    @Override
    public List<ArtworkResponse> getAllArtworks(int startPage) {
        List<ArtworkResponse> allArtworks = new ArrayList<>();
        ObjectResponse object = getObjectResponse(startPage);
        if (object != null) {
            allArtworks.addAll(object.getRecords());
            while (object.getInfo().getNext() != null) {
                object = getObjectResponse(++startPage);
                allArtworks.addAll(object.getRecords());
            }
        }
        return new ArrayList<>(new HashSet<>(allArtworks));
    }

    private Request createGetRequest(String url) {
        return new Request.Builder()
                .url(url)
                .get()
                .build();
    }
}
