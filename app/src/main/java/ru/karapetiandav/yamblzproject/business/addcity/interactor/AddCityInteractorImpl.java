package ru.karapetiandav.yamblzproject.business.addcity.interactor;


import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import ru.karapetiandav.yamblzproject.data.repositories.addcity.AddCityRepository;
import ru.karapetiandav.yamblzproject.ui.addcity.model.CityViewModel;
import ru.karapetiandav.yamblzproject.utils.mappers.CityMapper;

public class AddCityInteractorImpl implements AddCityInteractor {

    private AddCityRepository addCityRepository;
    private CityMapper cityMapper;

    public AddCityInteractorImpl(AddCityRepository addCityRepository, CityMapper cityMapper) {
        this.addCityRepository = addCityRepository;
        this.cityMapper = cityMapper;
    }

    @NonNull
    @Override
    public Observable<List<CityViewModel>> getCitiesMatches(String city) {
        if (TextUtils.isEmpty(city)) {
            return Observable.fromCallable(ArrayList::new);
        }
        return addCityRepository.getCitiesMatches(city)
                .toObservable()
                .map(cityMapper::getViewModelList);
    }

    @Override
    public Completable chooseCity(CityViewModel city) {
        return addCityRepository.chooseCity(cityMapper.getCityDataModel(city));
    }
}
