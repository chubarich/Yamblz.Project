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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CityDataModel that = (CityDataModel) o;

        if (Double.compare(that.getLat(), getLat()) != 0) return false;
        if (Double.compare(that.getLon(), getLon()) != 0) return false;
        if (getCityId() != null ? !getCityId().equals(that.getCityId()) : that.getCityId() != null)
            return false;
        if (getCity() != null ? !getCity().equals(that.getCity()) : that.getCity() != null)
            return false;
        return getCountry() != null ? getCountry().equals(that.getCountry()) : that.getCountry() == null;

    }
}
