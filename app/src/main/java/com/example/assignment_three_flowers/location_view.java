package com.example.assignment_three_flowers;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.assignment_three_flowers.R;


public class location_view extends Fragment {

    public location_view() {
        // Required empty public constructor
    }
//this was the other fragment that I could not get the methods from "tripDetails" to work
    private MessageViewModel messageViewModel;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_location_view, container, false);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        messageViewModel = new ViewModelProvider(requireActivity()).get(MessageViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((TextView)view.findViewById(R.id.a1)).setText(messageViewModel.getTripDetails().getContinent());
        ((TextView)view.findViewById(R.id.a2)).setText(messageViewModel.getTripDetails().getCity());
        ((TextView)view.findViewById(R.id.a3)).setText(messageViewModel.getTripDetails().getPeople());
    }


}