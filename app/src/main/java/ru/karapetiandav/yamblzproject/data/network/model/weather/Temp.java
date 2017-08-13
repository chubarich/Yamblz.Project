
package ru.karapetiandav.yamblzproject.data.network.model.weather;

public class Temp {

    private double day;
    private double min;
    private double max;
    private double night;
    private double eve;
    private double morn;

    public double getDay() {
        return day;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public double getNight() {
        return night;
    }

    public double getEve() {
        return eve;
    }

    public double getMorn() {
        return morn;
    }

    public void setDay(double day) {
        this.day = day;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public void setNight(double night) {
        this.night = night;
    }

    public void setEve(double eve) {
        this.eve = eve;
    }

    public void setMorn(double morn) {
        this.morn = morn;
    }
}
