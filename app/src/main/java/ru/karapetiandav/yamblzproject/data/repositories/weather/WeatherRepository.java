package ru.karapetiandav.yamblzproject.data.repositories.weather;


import io.reactivex.Single;
import ru.karapetiandav.yamblzproject.data.model.CityDataModel;
import ru.karapetiandav.yamblzproject.data.model.CurrentWeatherDataModel;
import ru.karapetiandav.yamblzproject.data.model.ForecastDataModel;

public interface WeatherRepository {

    Single<CurrentWeatherDataModel> getCurrentWeather(CityDataModel cityDataModel);
    Single<ForecastDataModel> getForecast(CityDataModel cityDataModel);

}
