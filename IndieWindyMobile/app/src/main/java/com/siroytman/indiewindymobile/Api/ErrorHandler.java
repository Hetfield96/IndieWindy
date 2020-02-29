package com.siroytman.indiewindymobile.api;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

public class ErrorHandler {

    // Return true if handled, false otherwise
    public static Boolean HandleError(Context context, VolleyError error)
    {
        if (error.getClass() == NoConnectionError.class)
        {
            Toast.makeText(context, "NoConnection", Toast.LENGTH_LONG)
                    .show();
            Log.d("VolleyError", "No connection error");
            return true;
        }
        if (error.getClass() == TimeoutError.class)
        {
            Toast.makeText(context, "TimeoutError", Toast.LENGTH_LONG)
                    .show();
            Log.d("VolleyError", "Timeout Error");
            return true;
        }

        Log.d("VolleyError", error.getMessage());
        return false;
    }
}
