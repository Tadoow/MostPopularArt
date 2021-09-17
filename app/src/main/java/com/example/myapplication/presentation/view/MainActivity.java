package com.example.myapplication.presentation.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.example.myapplication.R;
import com.example.myapplication.presentation.model.ArtistModel;
import com.example.myapplication.presentation.model.ArtworkModel;
import com.example.myapplication.presentation.view.adapter.ItemClickListener;
import com.example.myapplication.presentation.view.fragments.ArtistFragment;
import com.example.myapplication.presentation.view.fragments.ArtworkFragment;
import com.example.myapplication.presentation.view.fragments.ImageFragment;
import com.example.myapplication.presentation.view.settings.SettingsFragment;

/**
 * Активити приложения. Работает с фрагментами и реализует обработчик кликов
 *
 * @author Руслан Кадыров
 */
public class MainActivity extends AppCompatActivity implements ItemClickListener {

    private static final String KEY_ARTIST = "ARTIST";
    private static final String KEY_ARTWORK = "ARTWORK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String title = getIntent().getStringExtra("title");
        setTitle(title);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, ArtistFragment.newInstance())
                    .addToBackStack(null)
                    .commit();
        }

        PreferenceManager.setDefaultValues(this, R.xml.settings_fragment, true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.settings_menu) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, SettingsFragment.newInstance())
                    .addToBackStack(null)
                    .commit();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onArtistClickListener(ArtistModel artistModel) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_ARTIST, artistModel);
        Fragment artworkFragment = ArtworkFragment.newInstance();
        artworkFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, artworkFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onArtworkClickListener(ArtworkModel artworkModel) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_ARTWORK, artworkModel);
        Fragment imageFragment = ImageFragment.newInstance();
        imageFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, imageFragment)
                .addToBackStack(null)
                .commit();
    }
}
