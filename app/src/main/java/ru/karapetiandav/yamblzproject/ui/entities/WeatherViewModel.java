package ru.karapetiandav.yamblzproject.ui.entities;


public final class WeatherViewModel {

    private final int imageResourceId;
    private final String temp;
    private final String pressure;
    private final String humidity;

    public WeatherViewModel(int imageResourceId, String temp, String pressure, String humidity) {
        this.imageResourceId = imageResourceId;
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getTemp() {
        return temp;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }

}
