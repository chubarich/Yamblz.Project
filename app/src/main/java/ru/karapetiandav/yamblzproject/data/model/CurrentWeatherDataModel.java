package ru.karapetiandav.yamblzproject.data.model;


public class CurrentWeatherDataModel {

    private int weatherId;
    private double temp;
    private double pressure;
    private double humidity;

    public CurrentWeatherDataModel(int weatherId, double temp, double pressure, int humidity) {
        this.weatherId = weatherId;
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public static CurrentWeatherDataModel getEmpty() {
        return new CurrentWeatherDataModel(0, 0f, 0f, 0);
    }

    public int getWeatherId() {
        return weatherId;
    }

    public double getTemp() {
        return temp;
    }

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }

}
