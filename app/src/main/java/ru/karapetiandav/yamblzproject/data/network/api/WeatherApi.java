package ru.karapetiandav.yamblzproject.data.network.api;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.karapetiandav.yamblzproject.data.network.model.weather.ForecastResponse;
import ru.karapetiandav.yamblzproject.data.network.model.weather.WeatherResponse;

public interface WeatherApi {

    @GET(WeatherApiEndpoints.WEATHER)
    Single<WeatherResponse> getCurrentWeather(@Query(WeatherApiEndpoints.LATITUDE) String lat,
                                              @Query(WeatherApiEndpoints.LONGITUDE) String lon,
                                              @Query(WeatherApiEndpoints.KEY) String key);

    @GET(WeatherApiEndpoints.FORECAST)
    Single<ForecastResponse> getForecast(@Query(WeatherApiEndpoints.LATITUDE) String lat,
                                         @Query(WeatherApiEndpoints.LONGITUDE) String lon,
                                         @Query(WeatherApiEndpoints.COUNT) String count,
                                         @Query(WeatherApiEndpoints.KEY) String key);

}