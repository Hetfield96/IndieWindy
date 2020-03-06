package com.siroytman.indiewindymobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.controller.MediaController;
import com.siroytman.indiewindymobile.controller.SongController;
import com.siroytman.indiewindymobile.model.Song;
import com.siroytman.indiewindymobile.model.UserSongLink;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewAdapter.ViewHolder> {
    private SongController songController;
    private MediaController mediaController;
    private Context context;
    private List<UserSongLink> linksList;

    public SearchRecyclerViewAdapter(Context context, List<UserSongLink> linksList) {
        this.context = context;
        this.linksList = linksList;

        mediaController = MediaController.getInstance();
        songController = SongController.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.song_row, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        UserSongLink link = linksList.get(position); // each contact object inside of our list

        viewHolder.songName.setText(link.getSong().getName());
        viewHolder.artistName.setText(link.getSong().getArtist().getName());

        // If song is already added
        if(!link.isEmpty()){
            viewHolder.songSetIconCheck();
        }
    }


    @Override
    public int getItemCount() {
        return linksList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView songName;
        private TextView artistName;
        private ImageView songAddButton;
        private ImageView songOptionsButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            songName = itemView.findViewById(R.id.song_name);
            artistName = itemView.findViewById(R.id.artist_name);
            songAddButton = itemView.findViewById(R.id.song_add_button);
            songOptionsButton = itemView.findViewById(R.id.song_options_button);

            itemView.setOnClickListener(this);
            songAddButton.setOnClickListener(this);
        }


        @Override
        public void onClick(final View v) {
            int position = getAdapterPosition();
            UserSongLink link = linksList.get(position);
            Song song = link.getSong();

            switch (v.getId()) {
                case R.id.song_add_button:
                    songController.AddUserSongLink(this, song.getId());
                    return;
            }

            mediaController.prepareOrStartPause(song);
        }

        public void songSetIconCheck()
        {
            songAddButton.setImageResource(R.drawable.ic_check);
        }

        public void songSetIconAdd()
        {
            songAddButton.setImageResource(R.drawable.ic_add);
        }
    }
}