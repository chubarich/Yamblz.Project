package ru.karapetiandav.yamblzproject.data.model;


public class CityDataModel {

    private String id;
    private String city;
    private String country;
    private float lat;
    private float lon;

    public CityDataModel() {
    }

    public CityDataModel(String id, String city, String country, float lat, float lon) {
        this.id = id;
        this.city = city;
        this.country = country;
        this.lat = lat;
        this.lon = lon;
    }

    public String getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public float getLat() {
        return lat;
    }

    public float getLon() {
        return lon;
    }
}
