package com.siroytman.indiewindymobile.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.siroytman.indiewindymobile.Api.ApiController;
import com.siroytman.indiewindymobile.Api.RestMethod;
import com.siroytman.indiewindymobile.Api.VolleyStringCallback;
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
        apiController.getStringResponse(RestMethod.GET, "appuser", new VolleyStringCallback() {
            @Override
            public void onSuccessResponse(String result) {
                MakeToastMessage("Result: " + result, Toast.LENGTH_LONG);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                MakeToastMessage("Error: " + error.toString(), Toast.LENGTH_LONG);
            }
        });
    }

    public void MakeToastMessage(String msg, int ToastLength)
    {
        Toast.makeText(this, msg, ToastLength).show();
    }

}
