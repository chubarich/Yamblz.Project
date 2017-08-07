package ru.karapetiandav.yamblzproject.ui.addcity.model;


public final class CityViewModel {

    private final String cityId;
    private final String cityName;
    private final String country;

    public CityViewModel(String cityId, String cityName, String country) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.country = country;
    }

    public String getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountry() {
        return country;
    }
}
