package com.siroytman.indiewindymobile.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.offline.DownloadService;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.controller.AppController;
import com.siroytman.indiewindymobile.controller.SongController;
import com.siroytman.indiewindymobile.interfaces.ILinkAdd;
import com.siroytman.indiewindymobile.model.Album;
import com.siroytman.indiewindymobile.model.Artist;
import com.siroytman.indiewindymobile.model.Song;
import com.siroytman.indiewindymobile.model.UserSongLink;
import com.siroytman.indiewindymobile.services.DownloadTracker;
import com.siroytman.indiewindymobile.services.IconChanger;
import com.siroytman.indiewindymobile.services.PlayerForegroundService;
import com.siroytman.indiewindymobile.services.PlayerServiceConnection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.content.ContextCompat;

public class PlayerActivity extends AppCompatActivity implements ILinkAdd<Song>, DownloadTracker.Listener {
    public static final String TAG = "PlayerActivity";

    private AppController appController;
    private SongController songController;
    public PlayerView playerView;
    private UserSongLink songLink;

    private ImageView songArtwork;
    private ImageView optionsButton;
    private ImageView addButton;
    private ImageView downloadButton;

    private DownloadTracker downloadTracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        // Get album from bundle
        Bundle arguments = getIntent().getExtras();
        if(arguments != null) {
            songLink = arguments.getParcelable(UserSongLink.class.getSimpleName());
        }
        else {
            Log.e(TAG, "Error: Arguments are null!");
        }

        appController = AppController.getInstance();
        songController = SongController.getInstance();

        playerView = findViewById(R.id.player_view);
        songArtwork = playerView.findViewById(R.id.artwork);
        optionsButton = playerView.findViewById(R.id.player_options_button);
        addButton = playerView.findViewById(R.id.player_add_button);
        downloadButton = playerView.findViewById(R.id.player_download_button);

        downloadTracker = appController.getDownloadTracker();
        Glide.with(this).load(songLink.getSong().getAlbum().getImageUrl()).into(songArtwork);
        IconChanger.setAddStateIcon(songLink, addButton);

        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(PlayerActivity.this, optionsButton, songLink.getSong());
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (songLink.isEmpty()) {
                    songController.addUserSongLink(PlayerActivity.this);
                } else{
                    songController.removeUserSongLink(PlayerActivity.this);
                }
            }
        });

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Song song = songLink.getSong();
                Uri uri = Uri.parse(song.getSongUrl());
                downloadTracker.toggleDownload(
                        getSupportFragmentManager(),
                        song.getName(),
                        uri,
                        "extension",
                        null);
            }
        });

        PlayerServiceConnection.createInstance(playerView);
        PlayerForegroundService.startService(this, songLink.getSong());

        // Start the download service if it should be running but it's not currently.
        // Starting the service in the foreground causes notification flicker if there is no scheduled
        // action. Starting it in the background throws an exception if the app is in the background too
        // (e.g. if device screen is locked).
        try {
            DownloadService.start(this, DownloadService.class);
        } catch (IllegalStateException e) {
            DownloadService.startForeground(this, DownloadService.class);
        }
    }

    @Override
    public void onDownloadsChanged() {
        // TODO
    }


    @Override
    public void onStart() {
        super.onStart();
        downloadTracker.addListener(this);
    }

    @Override
    public void onStop() {
        downloadTracker.removeListener(this);
        super.onStop();
    }


    @Override
    public Song getItem() {
        return songLink.getSong();
    }

    @Override
    public void removed() {
        songLink.makeEmpty();

        IconChanger.setIconAdd(addButton);
    }

    @Override
    public void added() {
        songLink.setAppUserId(AppController.user.getId());
        songLink.setSongId(songLink.getSong().getId());

        IconChanger.setIconCheck(addButton);
    }

    @SuppressLint("RestrictedApi")
    public static void showPopupMenu(final Context context, View v, final Song song) {
        PopupMenu menu = new PopupMenu(context, v);
        menu.inflate(R.menu.popup_song_menu);

        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.song_options_menu_artist:
                        // If not already in ArtistActivity
                        if (!(context instanceof ArtistActivity)) {
                            Log.d(TAG, "to artist of song: " + song.getName());
                            Intent intent = new Intent(context, ArtistActivity.class);
                            intent.putExtra(Artist.class.getSimpleName(), song.getArtist());
                            context.startActivity(intent);
                            return true;
                        }
                        return true;
                    case R.id.song_options_menu_album:
                        // If not already in AlbumActivity
                        if (!(context instanceof AlbumActivity)) {
                            Log.d(TAG, "to album of song: " + song.getName());
                            Intent intent = new Intent(context, AlbumActivity.class);
                            intent.putExtra(Album.class.getSimpleName(), song.getAlbum());
                            intent.putExtra(Artist.class.getSimpleName(), song.getArtist());
                            context.startActivity(intent);
                            return true;
                        }
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

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUi();
        }
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }
}
