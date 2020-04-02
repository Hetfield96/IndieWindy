package com.siroytman.indiewindymobile.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.controller.PostController;
import com.siroytman.indiewindymobile.model.Artist;
import com.siroytman.indiewindymobile.model.ArtistPostLink;
import com.siroytman.indiewindymobile.model.UserSongLink;
import com.siroytman.indiewindymobile.services.FragmentService;
import com.siroytman.indiewindymobile.ui.activity.ArtistActivity;
import com.siroytman.indiewindymobile.ui.fragments.links.UserSongLinkListFragment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;

public class ArtistPostLinkListAdapter extends ArrayAdapter<ArtistPostLink> {
    public static final String TAG = "PostsListAdapter";
    private Context context;
    private List<ArtistPostLink> links;
    private FragmentManager fragmentManager;

    public ArtistPostLinkListAdapter(Context context, int resource, List<ArtistPostLink> links, FragmentManager fragmentManager) {
        super(context, resource, links);
        this.context = context;
        this.links = links;
        this.fragmentManager = fragmentManager;
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
    public class ViewHolder implements View.OnClickListener {
        public static final String TAG = "PostAdapter.VH";
        private ArtistPostLink link;

        private TextView postTextView;
        private TextView postArtistNameView;
        private ImageView postArtistPhotoView;
        private TextView postDateView;

        private RelativeLayout relativeLayout;
        private FrameLayout frameLayout;

        public ViewHolder(View convertView, final ArtistPostLink link) {
            this.link = link;

            postTextView = convertView.findViewById(R.id.post_list_item__text);
            postArtistNameView = convertView.findViewById(R.id.post_list_item__artist_name);
            postArtistPhotoView = convertView.findViewById(R.id.post_list_item__artist_photo);
            postDateView = convertView.findViewById(R.id.post_list_item__date);
            relativeLayout = convertView.findViewById(R.id.post_list_item__layout);

            Glide.with(convertView).load(link.getArtist().getImageUrl()).into(postArtistPhotoView);
            getPostSongs();
            postArtistNameView.setText(link.getArtist().getName());
            postTextView.setText(link.getPost().getText());
            postTextView.setMovementMethod(new ScrollingMovementMethod());
            postDateView.setText(link.getPost().getDateString());

            postArtistNameView.setOnClickListener(this);
            postArtistPhotoView.setOnClickListener(this);
            convertView.setOnClickListener(this);
        }

        private void getPostSongs() {
            // Create frame layout
            frameLayout = new FrameLayout(context);
            frameLayout.setId(getPosition(link) + 1);
            frameLayout.setScrollContainer(false);
            PostController.getInstance().getPostSongs(this, link.getPostId());
        }

        public void updatePostSongsView(List<UserSongLink> links) {
            // Dynamically add frame layout
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.addRule(RelativeLayout.BELOW, postTextView.getId());

            relativeLayout.addView(frameLayout, layoutParams);

            UserSongLinkListFragment fragment = new UserSongLinkListFragment(links, true);
            FragmentService.replaceFragment(fragmentManager, frameLayout.getId(), fragment);

        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "Post clicked");
            Artist artist = link.getArtist();

            switch (v.getId()) {
                case R.id.post_list_item__artist_name:
                case R.id.post_list_item__artist_photo:
                    Log.d(TAG, "to page of artist: " + artist.getName());
                    Intent intent = new Intent(context, ArtistActivity.class);
                    intent.putExtra(Artist.class.getSimpleName(), artist);
                    context.startActivity(intent);
                return;
            }
        }


    }
}
