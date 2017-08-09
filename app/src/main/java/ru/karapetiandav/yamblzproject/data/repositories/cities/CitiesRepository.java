package ru.karapetiandav.yamblzproject.data.repositories.cities;


import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import ru.karapetiandav.yamblzproject.data.model.CityDataModel;

public interface CitiesRepository {
    Single<List<CityDataModel>> getCitiesList();
    Single<List<CityDataModel>> getCitiesMatches(String input);
    Completable chooseCity(CityDataModel cityDataModel);
}
