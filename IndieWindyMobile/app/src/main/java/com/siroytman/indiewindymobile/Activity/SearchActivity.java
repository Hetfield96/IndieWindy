package com.siroytman.indiewindymobile.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.siroytman.indiewindymobile.Api.ApiController;
import com.siroytman.indiewindymobile.Api.ErrorHandler;
import com.siroytman.indiewindymobile.Api.RestMethod;
import com.siroytman.indiewindymobile.Api.VolleyJSONCallback;
import com.siroytman.indiewindymobile.Api.VolleyStringCallback;
import com.siroytman.indiewindymobile.Core;
import com.siroytman.indiewindymobile.Model.AppUser;
import com.siroytman.indiewindymobile.Model.Artist;
import com.siroytman.indiewindymobile.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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

    // Поиск (пока только по артистам)
    private void handleSearchIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            String url = "artist/getByName/" + query;
            apiController.getJSONResponse(url, null, new VolleyJSONCallback() {
                @Override
                public void onSuccessResponse(JSONObject result) {
                    Artist artist = apiController.gson.fromJson(result.toString(), Artist.class);
                    MakeToastMessage("Artist found: " + artist.name, Toast.LENGTH_LONG);
                }

                @Override
                public void onErrorResponse(VolleyError error) {
                    String res = ErrorHandler.HandleError(error);

                    MakeToastMessage("Artist not found!", Toast.LENGTH_LONG);
                }
            });
        }
    }

    public void MakeToastMessage(String msg, int ToastLength)
    {
        Toast.makeText(this, msg, ToastLength).show();
    }


}
