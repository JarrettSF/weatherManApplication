package com.example.assignment_three_flowers;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class placesToGoDetails extends Fragment {



    public placesToGoDetails() {
        // Required empty public constructor
    }

        private View placeView;

        private Place place;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            placeView = inflater.inflate(R.layout.fragment_places_to_go_details, container, false);

            this.place = (Place)getArguments().getParcelable("place");

            TextView countryView = placeView.findViewById(R.id.Country);
            TextView cityView = placeView.findViewById(R.id.City);
            TextView climateView = placeView.findViewById(R.id.Climate);
            TextView attireView = placeView.findViewById(R.id.Attire);

            countryView.setText("Country: " + place.getCountry());
            cityView.setText("City: " + place.getCity());
            climateView.setText("Climate: " + place.getClimate());
            attireView.setText("Attire: " + place.getAttire());

            placeView.findViewById(R.id.closePlaces).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragments_frame, new thePlacesToGo());
                    fragmentTransaction.commit();
                }
            });

        return placeView;


    }

}