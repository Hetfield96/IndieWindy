package com.siroytman.indiewindymobile.ui.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.controller.AppController;
import com.siroytman.indiewindymobile.controller.MediaController;
import com.siroytman.indiewindymobile.controller.SongController;
import com.siroytman.indiewindymobile.model.Album;
import com.siroytman.indiewindymobile.model.Artist;
import com.siroytman.indiewindymobile.model.Song;
import com.siroytman.indiewindymobile.model.UserSongLink;
import com.siroytman.indiewindymobile.ui.activity.AlbumActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.widget.PopupMenu;

public class UserSongLinkListAdapter extends ArrayAdapter<UserSongLink> {
    public static final String TAG = "UserSongLinkListAdapter";
    private MediaController mediaController;
    private SongController songController;
    private Context context;
    private List<UserSongLink> linksList;

    public UserSongLinkListAdapter(@NonNull Context context, int resource, @NonNull List<UserSongLink> linksList) {
        super(context, resource, linksList);
        this.context = context;
        this.linksList = linksList;
        songController = SongController.getInstance();
        mediaController = MediaController.getInstance();
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        final UserSongLink songLink = linksList.get(position);

        if(convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            assert inflater != null;
            convertView = inflater.inflate(R.layout.song_list_item, null);

            viewHolder = new ViewHolder(convertView, songLink);

            convertView.setTag(viewHolder);

        }
        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.songNameView.setText(songLink.getSong().getName());
        viewHolder.songArtistNameView.setText(songLink.getSong().getArtist().getName());

        return convertView;
    }


    // Single element
    public class ViewHolder  implements View.OnClickListener {
        public static final String TAG = "UserSongLinkAdapter.VH";
        private UserSongLink songLink;

        private TextView songNameView;
        private TextView songArtistNameView;
        private ImageView songAddButton;
        private ImageView songOptionsButton;

        public ViewHolder(View convertView, final UserSongLink songLink) {
            this.songLink = songLink;

            songNameView = convertView.findViewById(R.id.song_name);
            songArtistNameView = convertView.findViewById(R.id.artist_name);
            songAddButton = convertView.findViewById(R.id.song_add_button);
            songOptionsButton = convertView.findViewById(R.id.song_options_button);

            if(songLink.isEmpty()){
                songSetIconAdd();
            } else {
                songSetIconCheck();
            }

            convertView.setOnClickListener(this);
            songAddButton.setOnClickListener(this);
            songOptionsButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Song song = songLink.getSong();

            Log.d(TAG, "onClick song: " + song.getName());

            switch (v.getId()) {
                case R.id.song_add_button:
                    Log.d(TAG, "onClick song_add_button: " + song.getName());

                    if (songLink.isEmpty()) {
                        songController.addUserSongLink(this);
                    } else{
                        songController.removeUserSongLink(this);
                    }
                    return;
                case R.id.song_options_button:
                    Log.d(TAG, "onClick song_options_button: " + song.getName());
                    showPopupMenu(v);
                    return;

            }

            mediaController.prepareOrStartPause(song);
        }

        @SuppressLint("RestrictedApi")
        private void showPopupMenu(View v) {
            PopupMenu menu = new PopupMenu(context, v);
            menu.inflate(R.menu.popup_song_menu);

            menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.song_options_menu_artist:
                                    Log.d(TAG, "to artist of song: " + songLink.getSong().getName());
                                    return true;
                                case R.id.song_options_menu_album:
                                    // If not already in AlbumActivity
                                    if (!(context instanceof AlbumActivity)) {
                                        Log.d(TAG, "to album of song: " + songLink.getSong().getName());
                                        Intent intent = new Intent(context, AlbumActivity.class);
                                        // TODO if we are already in this album page?
                                        intent.putExtra(Album.class.getSimpleName(), songLink.getSong().getAlbum());
                                        intent.putExtra(Artist.class.getSimpleName(), songLink.getSong().getArtist());
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

            MenuPopupHelper menuHelper = new MenuPopupHelper(getContext(), (MenuBuilder) menu.getMenu(), v);
            menuHelper.setForceShowIcon(true);
            menuHelper.show();
        }

        public void songAdded(){
            songLink.setAppUserId(AppController.user.getId());
            songLink.setSongId(songLink.getSong().getId());

            songSetIconCheck();
        }

        public void songRemoved(){
            songLink.makeEmpty();

            songSetIconAdd();
        }

        private void songSetIconCheck() {
            songAddButton.setImageResource(R.drawable.ic_check);
        }

        private void songSetIconAdd() {
            songAddButton.setImageResource(R.drawable.ic_add);
        }

        public UserSongLink getSongLink() {
            return songLink;
        }
    }
}
