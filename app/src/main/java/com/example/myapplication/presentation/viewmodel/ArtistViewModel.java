package com.example.myapplication.presentation.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.domain.interactor.ArtistInteractor;
import com.example.myapplication.domain.model.ArtistDomain;
import com.example.myapplication.presentation.model.ArtistModel;
import com.example.myapplication.utils.SchedulersProvider;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;

/**
 * ViewModel экрана со списком художников
 *
 * @author Руслан Кадыров
 */
public class ArtistViewModel extends ViewModel {

    private static final String TAG = ArtistViewModel.class.getSimpleName();

    private final ArtistInteractor mArtistInteractor;
    private final SchedulersProvider mSchedulersProvider;

    private final MutableLiveData<List<ArtistModel>> mArtistsLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mProgressLiveData = new MutableLiveData<>();
    private final MutableLiveData<Throwable> mErrorLiveData = new MutableLiveData<>();

    private CompositeDisposable mDisposable = new CompositeDisposable();

    /**
     * Конструктор класса
     *
     * @param artistInteractor   инстанс интерактора
     * @param schedulersProvider инстанс scheduler
     */
    public ArtistViewModel(ArtistInteractor artistInteractor, SchedulersProvider schedulersProvider) {
        mArtistInteractor = artistInteractor;
        mSchedulersProvider = schedulersProvider;
    }

    /**
     * Метод получает данные из интерактора, маппит их в список моделей презентационного слоя.
     * Затем, уже в главном потоке сеттит данные в liveData
     */
    public void loadArtist(Boolean force) {
        mDisposable.add(
                Single.fromCallable(() -> mArtistInteractor.getArtists(force))
                        .map(artistDomain -> {
                            List<ArtistModel> artistModels = new ArrayList<>();
                            for (ArtistDomain a : artistDomain) {
                                artistModels.add(new ArtistModel(a.getDisplayName(),
                                        a.getBirthPlace(),
                                        a.getDisplayDate(),
                                        a.getDeathPlace(),
                                        a.getPersonId()));
                            }
                            return artistModels;
                        })
                        .doOnSubscribe(disposable -> mProgressLiveData.postValue(!force))
                        .doAfterTerminate(() -> mProgressLiveData.postValue(false))
                        .subscribeOn(mSchedulersProvider.io())
                        .observeOn(mSchedulersProvider.ui())
                        .subscribe(mArtistsLiveData::setValue, mErrorLiveData::setValue)
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

    public LiveData<List<ArtistModel>> getArtistsLiveData() {
        return mArtistsLiveData;
    }

    public LiveData<Boolean> getProgressLiveData() {
        return mProgressLiveData;
    }

    public LiveData<Throwable> getErrorLiveData() {
        return mErrorLiveData;
    }
}
