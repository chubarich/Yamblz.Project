package ru.karapetiandav.yamblzproject.business;


import android.text.TextUtils;

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
        if (TextUtils.isEmpty(city)) {
            return Observable.fromCallable(ArrayList::new);
        }
        return citiesRepository.getCitiesMatches(city)
                .toObservable()
                .map(cityMapper::getViewModelList);
    }
}
