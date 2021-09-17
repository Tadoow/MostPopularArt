package com.example.myapplication.presentation.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.domain.interactor.ArtistInteractor;
import com.example.myapplication.presentation.viewmodel.ArtistViewModel;
import com.example.myapplication.presentation.viewmodel.ArtworkViewModel;
import com.example.myapplication.utils.SchedulersProvider;

import javax.inject.Inject;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final ArtistInteractor mArtistInteractor;
    private final SchedulersProvider mSchedulersProvider;

    @Inject
    public ViewModelFactory(ArtistInteractor artistInteractor, SchedulersProvider schedulersProvider) {
        mArtistInteractor = artistInteractor;
        mSchedulersProvider = schedulersProvider;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (ArtistViewModel.class.equals(modelClass)) {
            return (T) new ArtistViewModel(mArtistInteractor, mSchedulersProvider);
        } else {
            return (T) new ArtworkViewModel(mArtistInteractor, mSchedulersProvider);
        }
    }
}
