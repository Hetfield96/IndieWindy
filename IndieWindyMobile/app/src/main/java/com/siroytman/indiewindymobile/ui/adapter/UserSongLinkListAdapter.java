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

import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.controller.AppController;
import com.siroytman.indiewindymobile.controller.SongController;
import com.siroytman.indiewindymobile.interfaces.ILinkAdd;
import com.siroytman.indiewindymobile.model.Song;
import com.siroytman.indiewindymobile.model.UserSongLink;
import com.siroytman.indiewindymobile.services.IconChanger;
import com.siroytman.indiewindymobile.ui.activity.PlayerActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class UserSongLinkListAdapter extends ArrayAdapter<UserSongLink> {
    public static final String TAG = "UserSongLinkListAdapter";
    private SongController songController;
    private Context context;
    private ArrayList<UserSongLink> songLinks;

    public UserSongLinkListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<UserSongLink> songLinks) {
        super(context, resource, songLinks);
        this.context = context;
        this.songLinks = songLinks;
        this.songController = SongController.getInstance();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        final UserSongLink songLink = songLinks.get(position);

        if(convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            assert inflater != null;
            convertView = inflater.inflate(R.layout.song_list_item, null);

            viewHolder = new ViewHolder(convertView, songLink, position);

            convertView.setTag(viewHolder);

        }
        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        return convertView;
    }


    // Single element
    public class ViewHolder implements View.OnClickListener, ILinkAdd<Song> {
        public static final String TAG = "UserSongLinkAdapter.VH";

        private int songPos;
        private UserSongLink songLink;

        private TextView songNameView;
        private TextView songArtistNameView;
        private ImageView songAddButton;
        private ImageView songOptionsButton;
        private ImageView songDownloadButton;


        public ViewHolder(View convertView, final UserSongLink songLink, final int songPos) {
            this.songLink = songLink;
            this.songPos = songPos;

            songNameView = convertView.findViewById(R.id.song_name);
            songArtistNameView = convertView.findViewById(R.id.artist_activity__artist_name);
            songAddButton = convertView.findViewById(R.id.song_add_button);
            songOptionsButton = convertView.findViewById(R.id.song_options_button);
            songDownloadButton = convertView.findViewById(R.id.song_download_button);

            songNameView.setText(songLink.getSong().getName());
            songArtistNameView.setText(songLink.getSong().getArtist().getName());

            IconChanger.setAddStateIcon(songLink, songAddButton);

            convertView.setOnClickListener(this);
            songAddButton.setOnClickListener(this);
            songOptionsButton.setOnClickListener(this);
            songDownloadButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Song song = songLink.getSong();

            Log.d(TAG, "onClick song: " + song.getName());

            switch (v.getId()) {
                case R.id.song_add_button:
                    if (songLink.isEmpty()) {
                        songController.addUserSongLink(this);
                    } else{
                        songController.removeUserSongLink(this);
                    }
                    return;
                case R.id.song_options_button:
                    PlayerActivity.showPopupMenu(getContext(), v, song);
                    return;
                case R.id.song_download_button:
                    // TODO
                    return;
            }


            Log.d(TAG, "to player starting starting with song: " + songLink.getSong().getName());
            Intent intent = new Intent(context, PlayerActivity.class);
            intent.putExtra("songLinks", songLinks);
            intent.putExtra("songPos", songPos);
            context.startActivity(intent);
        }

        @Override
        public Song getItem() {
            return songLink.getSong();
        }

        @Override
        public void removed() {
            songLink.makeEmpty();

            IconChanger.setIconAdd(songAddButton);
        }

        @Override
        public void added() {
            songLink.setAppUserId(AppController.user.getId());
            songLink.setSongId(songLink.getSong().getId());

            IconChanger.setIconCheck(songAddButton);
        }
    }
}
