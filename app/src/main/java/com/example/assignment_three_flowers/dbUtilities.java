package com.example.assignment_three_flowers;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class dbUtilities extends Fragment {



    public dbUtilities() {
        // Required empty public constructor
    }

    private View view;
    private TextView recordCount;

    private Button eraseDB;
    private Button loadJSON;

    private Button exit;

    private placesDatabaseHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_db_utilities, container, false);

        dbHelper = new placesDatabaseHelper(getActivity());

        recordCount = view.findViewById(R.id.recordCount);
        recordCount.setText("Number of records: " + dbHelper.getNumberOfPlaces());

        eraseDB = view.findViewById(R.id.erase);
        eraseDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("DBUtils", "Clear DB");
                dbHelper.clearRecords();
                recordCount.setText("Number of records: " + dbHelper.getNumberOfPlaces());
            }
        });

        loadJSON = view.findViewById(R.id.button_load_json);
        loadJSON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("DBUtils", "Load JSON");
                dbHelper.loadJSONContacts();
                recordCount.setText("Number of records: " + dbHelper.getNumberOfPlaces());
            }
        });

        exit = view.findViewById(R.id.shutDown);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragments_frame, new thePlacesToGo());
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}