package ru.karapetiandav.yamblzproject.data.db;


import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import ru.karapetiandav.yamblzproject.data.model.CityDataModel;
import ru.karapetiandav.yamblzproject.data.model.ForecastDataModel;
import ru.karapetiandav.yamblzproject.data.model.WeatherDataModel;

public interface DBHelper {

    Observable<List<CityDataModel>> subscribeOnCityChanges();
    Completable saveCity(CityDataModel cityDataModel);
    Single<CityDataModel> getCity(String cityId);
    Completable saveWeather(WeatherDataModel weather);
    Single<WeatherDataModel> getWeather(String cityId);
    Completable saveForecastList(List<ForecastDataModel> forecastList);
    Single<List<ForecastDataModel>> getForecastList(String cityId);
    Completable removeCity(String cityId);

}
