package com.siroytman.indiewindymobile.Api;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.siroytman.indiewindymobile.R;

public class ApiController extends Application {
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
    public void getStringResponse(RestMethod method, String apiUrl, final VolleyCallback callback) {
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
}