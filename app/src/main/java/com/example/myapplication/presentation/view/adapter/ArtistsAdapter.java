package com.example.myapplication.presentation.view.adapter;

import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.presentation.model.ArtistModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Адаптер для отображения элементов из списка моделей художников
 *
 * @author Руслан Кадыров
 */
public class ArtistsAdapter extends RecyclerView.Adapter<ArtistModelViewHolder> implements Filterable {

    private final List<ArtistModel> mArtists;
    private final List<ArtistModel> mArtistsFull;
    private final ItemClickListener mListener;
    private final int mDisplayItems;

    /**
     * Конструктор класса
     *
     * @param artists  список моделей художников
     * @param listener слушатель клика по элементу
     */
    public ArtistsAdapter(List<ArtistModel> artists, ItemClickListener listener, int displayItems) {
        mArtists = artists;
        mListener = listener;
        mDisplayItems = displayItems;
        mArtistsFull = new ArrayList<>(mArtists);
    }

    @NonNull
    @Override
    public ArtistModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ArtistModelViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistModelViewHolder holder, int position) {
        holder.bindView(mArtists.get(position));
        holder.itemView.setOnClickListener(v -> mListener.onArtistClickListener(mArtists.get(position)));

    }

    @Override
    public int getItemCount() {
        return mArtists != null ? Math.min(mArtists.size(), mDisplayItems) : 0;
    }

    @Override
    public Filter getFilter() {
        return artistsFilter;
    }

    private Filter artistsFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ArtistModel> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mArtistsFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (ArtistModel artist : mArtistsFull) {
                    if (artist.getDisplayName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(artist);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mArtists.clear();
            mArtists.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
