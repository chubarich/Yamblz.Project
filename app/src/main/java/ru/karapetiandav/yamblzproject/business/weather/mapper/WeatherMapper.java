package ru.karapetiandav.yamblzproject.business.weather.mapper;


import android.support.annotation.NonNull;

import ru.karapetiandav.yamblzproject.data.model.CurrentWeatherDataModel;
import ru.karapetiandav.yamblzproject.data.network.model.weather.Main;
import ru.karapetiandav.yamblzproject.data.network.model.weather.Weather;
import ru.karapetiandav.yamblzproject.data.network.model.weather.WeatherResponse;
import ru.karapetiandav.yamblzproject.ui.entities.WeatherViewModel;
import ru.karapetiandav.yamblzproject.utils.Utils;

public class WeatherMapper {

    private Utils utils;

    public WeatherMapper(Utils utils) {
        this.utils = utils;
    }

    @NonNull
    public WeatherViewModel from(@NonNull CurrentWeatherDataModel weatherData)
            throws IllegalArgumentException {
        if (weatherData.getTemp() == 0f && weatherData.getPressure() == 0){
            throw new IllegalArgumentException("Converting null weather data");
        }
        int drawableId = utils.getIconResourceForWeatherId(weatherData.getWeatherId());
        String temp = utils.formatTemperature(weatherData.getTemp());
        String pressure = utils.formatPressure(weatherData.getPressure());
        String humidity = utils.formatHumidity(weatherData.getHumidity());
        return new WeatherViewModel(drawableId, temp, pressure, humidity);
    }

    public CurrentWeatherDataModel from(WeatherResponse weatherResponse) {
        Weather weather = weatherResponse.getWeather().get(0);
        Main main = weatherResponse.getMain();
        return new CurrentWeatherDataModel(
                weather.getId(),
                main.getTemp(),
                main.getPressure(),
                main.getHumidity());
    }

}
