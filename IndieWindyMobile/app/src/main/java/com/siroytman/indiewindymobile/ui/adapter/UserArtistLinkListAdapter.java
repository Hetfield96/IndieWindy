package com.siroytman.indiewindymobile.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.controller.AppController;
import com.siroytman.indiewindymobile.controller.ArtistController;
import com.siroytman.indiewindymobile.interfaces.ILinkActions;
import com.siroytman.indiewindymobile.model.Artist;
import com.siroytman.indiewindymobile.model.UserArtistLink;
import com.siroytman.indiewindymobile.ui.activity.ArtistActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class UserArtistLinkListAdapter extends ArrayAdapter<UserArtistLink> {
    public static final String TAG = "UserArtistLinkListAdapter";
    private ArtistController artistController;
    private Context context;
    private List<UserArtistLink> linksList;

    public UserArtistLinkListAdapter(@NonNull Context context, int resource, @NonNull List<UserArtistLink> linksList) {
        super(context, resource, linksList);
        this.context = context;
        this.linksList = linksList;
        this.artistController = ArtistController.getInstance();
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        final UserArtistLink artistLink = linksList.get(position);

        if(convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            assert inflater != null;
            convertView = inflater.inflate(R.layout.artist_list_item, null);

            viewHolder = new ViewHolder(convertView, artistLink);
            convertView.setTag(viewHolder);

        }
        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        return convertView;
    }


    // Single element
    public class ViewHolder  implements View.OnClickListener, ILinkActions<Artist> {
        public static final String TAG = "UserArtistLinkAdapterVH";
        private UserArtistLink artistLink;

        private ImageView artistPhotoView;
        private TextView artistNameView;
        private ImageView artistAddButton;
        private ImageView artistOptionsButton;

        public ViewHolder(View convertView, final UserArtistLink artistLink) {
            this.artistLink = artistLink;

            artistPhotoView = convertView.findViewById(R.id.artist_list_item__artist_photo);
            artistNameView = convertView.findViewById(R.id.artist_list_item__artist_name);
            artistAddButton = convertView.findViewById(R.id.artist_list_item__add_button);
            artistOptionsButton = convertView.findViewById(R.id.artist_list_item__options_button);

            Artist artist = artistLink.getArtist();
            artistNameView.setText(artist.getName());
            Glide.with(convertView).load(artist.getImageUrl()).into(artistPhotoView);


            if(artistLink.isEmpty()){
                artistSetIconAdd();
            } else {
                artistSetIconCheck();
            }

            convertView.setOnClickListener(this);
            artistAddButton.setOnClickListener(this);
            artistOptionsButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Artist artist = artistLink.getArtist();

            Log.d(TAG, "onClick artist: " + artist.getName());

            switch (v.getId()) {
                case R.id.artist_list_item__add_button:
                    Log.d(TAG, "onClick artist_add_button: " + artist.getName());
                    if (artistLink.isEmpty()) {
                        artistController.addUserArtistLink(this);
                    } else{
                        artistController.removeUserArtistLink(this);
                    }
                    return;
                case R.id.artist_list_item__options_button:
                    Log.d(TAG, "onClick artist_options_button: " + artist.getName());
                    // TODO no popup menu for now
//                    ArtistActivity.showPopupMenu(getContext(), v, artist);
                    return;
            }


            Log.d(TAG, "to page of artist: " + artist.getName());
            Intent intent = new Intent(context, ArtistActivity.class);
            intent.putExtra(Artist.class.getSimpleName(), artist);
            context.startActivity(intent);
        }

        private void artistSetIconCheck() {
            artistAddButton.setImageResource(R.drawable.ic_check);
        }

        private void artistSetIconAdd() {
            artistAddButton.setImageResource(R.drawable.ic_add);
        }

        @Override
        public Artist getItem() {
            return artistLink.getArtist();
        }

        @Override
        public void removed() {
            artistLink.makeEmpty();

            artistSetIconAdd();
        }

        @Override
        public void added() {
            artistLink.setAppUserId(AppController.user.getId());
            artistLink.setArtistId(artistLink.getArtist().getId());

            artistSetIconCheck();
        }
    }
}
