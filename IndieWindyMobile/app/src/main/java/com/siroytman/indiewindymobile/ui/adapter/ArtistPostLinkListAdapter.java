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
import com.siroytman.indiewindymobile.controller.PostController;
import com.siroytman.indiewindymobile.model.Artist;
import com.siroytman.indiewindymobile.model.ArtistPostLink;
import com.siroytman.indiewindymobile.ui.activity.ArtistActivity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ArtistPostLinkListAdapter extends ArrayAdapter<ArtistPostLink> {
    public static final String TAG = "PostsListAdapter";
    private PostController postController;
    private Context context;
    private List<ArtistPostLink> links;

    public ArtistPostLinkListAdapter(@NonNull Context context, int resource, @NonNull List<ArtistPostLink> links) {
        super(context, resource, links);
        this.context = context;
        this.links = links;
        this.postController = PostController.getInstance();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;

        if(convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.post_list_item, null);

            viewHolder = new ViewHolder(convertView, links.get(position));

            convertView.setTag(viewHolder);

        }
        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        return convertView;
    }


    // Single element
    public class ViewHolder  implements View.OnClickListener {
        public static final String TAG = "PostAdapter.VH";
        private ArtistPostLink link;

        private TextView postTextView;
        private TextView postArtistNameView;
        private ImageView postArtistPhotoView;

        public ViewHolder(View convertView, final ArtistPostLink link) {
            this.link = link;

            postTextView = convertView.findViewById(R.id.post_list_item__text);
            postArtistNameView = convertView.findViewById(R.id.post_list_item__artist_name);
            postArtistPhotoView = convertView.findViewById(R.id.post_list_item__artist_photo);

            postArtistNameView.setText(link.getArtist().getName());
            postTextView.setText(link.getPost().getText());
            Glide.with(convertView).load(link.getArtist().getImageUrl()).into(postArtistPhotoView);


            convertView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "Post clicked");
            Artist artist = link.getArtist();

            Log.d(TAG, "to page of artist: " + artist.getName());
            Intent intent = new Intent(context, ArtistActivity.class);
            intent.putExtra(Artist.class.getSimpleName(), artist);
            context.startActivity(intent);
        }
    }
}
