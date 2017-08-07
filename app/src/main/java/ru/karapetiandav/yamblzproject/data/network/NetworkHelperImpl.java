package ru.karapetiandav.yamblzproject.data.network;


import io.reactivex.Single;
import ru.karapetiandav.yamblzproject.BuildConfig;
import ru.karapetiandav.yamblzproject.data.network.api.CityApi;
import ru.karapetiandav.yamblzproject.data.network.api.CityApiEndpoints;
import ru.karapetiandav.yamblzproject.data.network.api.WeatherApi;
import ru.karapetiandav.yamblzproject.data.network.model.city.autocomplete.CitiesResponse;
import ru.karapetiandav.yamblzproject.data.network.model.city.details.CityDetails;
import ru.karapetiandav.yamblzproject.data.network.model.weather.WeatherResponse;

public class NetworkHelperImpl implements NetworkHelper {

    private WeatherApi weatherApi;
    private CityApi cityApi;

    public NetworkHelperImpl(WeatherApi weatherApi, CityApi cityApi) {
        this.weatherApi = weatherApi;
        this.cityApi = cityApi;
    }

    @Override
    public Single<CitiesResponse> getCities(String input) {
        return cityApi.getCities(input, BuildConfig.GOOGLE_PLACES_API_KEY,
                CityApiEndpoints.TYPES_VALUE);
    }

    @Override
    public Single<CityDetails> getCityDetails(String cityId) {
        return cityApi.getCityDetails(cityId, BuildConfig.GOOGLE_PLACES_API_KEY);
    }

    @Override
    public Single<WeatherResponse> loadWeather(int cityId) {
        return null;
    }
}
