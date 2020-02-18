package com.siroytman.indiewindymobile.Api;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.siroytman.indiewindymobile.R;

import org.json.JSONObject;

public class ApiController extends Application {

    public Gson gson;

    Context appContext;

    /**
     * Volley queue for executing requests to server
     */
    VolleyQueue volleyQueue;

    /**
     * A singleton instance of the application class for easy access in other places
     */
    private static ApiController instance;


    public ApiController(Context appContext) {
        this.appContext = appContext;
        // initialize the singleton
        instance = this;
        // initialize volley queue
        volleyQueue = new VolleyQueue(appContext);
        gson = new Gson();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * @return ApplicationController singleton instance
     */
    public static synchronized ApiController getInstance(Context appContext) {
        if (instance == null)
        {
            instance = new ApiController(appContext);
        }
        return instance;
    }


    /**
     * Returns string response from server
     * @param method restMethod: GET, POST, ... {@link RestMethod}
     * @param apiUrl api address
     * @param callback function to call when got response
     */
    public void getStringResponse(RestMethod method, String apiUrl, final VolleyStringCallback callback) {
        StringRequest request = new StringRequest(method.ordinal(),
                appContext.getString(R.string.server_url) + "/" + apiUrl,
        new Response.Listener <String> () {
            @Override
            public void onResponse(String response) {
                callback.onSuccessResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e) {
                e.printStackTrace();
                Toast.makeText(appContext, "Error: " + e, Toast.LENGTH_LONG).show();
            }
        });

        volleyQueue.addToRequestQueue(request);
    }

    /**
     * Returns string response from server
     * @param apiUrl api address
     * @param callback function to call when got response
     */
    public void getStringResponseJSON(String apiUrl, JSONObject json, final VolleyJSONCallback callback) {
        JsonObjectRequest request = new JsonObjectRequest(appContext.getString(R.string.server_url) + "/" + apiUrl,
                json,
                new Response.Listener<JSONObject> () {
                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onSuccessResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e) {
                callback.onErrorResponse(e);
                e.printStackTrace();
                System.out.println("Error: " + e.toString());
            }
        });

        volleyQueue.addToRequestQueue(request);
    }
}