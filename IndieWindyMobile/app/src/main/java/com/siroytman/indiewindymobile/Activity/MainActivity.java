package com.siroytman.indiewindymobile.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.siroytman.indiewindymobile.Api.ApiController;
import com.siroytman.indiewindymobile.Api.RestMethod;
import com.siroytman.indiewindymobile.Api.VolleyCallback;
import com.siroytman.indiewindymobile.R;

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
        System.out.println();
        apiController.getStringResponse(RestMethod.GET, "appuser", new VolleyCallback() {
            @Override
            public void onSuccessResponse(String result) {
                System.out.println("Result: " + result);
            }
        });
    }

}
