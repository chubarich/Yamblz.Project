package ru.karapetiandav.yamblzproject.data.network;


import io.reactivex.Single;
import ru.karapetiandav.yamblzproject.data.network.model.city.autocomplete.CitiesResponse;
import ru.karapetiandav.yamblzproject.data.network.model.city.details.CityDetails;
import ru.karapetiandav.yamblzproject.data.network.model.weather.ForecastResponse;
import ru.karapetiandav.yamblzproject.data.network.model.weather.WeatherResponse;

public interface NetworkHelper {

    Single<WeatherResponse> getCurrentWeather(String lat, String lon);
    Single<CitiesResponse> getCities(String input);
    Single<CityDetails> getCityDetails(String cityId);
    Single<ForecastResponse> getForecast(String lat, String lon);

}
