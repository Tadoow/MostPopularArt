package com.example.myapplication.presentation.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ArtworkItemBinding;
import com.example.myapplication.presentation.model.ArtworkModel;
import com.squareup.picasso.Picasso;

/**
 * Класс viewHolder для отображения данных о картине
 *
 * @author Руслан Кадыров
 */
public class ArtWorkViewHolder extends RecyclerView.ViewHolder {

    private final ArtworkItemBinding mBinding;

    private ArtWorkViewHolder(@NonNull View itemView) {
        super(itemView);
        mBinding = ArtworkItemBinding.bind(itemView);
    }

    /**
     * Метод сеттит во вью, данные модели картины, скачивая при этом само изображение
     *
     * @param artWorkModel модель картины
     */
    public void bindView(ArtworkModel artWorkModel) {
        Picasso.get()
                .load(artWorkModel.getPrimaryImageUrl())
                .into(mBinding.artworkImageView);
    }

    /**
     * Метод создает инстанс данного viewHolder-а
     *
     * @param container родительская view
     * @return экземпляр viewHolder-а
     */
    public static ArtWorkViewHolder create(ViewGroup container) {
        return new ArtWorkViewHolder(
                LayoutInflater.from(container.getContext())
                        .inflate(R.layout.artwork_item, container, false));
    }
}
