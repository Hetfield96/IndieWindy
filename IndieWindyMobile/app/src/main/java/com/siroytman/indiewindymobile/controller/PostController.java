package com.siroytman.indiewindymobile.controller;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.siroytman.indiewindymobile.api.ApiController;
import com.siroytman.indiewindymobile.api.VolleyCallbackJSONArray;
import com.siroytman.indiewindymobile.model.ArtistPostLink;
import com.siroytman.indiewindymobile.model.Post;
import com.siroytman.indiewindymobile.model.UserSongLink;
import com.siroytman.indiewindymobile.ui.adapter.ArtistPostLinkListAdapter;
import com.siroytman.indiewindymobile.ui.fragments.home.SubscriptionHomeFragment;

import org.json.JSONArray;

import java.util.ArrayList;

public class PostController {
    private static final String TAG = "PostController";
    private ApiController apiController;
    private static PostController instance;


    private PostController() {
        apiController = ApiController.getInstance();
    }

    public static synchronized PostController getInstance() {
        if (instance == null) {
            instance = new PostController();
        }
        return instance;
    }

    public void getSubscriptionPosts(){
        String url = "post/getBySubscription/" + AppController.user.getId();
        apiController.getJSONArrayResponse(Request.Method.GET, url, null, new VolleyCallbackJSONArray() {
            @Override
            public void onSuccessResponse(JSONArray result) {
                try {
                    Log.d(TAG, "getSubscriptionPosts: search request completed");
                    ArrayList<ArtistPostLink> posts = ArtistPostLink.parseArray(result);
                    SubscriptionHomeFragment.getInstance().postsFoundViewUpdate(posts);
                }
                catch (Exception e) {
                    Log.d(TAG, "Unable to parse response: " + e.getMessage());
                }
            }
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "getSubscriptionPosts: search request not completed!" + error.getMessage());
            }
        });
    }

    public void getPostSongs(final ArtistPostLinkListAdapter.ViewHolder viewHolder, int postId){
        String url = "post/getSongs/" + AppController.user.getId() + "/" + postId;
        apiController.getJSONArrayResponse(Request.Method.GET, url, null, new VolleyCallbackJSONArray() {
            @Override
            public void onSuccessResponse(JSONArray result) {
                try {
                    Log.d(TAG, "getPostSongs: completed");
                    if(result.length() > 0) {
                        ArrayList<UserSongLink> songLinks = UserSongLink.parseLinks(result);
                        viewHolder.updatePostSongsView(songLinks);
                    }
                }
                catch (Exception e) {
                    Log.d(TAG, "Unable to parse response: " + e.getMessage());
                }
            }
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "getPostSongs: not completed!" + error.getMessage());
            }
        });
    }

}
