
package ru.karapetiandav.yamblzproject.data.network.model.weather;

import java.util.ArrayList;
import java.util.List;

public class Day {

    private int dt;
    private Temp temp;
    private double pressure;
    private double humidity;
    private List<Weather> weather = new ArrayList<>();
    private double speed;
    private double deg;

    public int getDt() {
        return dt;
    }

    public Temp getTemp() {
        return temp;
    }

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDeg() {
        return deg;
    }
}
