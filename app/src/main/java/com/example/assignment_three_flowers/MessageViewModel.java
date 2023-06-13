package com.example.assignment_three_flowers;

import androidx.lifecycle.ViewModel;


public class MessageViewModel extends ViewModel {

   private TripDetails tripDetails;

   public TripDetails getTripDetails(){ return tripDetails;}

    public void setTripDetails(TripDetails tripDetails)

    {this.tripDetails = tripDetails;}

    }




