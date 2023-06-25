package com.example.assignment_three_flowers;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class zipCode extends AppCompatActivity {

    private TextView city, state, country;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zip_code);

        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
        country = findViewById(R.id.country);
        Button zip = findViewById(R.id.zip);
        Intent one = getIntent();

        int zipC = one.getIntExtra("zip", 0);

        setInitialUIState();

        zip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( zipC < 0 ) {
                    Toast.makeText(zipCode.this, "Invalid search", Toast.LENGTH_LONG).show();
                    setInitialUIState();
                    return;
                }

                String requestURL = "https://api.zippopotam.us/us/" + zipC;
                getCall(requestURL, zipCode.this);
            }
        });
    }

    private void setInitialUIState() {
        setUIState("---", "---", "---");
    }

    private void setUIState(String cityInfo, String stateInfo, String countryInfo) {

        city.setText("City: " + cityInfo);
        state.setText("State: " + stateInfo);
        country.setText("Country: " + countryInfo);
    }

    public void getCall(String url, Context context) {

            if ( url == null ) url = "https://api.zippopotam.us/us/21201";
            // Response: {"post code": "21201", "country": "United States", "country abbreviation": "US", "places": [{"place name": "Baltimore", "longitude": "-76.6252", "state": "Maryland", "state abbreviation": "MD", "latitude": "39.2946"}]}


            StringRequest stringRequest = new StringRequest(
                    Request.Method.GET,
                    url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject jsonResponse = new JSONObject(response.toString());
                                String countryInfo = jsonResponse.getString("country");
                                countryInfo += " (" + jsonResponse.getString("country abbreviation") + ")";

                                JSONArray placesInfo = jsonResponse.getJSONArray("places");
                                JSONObject extraInfo = placesInfo.getJSONObject(0);

                                String stateInfo = extraInfo.getString("state");
                                stateInfo += " (" + extraInfo.getString("state abbreviation") + ")";

                                String cityInfo = extraInfo.getString("place name");

                                setUIState(cityInfo, stateInfo, countryInfo);
                            } catch (JSONException e) {
                                setInitialUIState();
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    setInitialUIState();
                }
            });
            Volley.newRequestQueue(context).add(stringRequest);
        }
    }


