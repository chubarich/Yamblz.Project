package ru.karapetiandav.yamblzproject.business.cities;


import io.reactivex.Observable;
import ru.karapetiandav.yamblzproject.data.repositories.CitiesRepository;
import ru.karapetiandav.yamblzproject.data.repositories.WeatherRepository;
import ru.karapetiandav.yamblzproject.ui.entities.CityWeatherViewModel;
import ru.karapetiandav.yamblzproject.utils.mappers.CityWeatherMapper;
import ru.karapetiandav.yamblzproject.utils.rx.RxSchedulers;

public class CitiesInteractorImpl implements CitiesInteractor {

    private CitiesRepository citiesRepository;
    private WeatherRepository weatherRepository;
    private RxSchedulers schedulers;
    private CityWeatherMapper mapper;


    public CitiesInteractorImpl(CitiesRepository citiesRepository,
                                WeatherRepository weatherRepository,
                                RxSchedulers schedulers,
                                CityWeatherMapper mapper) {
        this.citiesRepository = citiesRepository;
        this.weatherRepository = weatherRepository;
        this.schedulers = schedulers;
        this.mapper = mapper;
    }

    @Override
    public Observable<CityWeatherViewModel> subscribeOnCityWeathers() {
        return citiesRepository.subscribeOnCities()
                .flatMap(Observable::fromIterable)
                .flatMap(city -> weatherRepository.getCurrentWeather(city)
                        .toObservable()
                        .map(currentWeather -> mapper.getCityWeatherViewModel(city, currentWeather)));
    }
}
