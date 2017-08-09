package ru.karapetiandav.yamblzproject.business.cities;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import ru.karapetiandav.yamblzproject.data.model.CityDataModel;
import ru.karapetiandav.yamblzproject.data.repositories.cities.CitiesRepository;
import ru.karapetiandav.yamblzproject.data.repositories.weather.WeatherRepository;
import ru.karapetiandav.yamblzproject.ui.cities.model.CityWeatherViewModel;
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
    public Single<List<CityWeatherViewModel>> loadCityWeatherList() {
        return citiesRepository.getCitiesList().flatMap(this::getCityWeatherList);
    }

    private Single<List<CityWeatherViewModel>> getCityWeatherList(List<CityDataModel> cities) {
        List<CityWeatherViewModel> result = new ArrayList<>();
        for(CityDataModel city : cities) {
            weatherRepository.getCurrentWeather(city)
                    .subscribeOn(schedulers.getIOScheduler())
                    .observeOn(schedulers.getMainThreadScheduler())
                    .subscribe(currentWeatherDataModel ->
                            result.add(mapper.get(city, currentWeatherDataModel)));
        }
        return Single.fromCallable(() -> result);
    }
}
