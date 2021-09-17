package com.example.myapplication.presentation.view.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentThirdBinding;
import com.example.myapplication.presentation.model.ArtworkModel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.jetbrains.annotations.NotNull;

/**
 * Фрагмент третьего экрана приложения, показывает выбранную картину на весь экран
 *
 * @author Руслан Кадыров
 */
public class ImageFragment extends Fragment {

    private static final String KEY_ARTWORK = "ARTWORK";

    private FragmentThirdBinding mBinding;
    private ArtworkModel artWorkModel;

    /**
     * Статический метод для создания инстанса данного фрагмента
     *
     * @return экземпляр фрагмента
     */
    public static ImageFragment newInstance() {
        return new ImageFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        mBinding = FragmentThirdBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.artwork_fragment_menu, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share_item:
                Log.d("TAG", "onOptionsItemSelected: Clicked");
                Intent myShareIntent = new Intent(Intent.ACTION_SEND);
                myShareIntent.setType("image/*");
                myShareIntent.putExtra(Intent.EXTRA_STREAM, artWorkModel.getPrimaryImageUrl());
                startActivity(Intent.createChooser(myShareIntent, "Share artwork"));
                return true;
            case R.id.add_to_favourites_item:
                Toast.makeText(getContext(), "Image added to favourites", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        artWorkModel = (ArtworkModel) requireArguments().getSerializable(KEY_ARTWORK);
        Picasso.get()
                .load(artWorkModel.getPrimaryImageUrl())
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        mBinding.fullscreenImageView.setImage(ImageSource.bitmap(bitmap));
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                    }
                });
        mBinding.artworkNumber.setText(artWorkModel.getObjectNumber());
        mBinding.artworkAuthor.setText(artWorkModel.getArtistName());
        mBinding.artworkTitle.setText(artWorkModel.getTitle());
        mBinding.artworkClassification.setText(artWorkModel.getClassification());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBinding = null;
    }
}
