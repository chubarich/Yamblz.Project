
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

    public void setDt(int dt) {
        this.dt = dt;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setDeg(double deg) {
        this.deg = deg;
    }
}
