package com.siroytman.indiewindymobile.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.controller.AppController;
import com.siroytman.indiewindymobile.controller.ConcertController;
import com.siroytman.indiewindymobile.interfaces.ILinkAdd;
import com.siroytman.indiewindymobile.model.Concert;
import com.siroytman.indiewindymobile.model.UserArtistLink;
import com.siroytman.indiewindymobile.model.UserConcertLink;
import com.siroytman.indiewindymobile.services.FragmentService;
import com.siroytman.indiewindymobile.services.IconChanger;
import com.siroytman.indiewindymobile.ui.fragments.links.UserArtistLinkListFragment;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class ConcertActivity extends AppCompatActivity implements ILinkAdd<Concert> {
    public static final String TAG = "ConcertActivity";

    private UserConcertLink concertLink;

    @SuppressLint("StaticFieldLeak")
    private static ConcertActivity activity;

    private ConcertController concertController;

    private ImageView concertPhoto;
    private TextView concertName;
    private TextView concertDate;
    private TextView concertDescription;
    private ImageView concertAddButton;
    private ImageView concertOptionsButton;
    private Button concertTicketButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concert);

        activity = this;

        // Get concertLink from bundle
        Bundle arguments = getIntent().getExtras();
        if(arguments != null) {
            concertLink = arguments.getParcelable(UserConcertLink.class.getSimpleName());
        }
        else {
            Log.e(TAG, "Error: Arguments are null!");
        }

        concertPhoto = findViewById(R.id.concert_activity__concert_photo);
        concertName = findViewById(R.id.concert_activity__concert_name);
        concertDate = findViewById(R.id.concert_activity__concert_date);
        concertDescription = findViewById(R.id.concert_activity__concert_description);
        concertAddButton = findViewById(R.id.concert_activity__concert_add_button);
        concertOptionsButton = findViewById(R.id.concert_activity__concert_options_button);
        concertTicketButton = findViewById(R.id.concert_activity__ticket_button);

        IconChanger.setAddStateIcon(concertLink, concertAddButton);

        final Concert concert = concertLink.getConcert();

        concertName.setText(concert.getName());
        concertDate.setText(concert.getStartTimeString());
        concertDescription.setText(concert.getDescription());
        concertDescription.setMovementMethod(new ScrollingMovementMethod());
        Glide.with(activity).load(concert.getImageUrl()).into(concertPhoto);

        concertController = ConcertController.getInstance();
        concertController.getArtists(this, concert.getId());


        concertAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!concertLink.isEmpty()) {
                    concertController.removeUserConcertLink(activity);
                } else {
                    concertController.addUserConcertLink(activity);
                }
            }
        });

        concertTicketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(concert.getTicketLink()));
                startActivity(browserIntent);
            }
        });
    }


    public void artistsFoundViewUpdate(ArrayList<UserArtistLink> links)
    {
        // Load list fragment
        UserArtistLinkListFragment fragment = new UserArtistLinkListFragment(links);
        FragmentService.replaceFragment(activity, R.id.concert_activity__artists_container, fragment);
    }

    @Override
    public Concert getItem() {
        return concertLink.getConcert();
    }

    @Override
    public void removed() {
        IconChanger.setIconAdd(concertAddButton);

        concertLink.makeEmpty();
    }

    @Override
    public void added() {
        IconChanger.setIconCheck(concertAddButton);

        concertLink.setAppUserId(AppController.user.getId());
        concertLink.setConcertId(concertLink.getConcert().getId());
    }
}
