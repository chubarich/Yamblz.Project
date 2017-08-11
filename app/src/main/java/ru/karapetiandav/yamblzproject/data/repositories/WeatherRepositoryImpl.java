package ru.karapetiandav.yamblzproject.data.repositories;


import java.util.List;

import io.reactivex.Single;
import ru.karapetiandav.yamblzproject.data.model.CityDataModel;
import ru.karapetiandav.yamblzproject.data.model.WeatherDataModel;
import ru.karapetiandav.yamblzproject.data.model.ForecastDataModel;
import ru.karapetiandav.yamblzproject.data.network.NetworkHelper;
import ru.karapetiandav.yamblzproject.utils.mappers.WeatherMapper;

public class WeatherRepositoryImpl implements WeatherRepository {

    private NetworkHelper networkHelper;
    private WeatherMapper weatherMapper;

    public WeatherRepositoryImpl(NetworkHelper networkHelper,
                                 WeatherMapper weatherMapper) {
        this.networkHelper = networkHelper;
        this.weatherMapper = weatherMapper;
    }

    @Override
    public Single<WeatherDataModel> getCurrentWeather(CityDataModel city) {
        return networkHelper.getCurrentWeather(
                String.valueOf(city.getLat()), String.valueOf(city.getLon()))
                .map(weatherMapper::getWeatherFromResponse);
    }

    @Override
    public Single<List<ForecastDataModel>> getForecast(CityDataModel city) {
        return networkHelper.getForecast(
                String.valueOf(city.getLat()), String.valueOf(city.getLon()))
                .map(weatherMapper::getForecastListFromResponse);
    }
}
