package com.siroytman.indiewindymobile.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.siroytman.indiewindymobile.api.ApiController;
import com.siroytman.indiewindymobile.api.VolleyCallbackString;
import com.siroytman.indiewindymobile.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ApiController apiController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiController = ApiController.getInstance(this);

        request();
    }

    private void request()
    {
        apiController.getStringResponse(Request.Method.GET, "appuser", new VolleyCallbackString() {
            @Override
            public void onSuccessResponse(String result) {
                Toast.makeText(MainActivity.this, "Result: " + result, Toast.LENGTH_LONG)
                        .show();
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "VolleyError: " + error.getMessage(), Toast.LENGTH_LONG)
                        .show();
            }
        });
    }


}
