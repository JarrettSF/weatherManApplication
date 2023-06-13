package com.example.assignment_three_flowers;

public class TripDetails {

    public TripDetails (){

        continent = " Select a Continent";

        city = "Select a city";

        people = "How many people are traveling?";


    }

    private String continent;

    private String city;

    private String people;

    public String getContinent() {return continent;}

    public void setContinent(String continent) {
        this.continent = continent;
    }


    public String getCity() {return city;}

    public void setCity(String city) {
        this.city = city;
    }

    public String getPeople() {return people;}

    public void setPeople(String people) {
        this.people = people;
    }
}
