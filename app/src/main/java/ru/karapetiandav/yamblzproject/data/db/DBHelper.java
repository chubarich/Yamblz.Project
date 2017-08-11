package ru.karapetiandav.yamblzproject.data.db;


import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import ru.karapetiandav.yamblzproject.data.model.CityDataModel;

public interface DBHelper {
    Completable saveCity(CityDataModel cityDataModel);
    Observable<CityDataModel> subscribe();
    Single<CityDataModel> getCity(String cityId);
}
