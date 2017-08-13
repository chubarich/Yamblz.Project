package ru.karapetiandav.yamblzproject.business;


import io.reactivex.Observable;
import ru.karapetiandav.yamblzproject.data.repositories.CitiesRepository;
import ru.karapetiandav.yamblzproject.data.repositories.WeatherRepository;
import ru.karapetiandav.yamblzproject.ui.entities.CityWeatherViewModel;
import ru.karapetiandav.yamblzproject.utils.mappers.CityWeatherMapper;

public class SubscribeOnCityWeathersUseCase{

    private CitiesRepository citiesRepository;
    private WeatherRepository weatherRepository;
    private CityWeatherMapper mapper;


    public SubscribeOnCityWeathersUseCase(CitiesRepository citiesRepository,
                                          WeatherRepository weatherRepository,
                                          CityWeatherMapper mapper) {
        this.citiesRepository = citiesRepository;
        this.weatherRepository = weatherRepository;
        this.mapper = mapper;
    }

    public Observable<CityWeatherViewModel> execute() {
        return citiesRepository.subscribeOnCities()
                .flatMap(Observable::fromIterable)
                .flatMap(city -> weatherRepository.getCurrentWeather(city)
                        .toObservable()
                        .map(weather -> mapper.getCityWeatherViewModel(city, weather)));
    }
}
