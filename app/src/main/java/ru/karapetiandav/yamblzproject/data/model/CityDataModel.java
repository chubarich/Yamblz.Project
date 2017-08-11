package ru.karapetiandav.yamblzproject.data.model;


public class CityDataModel {

    private String cityId;
    private String city;
    private String country;
    private double lat;
    private double lon;

    public CityDataModel() {
    }

    public CityDataModel(String cityId, String city, String country, double lat, double lon) {
        this.cityId = cityId;
        this.city = city;
        this.country = country;
        this.lat = lat;
        this.lon = lon;
    }

    public String getCityId() {
        return cityId;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
