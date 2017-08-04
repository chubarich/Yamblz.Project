package ru.karapetiandav.yamblzproject.business.addcity.interactor;


import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import ru.karapetiandav.yamblzproject.business.addcity.mapper.CityMapper;
import ru.karapetiandav.yamblzproject.data.repositories.addcity.AddCityRepository;
import ru.karapetiandav.yamblzproject.ui.addcity.model.CityViewModel;
import ru.karapetiandav.yamblzproject.utils.LanguageUtils;

public class AddCityInteractorImpl implements AddCityInteractor {

    private AddCityRepository addCityRepository;
    private CityMapper cityMapper;
    private LanguageUtils languageUtils;

    public AddCityInteractorImpl(AddCityRepository addCityRepository, CityMapper cityMapper,
                                 LanguageUtils languageUtils) {
        this.addCityRepository = addCityRepository;
        this.cityMapper = cityMapper;
        this.languageUtils = languageUtils;
    }

    @NonNull
    @Override
    public Observable<List<CityViewModel>> getCitiesMatches(String city) {
        return addCityRepository
                .getCitiesMatches(city, languageUtils.getSupportedLanguageByText(city))
                .toObservable()
                .map(cityMapper::getViewModelList);
    }

    @Override
    public Completable saveCity(CityViewModel city) {
        return addCityRepository.saveCity(cityMapper.getDataModel(city));
    }
}
