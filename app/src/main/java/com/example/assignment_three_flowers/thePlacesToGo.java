package com.example.assignment_three_flowers;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;


public class thePlacesToGo extends Fragment {


    public thePlacesToGo() {
        // Required empty public constructor
    }

    private View placeView;

    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private ArrayList<Place> place;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        placeView = inflater.inflate(R.layout.fragment_the_places_to_go, container, false);

        recyclerView = placeView.findViewById(R.id.recyclerView);

        place = new ArrayList<>();



        try {
            InputStream inputStream = getResources().openRawResource(R.raw.places);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String jsonData = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(jsonData);
            for ( int i = 0; i < jsonArray.length(); i++ ) {
                JSONObject placeObject = jsonArray.getJSONObject(i);
                Place p = new Place();
                p.setCountry(placeObject.getString("Country"));
                p.setCity(placeObject.getString("City"));
                p.setClimate(placeObject.getString("Climate"));
                p.setAttire(placeObject.getString("Attire"));
                place.add(p);
            }
        } catch (Exception e) {
            //e.printStackTrace();
            Log.i("JSON", e.toString());
            Place aPlace = new Place();
            aPlace.setCountry("USA");
            aPlace.setCity("Baltimore");
            aPlace.setClimate("Atlantic Coast");
            aPlace.setAttire("Swim Wear");
            place.add(aPlace);
        }

        placesAdapter contactsAdapter = new placesAdapter(place, getActivity());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setAdapter(contactsAdapter);


        return placeView;
    }
}