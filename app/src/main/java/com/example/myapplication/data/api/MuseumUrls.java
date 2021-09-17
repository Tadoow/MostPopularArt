package com.example.myapplication.data.api;

/**
 * Класс хранилище, используемых адресов
 *
 * @author Руслан Кадыров
 */
public enum MuseumUrls {

    PAINTINGS("https://api.harvardartmuseums.org/object?" +
            "q=totalpageviews:%3E500&" +
            "classification=Paintings&" +
            "sort=random&" +
            "size=100&apikey=3a41b391-1992-4765-983e-2d21741057f3&page="),
    DRAWINGS("https://api.harvardartmuseums.org/object?" +
            "q=totalpageviews:%3E500&" +
            "classification=Drawings&" +
            "sort=random&" +
            "size=100&apikey=3a41b391-1992-4765-983e-2d21741057f3&page=");

    private final String mUrl;

    /**
     * Конструктор класса
     *
     * @param url адрес
     */
    MuseumUrls(String url) {
        mUrl = url;
    }

    public String getUrl() {
        return mUrl;
    }
}
