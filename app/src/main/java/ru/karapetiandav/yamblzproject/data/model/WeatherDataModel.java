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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeatherDataModel that = (WeatherDataModel) o;

        if (getWeatherId() != that.getWeatherId()) return false;
        if (Double.compare(that.getTemp(), getTemp()) != 0) return false;
        return getCityId() != null ? getCityId().equals(that.getCityId()) : that.getCityId() == null;

    }
}
