package com.siroytman.indiewindymobile.Api;

import com.android.volley.NoConnectionError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

public class ErrorHandler {

    public static String HandleError(VolleyError error)
    {
        if (error.getClass() == NoConnectionError.class)
        {
            return "NoConnection";
        }
        if (error.getClass() == TimeoutError.class)
        {
            return "TimeoutError";
        }
        return "NoErrors";
    }
}
