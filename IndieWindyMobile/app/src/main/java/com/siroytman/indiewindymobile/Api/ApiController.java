package com.siroytman.indiewindymobile.api;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.siroytman.indiewindymobile.R;
import com.siroytman.indiewindymobile.controller.AppController;

import org.json.JSONArray;
import org.json.JSONObject;

public class ApiController {
    /**
     * Volley queue for executing requests to server
     */
    private VolleyQueue volleyQueue;

    /**
     * A singleton instance of the application class for easy access in other places
     */
    private static ApiController instance;

    private ApiController() {
        // initialize the singleton
        instance = this;
        // initialize volley queue
        volleyQueue = new VolleyQueue(AppController.getContext());
    }

    /**
     * @return ApplicationController singleton instance
     */
    public static synchronized ApiController getInstance() {
        if (instance == null)
        {
            instance = new ApiController();
        }
        return instance;
    }

    /**
     * Returns string response from server
     * @param method restMethod: GET, POST, ... {@link com.android.volley.Request.Method}
     * @param apiUrl api address
     * @param callback function to call when got response
     */
    public void getStringResponse(int method, String apiUrl, final VolleyCallbackString callback) {
        StringRequest request = new StringRequest(method,
                AppController.getContext().getString(R.string.server_url) + "/" + apiUrl,
        new Response.Listener <String> () {
            @Override
            public void onResponse(String response) {
                callback.onSuccessResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e) {
                Log.d("ApiController", "Error: " + e.toString());
            }
        });

        volleyQueue.addToRequestQueue(request);
    }


    public void getJSONObjectResponse(String apiUrl, JSONObject json, final VolleyCallbackJSONObject callback) {
        JsonObjectRequest request = new JsonObjectRequest(AppController.getContext().getString(R.string.server_url) + "/" + apiUrl,
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
                Log.d("ApiController", "Error: " + e.toString());
            }
        });

        volleyQueue.addToRequestQueue(request);
    }

    public void getJSONArrayResponse(int method, String apiUrl, JSONArray json, final VolleyCallbackJSONArray callback) {
        JsonArrayRequest request = new JsonArrayRequest(method, AppController.getContext().getString(R.string.server_url) + "/" + apiUrl,
                json,
                new Response.Listener<JSONArray> () {
                    @Override
                    public void onResponse(JSONArray response) {
                        callback.onSuccessResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e) {
                callback.onErrorResponse(e);
                Log.d("ApiController", "Error: " + e.toString());
            }
        });

        volleyQueue.addToRequestQueue(request);
    }
}