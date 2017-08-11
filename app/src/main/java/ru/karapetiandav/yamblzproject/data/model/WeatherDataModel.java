package ru.karapetiandav.yamblzproject.data.model;


public class WeatherDataModel {

    private int weatherId;
    private double temp;
    private double pressure;
    private double humidity;
    private double windSpeed;
    private double windDir;

    public WeatherDataModel() {
    }

    public WeatherDataModel(int weatherId, double temp, double pressure, double humidity,
                            double windSpeed, double windDir) {
        this.weatherId = weatherId;
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windDir = windDir;
    }

    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getWindDir() {
        return windDir;
    }

    public void setWindDir(double windDir) {
        this.windDir = windDir;
    }
}
