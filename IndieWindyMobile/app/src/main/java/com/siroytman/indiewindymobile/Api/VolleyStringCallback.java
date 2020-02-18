package com.siroytman.indiewindymobile.Api;

import com.android.volley.VolleyError;

public interface VolleyStringCallback {
    void onSuccessResponse(String result);
    void onErrorResponse(VolleyError error);
}