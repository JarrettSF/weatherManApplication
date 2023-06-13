package com.example.assignment_three_flowers;

import android.os.Parcel;
import android.os.Parcelable;

public class Place implements Parcelable {
    public Place() {


        setCountry("---");
        setCity("---");
        setClimate("---");
        setAttire("---");
    }

    private String country;
    private String city;
    private String climate;
    private String attire;

    protected Place(Parcel in) {
        country = in.readString();
        city = in.readString();
        climate = in.readString();
        attire = in.readString();
    }

    public static final Creator<Place> CREATOR = new Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };

    public String getCountry() {
        return country;
    }

    public void setCountry(String firstName) {
        this.country = firstName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String lastName) {
        this.city = lastName;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String phoneNumber) {
        this.climate = phoneNumber;
    }

    public String getAttire() {
        return attire;
    }

    public void setAttire(String emailAddress) {
        this.attire = emailAddress;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(country);
        parcel.writeString(city);
        parcel.writeString(climate);
        parcel.writeString(attire);
    }
}

