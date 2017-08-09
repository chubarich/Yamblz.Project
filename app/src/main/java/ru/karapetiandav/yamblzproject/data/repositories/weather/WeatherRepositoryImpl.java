package ru.karapetiandav.yamblzproject.data.repositories.weather;


import io.reactivex.Single;
import ru.karapetiandav.yamblzproject.business.weather.mapper.WeatherMapper;
import ru.karapetiandav.yamblzproject.data.model.CityDataModel;
import ru.karapetiandav.yamblzproject.data.model.CurrentWeatherDataModel;
import ru.karapetiandav.yamblzproject.data.model.ForecastDataModel;
import ru.karapetiandav.yamblzproject.data.network.NetworkHelper;

public class WeatherRepositoryImpl implements WeatherRepository {

    private NetworkHelper networkHelper;
    private WeatherMapper weatherMapper;

    public WeatherRepositoryImpl(NetworkHelper networkHelper,
                                 WeatherMapper weatherMapper) {
        this.networkHelper = networkHelper;
        this.weatherMapper = weatherMapper;
    }

    @Override
    public Single<CurrentWeatherDataModel> getCurrentWeather(CityDataModel cityDataModel) {
        return networkHelper.getCurrentWeather(String.valueOf(cityDataModel.getLat()),
                String.valueOf(cityDataModel.getLon()))
                .map(weatherMapper::from);
    }

    @Override
    public Single<ForecastDataModel> getForecast(CityDataModel cityDataModel) {
        return null;
    }
}
