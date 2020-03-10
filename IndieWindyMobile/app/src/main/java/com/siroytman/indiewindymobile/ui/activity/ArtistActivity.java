package com.siroytman.indiewindymobile.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.controller.ArtistController;
import com.siroytman.indiewindymobile.model.Artist;

import androidx.appcompat.app.AppCompatActivity;

public class ArtistActivity extends AppCompatActivity {
    public static final String TAG = "ArtistActivity";

    private ArtistController artistController;
    private Artist artist;

    private ImageView artistPhoto;
    private TextView artistName;
    private TextView artistDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);

        // Get artist from bundle
        Bundle arguments = getIntent().getExtras();
        if(arguments != null) {
            artist = arguments.getParcelable(Artist.class.getSimpleName());
        }
        else {
            Log.e(TAG, "Error: Arguments are null!");
        }

        artistPhoto = findViewById(R.id.artist_photo);
        artistName = findViewById(R.id.artist_name);
        artistDescription = findViewById(R.id.artist_description);

        artistName.setText(artist.getName());
        artistDescription.setText(artist.getDescription());
        Glide.with(this).load(artist.getImageUrl()).into(artistPhoto);
    }
}
