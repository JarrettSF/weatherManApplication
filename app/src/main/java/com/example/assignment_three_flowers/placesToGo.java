package com.example.assignment_three_flowers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

public class placesToGo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_to_go);

        FrameLayout frameLayout = findViewById(R.id.fragments_frame);

        swapFragments(frameLayout, 0);

    }

    public void swapFragments(FrameLayout frameLayout, int fragmentNum) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch(fragmentNum) {
            case 0:
                thePlacesToGo placeList = new thePlacesToGo();
                fragmentTransaction.replace(frameLayout.getId(), placeList);
                break;
            case 1:
                placesToGoDetails detail = new placesToGoDetails();
                fragmentTransaction.replace(frameLayout.getId(), detail);
                break;

            default:
                Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_LONG).show();
        }

        fragmentTransaction.commit();
    }





}