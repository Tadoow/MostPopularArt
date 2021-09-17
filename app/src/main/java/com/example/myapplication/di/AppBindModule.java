package com.example.myapplication.di;

import com.example.myapplication.data.api.artist.ArtistApi;
import com.example.myapplication.data.api.artist.ArtistApiImpl;
import com.example.myapplication.data.repository.ArtistRepository;
import com.example.myapplication.data.repository.ArtistRepositoryImpl;
import com.example.myapplication.utils.parser.JsonParser;
import com.example.myapplication.utils.parser.JsonParserImpl;

import dagger.Binds;
import dagger.Module;

@Module
public interface AppBindModule {

    @Binds
    ArtistRepository bindsArtistRepositoryImpl_to_ArtistRepository(ArtistRepositoryImpl artistRepository);

    @Binds
    ArtistApi bindsArtistApiImpl_to_ArtistApi(ArtistApiImpl artistApi);

    @Binds
    JsonParser bindsJsonParserImpl_to_JsonParser(JsonParserImpl parser);
}
