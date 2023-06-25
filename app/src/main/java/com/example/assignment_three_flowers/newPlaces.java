package com.example.assignment_three_flowers;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class newPlaces extends Fragment {

    public newPlaces() {
        // Required empty public constructor
    }

    private View view;

    private placesDatabaseHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_new_places, container, false);

        dbHelper = new placesDatabaseHelper(getActivity());

        EditText newId = view.findViewById(R.id.placeId);
        EditText Country = view.findViewById(R.id.country);
        EditText City = view.findViewById(R.id.city);
        EditText Climate = view.findViewById(R.id.climate);
        EditText Attire = view.findViewById(R.id.attire);

        newId.setText(String.valueOf(dbHelper.getNewId()));

        Button saveAndClose = view.findViewById(R.id.saveButton);
        saveAndClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Place newPlace = new Place();
                newPlace.setId(Integer.parseInt(newId.getText().toString()));
                newPlace.setCountry(Country.getText().toString());
                newPlace.setCity(City.getText().toString());
                newPlace.setClimate(Climate.getText().toString());
                newPlace.setAttire(Attire.getText().toString());
                dbHelper.addPlace(newPlace);

                leaveFragment();
            }
        });

        Button closeWithoutSave = view.findViewById(R.id.exitButton);
        closeWithoutSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leaveFragment();
            }
        });

        return view;
    }

    private void leaveFragment() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragments_frame, new thePlacesToGo());
        fragmentTransaction.commit();
    }


}