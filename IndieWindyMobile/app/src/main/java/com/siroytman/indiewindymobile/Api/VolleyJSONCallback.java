package com.siroytman.indiewindymobile.Api;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface VolleyJSONCallback {
    void onSuccessResponse(JSONObject result);
    void onErrorResponse(VolleyError error);
}
