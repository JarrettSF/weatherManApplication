package com.example.assignment_three_flowers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class placesAdapter extends RecyclerView.Adapter<placesAdapter.PlacesHolder> {
    public placesAdapter(ArrayList<Place> place, FragmentActivity fragmentActivity) {
        this.place = place;
        this.fragmentActivity = fragmentActivity;
    }

    private ArrayList<Place> place;
    private FragmentActivity fragmentActivity;

    @NonNull
    @Override
    public PlacesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View showPlace = layoutInflater.inflate(R.layout.short_list_for_places_to_go, parent, false);
        PlacesHolder placesHolder  = new PlacesHolder(showPlace);
        return placesHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlacesHolder location, int position) {
        Place p = place.get(position);
        location.Country.setText(p.getCountry());
        location.City.setText(p.getCity());
        location.shortList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Bundle contactBundle = new Bundle();
                contactBundle.putParcelable("place", p);

                placesToGoDetails contactsInfoFragment = new placesToGoDetails();
                contactsInfoFragment.setArguments(contactBundle);

                fragmentTransaction.replace(R.id.fragments_frame, contactsInfoFragment);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return place.size();
    }

    public class PlacesHolder extends RecyclerView.ViewHolder {

        public TextView Country;

        public TextView City;

        public LinearLayout shortList;
        public PlacesHolder(@NonNull View itemView) {
            super(itemView);
            Country = itemView.findViewById(R.id.Country);
            City = itemView.findViewById(R.id.City);
            shortList = itemView.findViewById(R.id.shortList);
        }
    }
}
