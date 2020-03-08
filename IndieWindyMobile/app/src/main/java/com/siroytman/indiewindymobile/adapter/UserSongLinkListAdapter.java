package com.siroytman.indiewindymobile.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.controller.AppController;
import com.siroytman.indiewindymobile.controller.MediaController;
import com.siroytman.indiewindymobile.controller.SongController;
import com.siroytman.indiewindymobile.model.Song;
import com.siroytman.indiewindymobile.model.UserSongLink;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class UserSongLinkListAdapter extends ArrayAdapter<UserSongLink> {
    public static final String TAG = "UserSongLinkListAdapter";
    private MediaController mediaController;
    private SongController songController;
    private Context context;
    private List<UserSongLink> linksLinks;

    public UserSongLinkListAdapter(@NonNull Context context, int resource, @NonNull List<UserSongLink> linksLinks) {
        super(context, resource, linksLinks);
        this.context = context;
        this.linksLinks = linksLinks;
        songController = SongController.getInstance();
        mediaController = MediaController.getInstance();
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        final UserSongLink songLink = linksLinks.get(position);

        if(convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            assert inflater != null;
            convertView = inflater.inflate(R.layout.song_list_item, null);

            viewHolder = new ViewHolder(convertView, position, songLink);

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
        private int position;
        private UserSongLink songLink;

        private TextView songNameView;
        private TextView songArtistNameView;
        private ImageView songAddButton;
        private ImageView songOptionsButton;

        public ViewHolder(View convertView, final int position, final UserSongLink songLink) {
            this.songLink = songLink;
            this.position = position;

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
        }

        @Override
        public void onClick(View v) {
            Song song = songLink.getSong();

            Log.d(TAG, "onClick song: " + song.getName());

            switch (v.getId()) {
                case R.id.song_add_button:
                    Log.d(TAG, "onClick add button: " + song.getName());

                    if (songLink.isEmpty()) {
                        songController.addUserSongLink(this);
                    } else{
                        songController.removeUserSongLink(this);
                    }
                    return;
            }

            mediaController.prepareOrStartPause(song);
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
