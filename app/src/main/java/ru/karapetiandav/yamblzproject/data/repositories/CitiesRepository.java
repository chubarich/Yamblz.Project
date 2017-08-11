package ru.karapetiandav.yamblzproject.data.repositories;


import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import ru.karapetiandav.yamblzproject.data.model.CityDataModel;

public interface CitiesRepository {
    Observable<List<CityDataModel>> subscribeOnCities();
    Single<List<CityDataModel>> getCitiesMatches(String input);
    Completable chooseCity(CityDataModel cityDataModel);
    Single<CityDataModel> getCity(String cityId);
}
