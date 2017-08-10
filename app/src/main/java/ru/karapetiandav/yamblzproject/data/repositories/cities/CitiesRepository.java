package ru.karapetiandav.yamblzproject.data.repositories.cities;


import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import ru.karapetiandav.yamblzproject.data.model.CityDataModel;

public interface CitiesRepository {
    Observable<CityDataModel> subscribeOnCities();
    Single<List<CityDataModel>> getCitiesMatches(String input);
    Completable chooseCity(CityDataModel cityDataModel);
}
