package com.example.myapplication.presentation.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.domain.interactor.ArtistInteractor;
import com.example.myapplication.domain.model.ArtworkDomain;
import com.example.myapplication.presentation.model.ArtworkModel;
import com.example.myapplication.utils.SchedulersProvider;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;

/**
 * ViewModel экрана со списком картин
 *
 * @author Руслан Кадыров
 */
public class ArtworkViewModel extends ViewModel {

    private static final String TAG = ArtworkViewModel.class.getSimpleName();

    private final ArtistInteractor mArtistInteractor;
    private final SchedulersProvider mSchedulersProvider;

    private final MutableLiveData<List<ArtworkModel>> mArtworksLiveData = new MutableLiveData<>();
    private final MutableLiveData<Throwable> mErrorLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mProgressLiveData = new MutableLiveData<>();

    private CompositeDisposable mDisposable = new CompositeDisposable();

    /**
     * Конструктор класса
     *
     * @param artistInteractor   инстанс интерактора
     * @param schedulersProvider инстанс scheduler
     */
    public ArtworkViewModel(ArtistInteractor artistInteractor, SchedulersProvider schedulersProvider) {
        mArtistInteractor = artistInteractor;
        mSchedulersProvider = schedulersProvider;
    }

    /**
     * Метод получает данные из интерактора с учетом id художника, маппит их в список моделей презентационного слоя.
     * Затем, уже в главном потоке сеттит данные в liveData
     *
     * @param personId id художника
     */
    public void loadArtworks(int personId) {
        mDisposable.add(
                Single.fromCallable(() -> mArtistInteractor.getArtworksByArtist(personId))
                        .map(artworksDomain -> {
                            List<ArtworkModel> artworkModels = new ArrayList<>();
                            for (ArtworkDomain model : artworksDomain) {
                                artworkModels.add(new ArtworkModel(
                                        model.getPrimaryImageUrl(),
                                        model.getObjectNumber(),
                                        model.getPeople(),
                                        model.getTitle(),
                                        model.getClassification()
                                ));
                            }
                            return artworkModels;
                        })
                        .doOnSubscribe(disposable -> mProgressLiveData.postValue(true))
                        .doAfterTerminate(() -> mProgressLiveData.postValue(false))
                        .subscribeOn(mSchedulersProvider.io())
                        .observeOn(mSchedulersProvider.ui())
                        .subscribe(mArtworksLiveData::setValue, mErrorLiveData::setValue)
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
            mDisposable = null;
        }

        Log.d(TAG, "onCleared() called");
    }

    public LiveData<List<ArtworkModel>> getArtworksLiveData() {
        return mArtworksLiveData;
    }

    public LiveData<Throwable> getErrorLiveData() {
        return mErrorLiveData;
    }

    public LiveData<Boolean> getProgressLiveData() {
        return mProgressLiveData;
    }
}
