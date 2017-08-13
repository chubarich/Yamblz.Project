
package ru.karapetiandav.yamblzproject.data.network.model.weather;

import java.util.List;

public class WeatherResponse {

    private List<Weather> weather = null;
    private Main main;

    public List<Weather> getWeather() {
        return weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }
}
