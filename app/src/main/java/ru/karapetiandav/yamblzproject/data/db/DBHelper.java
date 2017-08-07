package ru.karapetiandav.yamblzproject.data.db;


import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import ru.karapetiandav.yamblzproject.data.model.CityDataModel;

public interface DBHelper {
    Completable saveCity(CityDataModel cityDataModel);
    Single<List<CityDataModel>> getCities();
}
