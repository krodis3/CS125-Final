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

    /** This is the TAG name for this class. Used in Log messages. */
    private static final String TAG = "MainActivity";

    /** Code that runs automatically whenever the app comes into the foreground.
     * This does not need to be modified.
     * @param savedInstanceState a saved state from when the app left the foreground.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This function sends user input data to the API and then calls the JSON Parser.
     * This function should be called when the 'Translate' button is pressed.
     * <strong> This code should not be modified. </strong>
     * @param toTranslate Text from user input.
     */
    public void callAPI(final String toTranslate) {
        Log.d(TAG, "callAPI: I was activated");
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://api.funtranslations.com/translate/yoda.json?text=" + toTranslate;
        Log.d(TAG, "callAPI: using url: " + url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "onResponse: Received Response!");
                        Log.d(TAG, "onResponse: " + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: Error returning data or request limit exceeded.");
            }
        });
        queue.add(stringRequest);
    }

    /**
     * This function gets the translation from the JSON object and sets the text view.
     * @param json json string from callAPI
     */
    public void translate(final String json) {
        String translation = getObj(json).get("contents").getAsJsonObject().get("translation").getAsString();
        //Set textView = translation
    }

    /**
     * This is a helper function for translate.
     * Takes a string and returns a JSON object.
     * This code does not need to be modified.
     * @param json JSON response from API.
     * @return JSON object built from API response.
     */
    private static JsonObject getObj(final String json) {
        JsonParser parser = new JsonParser();
        return parser.parse(json).getAsJsonObject();
    }
}
