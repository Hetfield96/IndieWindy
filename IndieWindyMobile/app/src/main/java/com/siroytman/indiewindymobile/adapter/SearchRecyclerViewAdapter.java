package com.siroytman.indiewindymobile.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.controller.SongController;
import com.siroytman.indiewindymobile.model.Song;

import java.io.IOException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewAdapter.ViewHolder> {
    private MediaPlayer mediaPlayer;

    private Context context;
    private List<Song> songsList;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            mediaPlayer.release();
        }
    }

    public SearchRecyclerViewAdapter(Context context, List<Song> songsList) {
        this.context = context;
        this.songsList = songsList;

        mediaPlayer = new MediaPlayer();
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
        Song song = songsList.get(position); // each contact object inside of our list

        viewHolder.songName.setText(song.getName());
        viewHolder.artistName.setText(song.getArtist().getName());
    }


    @Override
    public int getItemCount() {
        return songsList.size();
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
            Song song = songsList.get(position);

            try {
                mediaPlayer.setDataSource(song.getSongUrl());
            } catch (IOException e) {
                e.printStackTrace();
            }

            MediaPlayer.OnPreparedListener preparedListener = new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(final MediaPlayer mp) {
                    Log.d("MediaPlayer", "Music is playing");
                    Toast.makeText(v.getContext(), "Music is playing!", Toast.LENGTH_LONG).show();
                    mp.start();
                }
            };
            mediaPlayer.setOnPreparedListener(preparedListener);
            mediaPlayer.prepareAsync();


            switch (v.getId()) {
                case R.id.song_add_button:
                    SongController.AddSong(this, song.getId());
                    break;
            }


             Log.d("Clicked", "onClick: " + song.getName());
        }

        public void songAddedChangeIcon()
        {
            songAddButton.setImageResource(R.drawable.ic_check);
        }
    }
}