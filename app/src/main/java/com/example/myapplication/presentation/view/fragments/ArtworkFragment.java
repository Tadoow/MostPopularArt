package com.example.myapplication.presentation.view.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.myapplication.ArtistApplication;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentSecondBinding;
import com.example.myapplication.di.fragments.FragmentsComponent;
import com.example.myapplication.presentation.model.ArtistModel;
import com.example.myapplication.presentation.view.ViewModelFactory;
import com.example.myapplication.presentation.view.adapter.ArtworkAdapter;
import com.example.myapplication.presentation.view.adapter.ItemClickListener;
import com.example.myapplication.presentation.viewmodel.ArtworkViewModel;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

/**
 * Фрагмент второго экрана приложения, показывает список картин выбранного художника
 *
 * @author Руслан Кадыров
 */
public class ArtworkFragment extends Fragment {

    private static final String KEY_ARTIST = "ARTIST";

    private ArtworkViewModel mViewModel;
    private FragmentSecondBinding mBinding;
    @Inject
    ViewModelFactory mViewModelFactory;

    /**
     * Статический метод для создания инстанса данного фрагмента
     *
     * @return экземпляр фрагмента
     */
    public static Fragment newInstance() {
        return new ArtworkFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        provideDependencies();
        createViewModel();

        if (savedInstanceState == null) {
            ArtistModel mArtistModel = (ArtistModel) requireArguments().getSerializable(KEY_ARTIST);
            mViewModel.loadArtworks(mArtistModel.getPersonId());
        }
    }

    private void provideDependencies() {
        FragmentsComponent fragmentsComponent = ArtistApplication.getAppComponent(requireContext())
                .getFragmentsComponent();
        fragmentsComponent.inject(this);
    }

    private void createViewModel() {
        mViewModel = new ViewModelProvider(this, mViewModelFactory)
                .get(ArtworkViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = FragmentSecondBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        observeLiveData();
    }

    private void observeLiveData() {
        mViewModel.getArtworksLiveData().observe(getViewLifecycleOwner(), artworkModels -> {
            ArtworkAdapter adapter = new ArtworkAdapter(artworkModels, (ItemClickListener) getActivity());
            mBinding.recyclerViewSecond.setAdapter(adapter);
            mBinding.recyclerViewSecond.setLayoutManager(new StaggeredGridLayoutManager(getItemColumnsCount(), StaggeredGridLayoutManager.VERTICAL));
        });
        mViewModel.getProgressLiveData().observe(getViewLifecycleOwner(), aBoolean ->
                mBinding.progressBarSecond.setVisibility(aBoolean ? View.VISIBLE : View.GONE));
        mViewModel.getErrorLiveData().observe(getViewLifecycleOwner(), throwable ->
                Snackbar.make(mBinding.getRoot(), throwable.toString(), BaseTransientBottomBar.LENGTH_LONG).show());
    }

    public int getItemColumnsCount() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        return Integer.parseInt(sharedPreferences.getString(getString(R.string.columns_count), "2"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBinding = null;
    }
}
