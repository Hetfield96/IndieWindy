package com.siroytman.indiewindymobile.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.controller.DonationController;
import com.siroytman.indiewindymobile.model.Artist;

public class DonationActivity extends AppCompatActivity {
    public static final String TAG = "ArtistActivity";

    private DonationController donationController;
    private Artist artist;

    private TextView artistName;
    private ImageView artistPhoto;
    private EditText donationAmount;
    private Button donationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);

        // Get artist from bundle
        Bundle arguments = getIntent().getExtras();
        if(arguments != null) {
            artist = arguments.getParcelable(Artist.class.getSimpleName());
        }
        else {
            Log.e(TAG, "Error: Arguments are null!");
        }

        donationController = DonationController.getInstance();

        artistName = findViewById(R.id.donation_activity_artist_name);
        artistPhoto = findViewById(R.id.donation_activity_artist_image);
        donationAmount = findViewById(R.id.donation_activity_amount);
        donationButton = findViewById(R.id.donation_activity_donation_button);

        artistName.setText(artist.getName());
        Glide.with(this).load(artist.getImageUrl()).into(artistPhoto);

        donationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int amount = Integer.parseInt(donationAmount.getText().toString());
                donationController.addDonation(artist, amount);
            }
        });
    }
}
