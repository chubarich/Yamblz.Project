package ru.karapetiandav.yamblzproject.utils.mappers;


import java.util.ArrayList;
import java.util.List;

import ru.karapetiandav.yamblzproject.data.model.WeatherDataModel;
import ru.karapetiandav.yamblzproject.data.model.ForecastDataModel;
import ru.karapetiandav.yamblzproject.data.network.model.weather.Day;
import ru.karapetiandav.yamblzproject.data.network.model.weather.ForecastResponse;
import ru.karapetiandav.yamblzproject.data.network.model.weather.Main;
import ru.karapetiandav.yamblzproject.data.network.model.weather.Temp;
import ru.karapetiandav.yamblzproject.data.network.model.weather.Weather;
import ru.karapetiandav.yamblzproject.data.network.model.weather.WeatherResponse;
import ru.karapetiandav.yamblzproject.data.network.model.weather.Wind;
import ru.karapetiandav.yamblzproject.ui.entities.WeatherViewModel;
import ru.karapetiandav.yamblzproject.utils.Utils;

public class WeatherMapper {

    private Utils utils;

    public WeatherMapper(Utils utils) {
        this.utils = utils;
    }

    public WeatherDataModel getWeatherFromResponse(WeatherResponse weatherResponse) {
        Weather weather = weatherResponse.getWeather().get(0);
        Main main = weatherResponse.getMain();
        Wind wind = weatherResponse.getWind();
        return new WeatherDataModel(
                weather.getId(),
                main.getTemp(),
                main.getPressure(),
                main.getHumidity(),
                wind.getSpeed(),
                wind.getDeg()
        );
    }

    public List<ForecastDataModel> getForecastListFromResponse(ForecastResponse forecastResponse) {
        List<ForecastDataModel> forecasts = new ArrayList<>();
        for (Day day : forecastResponse.getDays()) {
            forecasts.add(getForecastFromDay(day));
        }
        return forecasts;
    }

    public ForecastDataModel getForecastFromDay(Day day) {
        ForecastDataModel.Builder builder = new ForecastDataModel.Builder();
        Weather weather = day.getWeather().get(0);
        Temp temp = day.getTemp();
        builder.setDate(day.getDt())
                .setWeatherId(weather.getId())
                .setMin(temp.getMin())
                .setMax(temp.getMax())
                .setPressure(day.getPressure())
                .setHumidity(day.getHumidity())
                .setSpeed(day.getSpeed())
                .setDeg(day.getDeg())
                .setMorn(temp.getMorn())
                .setDay(temp.getDay())
                .setEve(temp.getEve())
                .setNight(temp.getNight());
        return builder.build();
    }

    public List<WeatherViewModel> getWeatherViewModels(List<ForecastDataModel> forecastList) {
        List<WeatherViewModel> weatherViewModels = new ArrayList<>();
        for (ForecastDataModel forecast : forecastList) {
            weatherViewModels.add(getWeatherViewModelFromDataModel(forecast));
        }
        return weatherViewModels;
    }

    public WeatherViewModel getWeatherViewModelFromDataModel(ForecastDataModel forecast) {
        WeatherViewModel.Builder builder = new WeatherViewModel.Builder();
        builder.setColorId(utils.getColorResourceForWeatherId(forecast.getWeatherId()))
                .setIconId(utils.getIconResourceForWeatherId(forecast.getWeatherId()))
                .setMinTemp(utils.formatTemperature(forecast.getMin()))
                .setMaxTemp(utils.formatTemperature(forecast.getMax()))
                .setMorningTemp(utils.formatTemperature(forecast.getMorn()))
                .setDayTemp(utils.formatTemperature(forecast.getDay()))
                .setEveningTemp(utils.formatTemperature(forecast.getEve()))
                .setNightTemp(utils.formatTemperature(forecast.getNight()))
                .setPressure(utils.formatPressure(forecast.getPressure()))
                .setHumidity(utils.formatHumidity(forecast.getHumidity()))
                .setWind(utils.formatWind(forecast.getSpeed()))
                .setDayOfWeek(utils.formatDayOfWeek(forecast.getDate()));
        return builder.build();
    }
}
