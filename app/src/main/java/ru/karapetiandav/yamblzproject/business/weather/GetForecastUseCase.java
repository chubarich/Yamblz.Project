package ru.karapetiandav.yamblzproject.business.weather;


import java.util.List;

import io.reactivex.Single;
import ru.karapetiandav.yamblzproject.data.repositories.CitiesRepository;
import ru.karapetiandav.yamblzproject.data.repositories.WeatherRepository;
import ru.karapetiandav.yamblzproject.ui.entities.WeatherViewModel;
import ru.karapetiandav.yamblzproject.utils.mappers.WeatherMapper;

public class GetForecastUseCase {

    private WeatherRepository weatherRepository;
    private CitiesRepository citiesRepository;
    private WeatherMapper weatherMapper;

    public GetForecastUseCase(WeatherRepository weatherRepository,
                              CitiesRepository citiesRepository, WeatherMapper weatherMapper) {
        this.weatherRepository = weatherRepository;
        this.citiesRepository = citiesRepository;
        this.weatherMapper = weatherMapper;
    }

    public Single<List<WeatherViewModel>> execute(String cityId) {
        return citiesRepository.getCity(cityId)
                .flatMap(weatherRepository::getForecast)
                .map(weatherMapper::getWeatherViewModels);
    }

}
