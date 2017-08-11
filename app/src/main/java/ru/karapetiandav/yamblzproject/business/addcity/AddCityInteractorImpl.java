package ru.karapetiandav.yamblzproject.business.addcity;


import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import ru.karapetiandav.yamblzproject.data.repositories.CitiesRepository;
import ru.karapetiandav.yamblzproject.ui.entities.CityViewModel;
import ru.karapetiandav.yamblzproject.utils.mappers.CityMapper;

public class AddCityInteractorImpl implements AddCityInteractor {

    private CitiesRepository citiesRepository;
    private CityMapper cityMapper;

    public AddCityInteractorImpl(CitiesRepository citiesRepository, CityMapper cityMapper) {
        this.citiesRepository = citiesRepository;
        this.cityMapper = cityMapper;
    }

    @NonNull
    @Override
    public Observable<List<CityViewModel>> getCitiesMatches(String city) {
        if (TextUtils.isEmpty(city)) {
            return Observable.fromCallable(ArrayList::new);
        }
        return citiesRepository.getCitiesMatches(city)
                .toObservable()
                .map(cityMapper::getViewModelList);
    }

    @Override
    public Completable chooseCity(CityViewModel city) {
        return citiesRepository.chooseCity(cityMapper.getCityDataModel(city));
    }
}
