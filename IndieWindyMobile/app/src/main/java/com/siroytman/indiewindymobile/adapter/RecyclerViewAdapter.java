package com.siroytman.indiewindymobile.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.model.Song;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Song> songsList;

    public RecyclerViewAdapter(Context context, List<Song> songsList) {
        this.context = context;
        this.songsList = songsList;
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

        public TextView songName;
        public TextView artistName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            songName = itemView.findViewById(R.id.song_name);
            artistName = itemView.findViewById(R.id.artist_name);
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            Song song = songsList.get(position);

//            Intent intent = new Intent(context, DetailsActivity.class);
//            intent.putExtra("name", song.getName());
//            intent.putExtra("number", song.getPhoneNumber());
//
//            context.startActivity(intent);


//            switch (v.getId()) {
//                case R.id.icon_button:
//                    Log.d("IconClicked", "onClick: " + song.getPhoneNumber());
//                    break;
//            }


             Log.d("Clicked", "onClick: " + song.getName());
        }
    }
}