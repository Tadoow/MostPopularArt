package com.example.myapplication.presentation.view.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;

import com.example.myapplication.ArtistApplication;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentFirstBinding;
import com.example.myapplication.di.fragments.FragmentsComponent;
import com.example.myapplication.presentation.view.ViewModelFactory;
import com.example.myapplication.presentation.view.adapter.ArtistsAdapter;
import com.example.myapplication.presentation.view.adapter.ItemClickListener;
import com.example.myapplication.presentation.viewmodel.ArtistViewModel;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

/**
 * Фрагмент первого экрана приложения, показывает список художников
 *
 * @author Руслан Кадыров
 */
public class ArtistFragment extends Fragment {

    private static final String TAG = ArtistFragment.class.getSimpleName();

    private ArtistViewModel mViewModel;
    private FragmentFirstBinding mBinding;
    private ArtistsAdapter mAdapter;
    @Inject
    ViewModelFactory mViewModelFactory;

    /**
     * Статический метод для создания инстанса данного фрагмента
     *
     * @return экземпляр фрагмента
     */
    public static Fragment newInstance() {
        return new ArtistFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        provideDependencies();
        createViewModel();

        if (savedInstanceState == null) {
            mViewModel.loadArtist(false);
        }
    }

    private void provideDependencies() {
        FragmentsComponent fragmentsComponent = ArtistApplication.getAppComponent(requireContext())
                .getFragmentsComponent();
        fragmentsComponent.inject(this);
    }

    private void createViewModel() {
        mViewModel = new ViewModelProvider(requireActivity(), mViewModelFactory)
                .get(ArtistViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = FragmentFirstBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        observeLiveData();
        mBinding.swipeRefreshLayout.setOnRefreshListener(() -> {
            mViewModel.loadArtist(true);
        });
    }

    private void observeLiveData() {
        mViewModel.getProgressLiveData().observe(getViewLifecycleOwner(), aBoolean ->
                mBinding.progressBarFirst.setVisibility(aBoolean ? View.VISIBLE : View.GONE));
        mViewModel.getErrorLiveData().observe(getViewLifecycleOwner(), throwable ->
                Snackbar.make(mBinding.getRoot(), throwable.toString(), BaseTransientBottomBar.LENGTH_LONG).show());
        mViewModel.getArtistsLiveData().observe(getViewLifecycleOwner(), artistModels -> {
            mAdapter = new ArtistsAdapter(artistModels, (ItemClickListener) getActivity(), getItemRowsCount());
            mBinding.recyclerViewFirst.setAdapter(mAdapter);
            mBinding.swipeRefreshLayout.setRefreshing(false);
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.artist_fragment_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search_action);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private int getItemRowsCount() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        return Integer.parseInt(sharedPreferences.getString(getString(R.string.rows_count), "0"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBinding = null;
    }

}
