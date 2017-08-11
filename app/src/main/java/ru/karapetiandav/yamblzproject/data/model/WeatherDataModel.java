package ru.karapetiandav.yamblzproject.data.model;


public class WeatherDataModel {

    private int weatherId;
    private double temp;
    private String cityId;

    public WeatherDataModel() {
    }

    public WeatherDataModel(int weatherId, double temp, String cityId) {
        this.weatherId = weatherId;
        this.temp = temp;
        this.cityId = cityId;
    }

    public int getWeatherId() {
        return weatherId;
    }

    public double getTemp() {
        return temp;
    }

    public String getCityId() {
        return cityId;
    }
}
