package com.example.myapplication.presentation.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ArtistItemBinding;
import com.example.myapplication.presentation.model.ArtistModel;

/**
 * Класс viewHolder для отображения данных о художнике
 *
 * @author Руслан Кадыров
 */
public class ArtistModelViewHolder extends RecyclerView.ViewHolder {

    private final ArtistItemBinding mBinding;

    private ArtistModelViewHolder(@NonNull View itemView) {
        super(itemView);
        mBinding = ArtistItemBinding.bind(itemView);
    }

    /**
     * Метод сеттит во вью, данные модели художника
     *
     * @param artistModel модель художника
     */
    public void bindView(ArtistModel artistModel) {
        mBinding.artistNameTextView.setText(artistModel.getDisplayName());
        mBinding.artistBirthplaceTextView.setText(artistModel.getBirthPlace());
        mBinding.artistDateTextView.setText(artistModel.getDisplayDate());
        mBinding.artistDeathplaceTextView.setText(artistModel.getDeathPlace());
    }

    /**
     * Метод создает инстанс данного viewHolder-а
     *
     * @param container родительская view
     * @return экземпляр viewHolder-а
     */
    public static ArtistModelViewHolder create(ViewGroup container) {
        return new ArtistModelViewHolder(
                LayoutInflater.from(container.getContext())
                        .inflate(R.layout.artist_item, container, false));
    }
}
