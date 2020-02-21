package com.siroytman.indiewindymobile.Activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.siroytman.indiewindymobile.Api.ApiController;
import com.siroytman.indiewindymobile.Api.ErrorHandler;
import com.siroytman.indiewindymobile.Api.VolleyJSONCallback;
import com.siroytman.indiewindymobile.Model.Artist;
import com.siroytman.indiewindymobile.R;

import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {

    ApiController apiController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        apiController = ApiController.getInstance(this);

        handleSearchIntent(getIntent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleSearchIntent(intent);
    }

    // Search (by artists)
    private void handleSearchIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            String url = "artist/getByName/" + query;
            apiController.getJSONResponse(url, null, new VolleyJSONCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    try {
                        Artist artist = apiController.gson.fromJson(result.toString(), Artist.class);
                        Toast.makeText(SearchActivity.this, "Artist found: " + artist.name, Toast.LENGTH_LONG)
                                .show();
                    }
                    catch (Exception e)
                    {
                        Log.d("VolleyError", "Unable to parse response: " + e.getMessage());
                    }

                }

                @Override
                public void onErrorResponse(VolleyError error) {
                    if (!ErrorHandler.HandleError(SearchActivity.this, error)) {
                        Toast.makeText(SearchActivity.this, "Artist not found!", Toast.LENGTH_LONG)
                                .show();
                    }
                }
            });
        }
    }


}
