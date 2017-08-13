package ru.karapetiandav.yamblzproject.business.usecases;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import ru.karapetiandav.yamblzproject.data.repositories.CitiesRepository;
import ru.karapetiandav.yamblzproject.ui.entities.CityViewModel;
import ru.karapetiandav.yamblzproject.utils.mappers.CityMapper;

public class GetCitiesMatchesUseCase {

    private CitiesRepository citiesRepository;
    private CityMapper cityMapper;

    public GetCitiesMatchesUseCase(CitiesRepository citiesRepository, CityMapper cityMapper) {
        this.citiesRepository = citiesRepository;
        this.cityMapper = cityMapper;
    }

    public Observable<List<CityViewModel>> execute(String city) {
        if (city == null || city.trim().length() == 0) {
            return Observable.fromCallable(ArrayList::new);
        }
        return citiesRepository.getCitiesMatches(city)
                .toObservable()
                .map(cityMapper::getViewModelList);
    }
}
