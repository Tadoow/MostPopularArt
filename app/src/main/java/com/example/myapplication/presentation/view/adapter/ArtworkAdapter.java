package com.example.myapplication.presentation.view.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.presentation.model.ArtworkModel;

import java.util.List;

/**
 * Адаптер для отображения элементов из списка моделей картин
 *
 * @author Руслан Кадыров
 */
public class ArtworkAdapter extends RecyclerView.Adapter<ArtWorkViewHolder> {

    private final List<ArtworkModel> mArtWorks;
    private final ItemClickListener mListener;

    /**
     * Конструктор класса
     *
     * @param artWorks список моделей картин
     * @param listener слушатель клика по элементу
     */
    public ArtworkAdapter(List<ArtworkModel> artWorks, ItemClickListener listener) {
        mListener = listener;
        mArtWorks = artWorks;
    }

    @NonNull
    @Override
    public ArtWorkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ArtWorkViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtWorkViewHolder holder, int position) {
        holder.bindView(mArtWorks.get(position));
        holder.itemView.setOnClickListener(v -> mListener.onArtworkClickListener(mArtWorks.get(position)));
    }

    @Override
    public int getItemCount() {
        return mArtWorks != null ? mArtWorks.size() : 0;
    }
}
