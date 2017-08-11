
package ru.karapetiandav.yamblzproject.data.network.model.weather;

import java.util.List;

public class WeatherResponse {

    private List<Weather> weather = null;
    private Main main;
    private Wind wind;

    public List<Weather> getWeather() {
        return weather;
    }

    public Main getMain() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }
}
