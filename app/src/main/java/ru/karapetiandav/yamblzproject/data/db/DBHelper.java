package ru.karapetiandav.yamblzproject.data.db;


import io.reactivex.Completable;
import io.reactivex.Observable;
import ru.karapetiandav.yamblzproject.data.model.CityDataModel;

public interface DBHelper {
    Completable saveCity(CityDataModel cityDataModel);
    Observable<CityDataModel> subscribe();
}
