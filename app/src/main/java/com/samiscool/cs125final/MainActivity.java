package com.samiscool.cs125final;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    public static String JSON = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callAPI("Imyoda.");
        Log.d(TAG, "onCreate: JSON =" + JSON);
    }

    public void callAPI(final String toTranslate) {
        Log.d(TAG, "callAPI: i got called");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://api.funtranslations.com/translate/yoda.json?text=" + toTranslate;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG,"response!");
                        JSON = response;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.toString());
                Log.d(TAG, "Here is the ERROR!");
                JSON = "Volley request done goofed.";
            }
        });
        queue.add(stringRequest);
    }
}
