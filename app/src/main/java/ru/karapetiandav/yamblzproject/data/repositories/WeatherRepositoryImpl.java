package ru.karapetiandav.yamblzproject.data.repositories;


import java.util.List;

import io.reactivex.Single;
import ru.karapetiandav.yamblzproject.data.db.DBHelper;
import ru.karapetiandav.yamblzproject.data.model.CityDataModel;
import ru.karapetiandav.yamblzproject.data.model.ForecastDataModel;
import ru.karapetiandav.yamblzproject.data.model.WeatherDataModel;
import ru.karapetiandav.yamblzproject.data.network.NetworkHelper;
import ru.karapetiandav.yamblzproject.utils.mappers.WeatherMapper;

public class WeatherRepositoryImpl implements WeatherRepository {

    private NetworkHelper networkHelper;
    private WeatherMapper weatherMapper;
    private DBHelper dbHelper;

    public WeatherRepositoryImpl(NetworkHelper networkHelper, WeatherMapper weatherMapper,
                                 DBHelper dbHelper) {
        this.networkHelper = networkHelper;
        this.weatherMapper = weatherMapper;
        this.dbHelper = dbHelper;
    }

    @Override
    public Single<WeatherDataModel> getCurrentWeather(CityDataModel city) {
        return networkHelper.getCurrentWeather(
                String.valueOf(city.getLat()), String.valueOf(city.getLon()))
                .map(response -> weatherMapper.getWeatherFromResponse(response, city.getCityId()))
                .doOnSuccess(dataModel -> dbHelper.saveWeather(dataModel).subscribe())
                .onErrorResumeNext(ignore -> dbHelper.getWeather(city.getCityId()));
    }

    @Override
    public Single<List<ForecastDataModel>> getForecast(CityDataModel city) {
        return networkHelper.getForecast(
                String.valueOf(city.getLat()), String.valueOf(city.getLon()))
                .map(response ->
                        weatherMapper.getForecastListFromResponse(response, city.getCityId()))
                .doOnSuccess(forecastList -> dbHelper.saveForecastList(forecastList).subscribe())
                .onErrorResumeNext(ignore -> dbHelper.getForecastList(city.getCityId()));
    }
}
