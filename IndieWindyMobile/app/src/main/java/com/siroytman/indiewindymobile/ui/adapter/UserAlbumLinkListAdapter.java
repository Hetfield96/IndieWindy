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
import com.siroytman.indiewindymobile.controller.AlbumController;
import com.siroytman.indiewindymobile.controller.AppController;
import com.siroytman.indiewindymobile.interfaces.ILinkActions;
import com.siroytman.indiewindymobile.model.Album;
import com.siroytman.indiewindymobile.model.Artist;
import com.siroytman.indiewindymobile.model.UserAlbumLink;
import com.siroytman.indiewindymobile.ui.activity.AlbumActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class UserAlbumLinkListAdapter extends ArrayAdapter<UserAlbumLink> {
    public static final String TAG = "UserAlbumLinkListAdapter";
    private AlbumController albumController;
    private Context context;
    private List<UserAlbumLink> linksList;
    private Artist artist;

    public UserAlbumLinkListAdapter(@NonNull Context context, int resource, @NonNull List<UserAlbumLink> linksList) {
        super(context, resource, linksList);
        this.context = context;
        this.linksList = linksList;
        this.albumController = AlbumController.getInstance();
    }

    public UserAlbumLinkListAdapter(@NonNull Context context, int resource, @NonNull List<UserAlbumLink> linksList, Artist artist) {
        super(context, resource, linksList);
        this.context = context;
        this.linksList = linksList;
        this.artist = artist;
        this.albumController = AlbumController.getInstance();
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        final UserAlbumLink albumLink = linksList.get(position);

        if(convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            assert inflater != null;
            convertView = inflater.inflate(R.layout.album_list_item, null);

            if (artist != null){
                viewHolder = new ViewHolder(convertView, albumLink, artist);
            } else {
                viewHolder = new ViewHolder(convertView, albumLink);
            }

            convertView.setTag(viewHolder);

        }
        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        return convertView;
    }


    // Single element
    public class ViewHolder  implements View.OnClickListener, ILinkActions<Album> {
        public static final String TAG = "UserAlbumLinkAdapter.VH";
        private UserAlbumLink albumLink;

        private ImageView albumPhotoView;
        private TextView albumNameView;
        private TextView albumArtistNameView;
        private ImageView albumAddButton;
        private ImageView albumOptionsButton;

        public ViewHolder(View convertView, final UserAlbumLink albumLink) {
            this.albumLink = albumLink;

            albumPhotoView = convertView.findViewById(R.id.album_list_item__album_photo);
            albumNameView = convertView.findViewById(R.id.album_list_item__album_name);
            albumArtistNameView = convertView.findViewById(R.id.album_list_item__album_artist_name);
            albumAddButton = convertView.findViewById(R.id.album_list_item__album_add_button);
            albumOptionsButton = convertView.findViewById(R.id.album_list_item__album_options_button);

            Album album = albumLink.getAlbum();
            albumNameView.setText(album.getName());
            albumArtistNameView.setText(album.getArtist().getName());
            Glide.with(convertView).load(album.getImageUrl()).into(albumPhotoView);


            if(albumLink.isEmpty()){
                albumSetIconAdd();
            } else {
                albumSetIconCheck();
            }

            convertView.setOnClickListener(this);
            albumAddButton.setOnClickListener(this);
            albumOptionsButton.setOnClickListener(this);
        }

        public ViewHolder(View convertView, final UserAlbumLink albumLink, final Artist artist) {
            this.albumLink = albumLink;

            albumPhotoView = convertView.findViewById(R.id.album_list_item__album_photo);
            albumNameView = convertView.findViewById(R.id.album_list_item__album_name);
            albumArtistNameView = convertView.findViewById(R.id.album_list_item__album_artist_name);
            albumAddButton = convertView.findViewById(R.id.album_list_item__album_add_button);
            albumOptionsButton = convertView.findViewById(R.id.album_list_item__album_options_button);

            albumLink.getAlbum().setArtist(artist);
            Album album = albumLink.getAlbum();
            albumNameView.setText(album.getName());
            albumArtistNameView.setText(album.getArtist().getName());
            Glide.with(convertView).load(album.getImageUrl()).into(albumPhotoView);

            if(albumLink.isEmpty()){
                albumSetIconAdd();
            } else {
                albumSetIconCheck();
            }

            convertView.setOnClickListener(this);
            albumAddButton.setOnClickListener(this);
            albumOptionsButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Album album = albumLink.getAlbum();

            Log.d(TAG, "onClick album: " + album.getName());

            switch (v.getId()) {
                case R.id.album_list_item__album_add_button:
                    Log.d(TAG, "onClick album_add_button: " + album.getName());
                    if (albumLink.isEmpty()) {
                        albumController.addUserAlbumLink(this);
                    } else{
                        albumController.removeUserAlbumLink(this);
                    }
                    return;
                case R.id.album_list_item__album_options_button:
                    Log.d(TAG, "onClick album_options_button: " + album.getName());
                    AlbumActivity.showPopupMenu(getContext(), v, album);
                    return;
            }


            Log.d(TAG, "to page of album: " + album.getName());
            Intent intent = new Intent(context, AlbumActivity.class);
            intent.putExtra(Album.class.getSimpleName(), album);
            intent.putExtra(Artist.class.getSimpleName(), album.getArtist());
            context.startActivity(intent);
        }

        private void albumSetIconCheck() {
            albumAddButton.setImageResource(R.drawable.ic_check);
        }

        private void albumSetIconAdd() {
            albumAddButton.setImageResource(R.drawable.ic_add);
        }

        @Override
        public Album getItem() {
            return albumLink.getAlbum();
        }

        @Override
        public void removed() {
            albumLink.makeEmpty();

            albumSetIconAdd();
        }

        @Override
        public void added() {
            albumLink.setAppUserId(AppController.user.getId());
            albumLink.setAlbumId(albumLink.getAlbum().getId());

            albumSetIconCheck();
        }
    }
}
