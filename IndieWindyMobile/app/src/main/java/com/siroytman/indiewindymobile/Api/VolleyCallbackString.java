package com.siroytman.indiewindymobile.api;

import com.android.volley.VolleyError;

public interface VolleyCallbackString
{
    void onSuccessResponse(String result);
    void onErrorResponse(VolleyError error);
}
