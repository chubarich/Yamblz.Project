package ru.karapetiandav.yamblzproject.data.repositories;


import java.util.List;

import io.reactivex.Single;
import ru.karapetiandav.yamblzproject.data.model.CityDataModel;
import ru.karapetiandav.yamblzproject.data.model.WeatherDataModel;
import ru.karapetiandav.yamblzproject.data.model.ForecastDataModel;

public interface WeatherRepository {

    Single<WeatherDataModel> getCurrentWeather(CityDataModel cityDataModel);
    Single<List<ForecastDataModel>> getForecast(CityDataModel cityDataModel);

}
