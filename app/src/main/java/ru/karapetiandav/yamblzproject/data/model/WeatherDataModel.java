package ru.karapetiandav.yamblzproject.data.model;


import java.util.Date;

import ru.karapetiandav.yamblzproject.data.network.model.weather.Main;
import ru.karapetiandav.yamblzproject.data.network.model.weather.Weather;
import ru.karapetiandav.yamblzproject.data.network.model.weather.WeatherResponse;

public class WeatherDataModel {

    private int weatherId;
    private float temp;
    private float pressure;
    private int humidity;
    private long time;

    public WeatherDataModel(int weatherId, float temp, float pressure, int humidity, long time) {
        this.weatherId = weatherId;
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.time = time;
    }

    public static WeatherDataModel valueOf(WeatherResponse weatherResponse) {
        Weather weather = weatherResponse.getWeather().get(0);
        Main main = weatherResponse.getMain();
        Date date = new Date();
        return new WeatherDataModel(
                weather.getId(),
                main.getTemp(),
                main.getPressure(),
                main.getHumidity(),
                date.getTime());
    }

    public static WeatherDataModel getEmpty() {
        return new WeatherDataModel(0, 0f, 0f, 0, 0L);
    }

    public int getWeatherId() {
        return weatherId;
    }

    public float getTemp() {
        return temp;
    }

    public float getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public long getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "WeatherDataModel{" +
                "weatherId=" + weatherId +
                ", temp=" + temp +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", time=" + time +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeatherDataModel that = (WeatherDataModel) o;

        if (weatherId != that.weatherId) return false;
        if (Float.compare(that.temp, temp) != 0) return false;
        if (Float.compare(that.pressure, pressure) != 0) return false;
        if (humidity != that.humidity) return false;
        return time == that.time;

    }
}
