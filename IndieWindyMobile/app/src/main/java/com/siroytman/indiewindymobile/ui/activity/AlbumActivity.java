package com.siroytman.indiewindymobile.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.controller.AlbumController;
import com.siroytman.indiewindymobile.interfaces.ILinkAdd;
import com.siroytman.indiewindymobile.model.Album;
import com.siroytman.indiewindymobile.model.Artist;
import com.siroytman.indiewindymobile.model.UserSongLink;
import com.siroytman.indiewindymobile.services.FragmentService;
import com.siroytman.indiewindymobile.services.IconChanger;
import com.siroytman.indiewindymobile.ui.fragments.links.UserSongLinkListFragment;

import java.util.ArrayList;

import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.FragmentActivity;

public class AlbumActivity extends FragmentActivity implements ILinkAdd<Album> {
    public static final String TAG = "AlbumActivity";

    @SuppressLint("StaticFieldLeak")
    private static AlbumActivity activity;
    
    private AlbumController albumController;
    private Album album;
    private Boolean linkExist = false;

    private ImageView albumPhoto;
    private TextView albumName;
    private TextView albumArtistName;
    private ImageView albumAddButton;
    private ImageView albumOptionsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        activity = this;

        // Get album from bundle
        Bundle arguments = getIntent().getExtras();
        if(arguments != null) {
            album = arguments.getParcelable(Album.class.getSimpleName());
        }
        else {
            Log.e(TAG, "Error: Arguments are null!");
        }

        albumName = findViewById(R.id.album_activity__album_name);
        albumArtistName = findViewById(R.id.album_activity__album_artist_name);
        albumPhoto = findViewById(R.id.album_activity__album_photo);
        albumAddButton = findViewById(R.id.album_activity__album_add_button);
        albumOptionsButton = findViewById(R.id.album_activity__album_options_button);

        albumName.setText(album.getName());
        albumArtistName.setText(album.getArtist().getName());
        Glide.with(this).load(album.getImageUrl()).into(albumPhoto);

        final AlbumActivity activity = this;
        albumController = AlbumController.getInstance();
        albumController.linkExist(activity);
        albumController.getAlbumSongs(activity);

        albumAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linkExist) {
                    albumController.removeUserAlbumLink(activity);
                } else {
                    albumController.addUserAlbumLink(activity);
                }
            }
        });

        albumOptionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(activity, v, album);
            }
        });
    }

    public void songsFoundViewUpdate(ArrayList<UserSongLink> links)
    {
        // Load list fragment
        UserSongLinkListFragment fragment = new UserSongLinkListFragment(links, true, false);
        FragmentService.replaceFragment(activity, R.id.album_activity__album_song_list_container, fragment);
    }


    @Override
    public Album getItem() {
        return album;
    }

    @Override
    public void removed() {
        IconChanger.setIconAdd(albumAddButton);
        linkExist = false;
    }

    @Override
    public void added() {
        IconChanger.setIconCheck(albumAddButton);
        linkExist = true;
    }


    @SuppressLint("RestrictedApi")
    public static void showPopupMenu(final Context context, View v, final Album album) {
        PopupMenu menu = new PopupMenu(context, v);
        menu.inflate(R.menu.popup_album_menu);

        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.album_options_menu_artist:
                        // If not already in ArtistActivity
                        if (!(context instanceof ArtistActivity)) {
                            Log.d(TAG, "to artist of album: " + album.getName());
                            Intent intent = new Intent(context, ArtistActivity.class);
                            intent.putExtra(Artist.class.getSimpleName(), album.getArtist());
                            context.startActivity(intent);
                            return true;
                        }
                        return true;
                    default:
                        return false;
                }
            }
        });

        menu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
            }
        });

        MenuPopupHelper menuHelper = new MenuPopupHelper(context, (MenuBuilder) menu.getMenu(), v);
        menuHelper.setForceShowIcon(true);
        menuHelper.show();
    }
}
