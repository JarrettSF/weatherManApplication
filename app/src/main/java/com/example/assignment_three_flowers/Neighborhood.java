package com.example.assignment_three_flowers;

import androidx.appcompat.app.AppCompatActivity;

public class Neighborhood extends AppCompatActivity {


    public Neighborhood() {

        hoodName = "Cherry Hill";

        streetName = "Waldenhorph";


    }

    private String hoodName;
    private String streetName;




    public String getHoodName() {
        return hoodName;
    }


    public void setHoodName(String hoodName) {
        this.hoodName = hoodName;
    }



    public String streetName() {
        return streetName;
    }



    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }


    public String toString() {
        return hoodName;
    }

    public String tooString() {
        return streetName;
    }









}
