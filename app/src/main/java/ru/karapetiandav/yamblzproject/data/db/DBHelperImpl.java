package ru.karapetiandav.yamblzproject.data.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.subjects.BehaviorSubject;
import ru.karapetiandav.yamblzproject.data.model.CityDataModel;
import ru.karapetiandav.yamblzproject.data.model.ForecastDataModel;
import ru.karapetiandav.yamblzproject.data.model.WeatherDataModel;
import ru.karapetiandav.yamblzproject.di.qualifiers.DbName;
import ru.karapetiandav.yamblzproject.di.qualifiers.DbVersion;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class DBHelperImpl extends SQLiteOpenHelper implements DBHelper {

    private static final String BY_CITY_ID = "cityId = ?";
    private BehaviorSubject<List<CityDataModel>> citiesSubject = BehaviorSubject.create();

    public DBHelperImpl(Context context,@DbName String name,
                        @DbVersion int version) {
        super(context, name, null, version);
    }

    static {
        cupboard().register(CityDataModel.class);
        cupboard().register(WeatherDataModel.class);
        cupboard().register(ForecastDataModel.class);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        cupboard().withDatabase(db).createTables();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        cupboard().withDatabase(db).upgradeTables();
    }

    @Override
    public Observable<List<CityDataModel>> subscribeOnCityChanges() {
        citiesSubject.onNext(getCityList());
        return citiesSubject;
    }

    @Override
    public Completable saveCity(CityDataModel city) {
        return Completable.fromCallable(() -> {
            cupboard().withDatabase(getWritableDatabase())
                    .delete(CityDataModel.class, BY_CITY_ID, city.getCityId());
            cupboard().withDatabase(getWritableDatabase()).put(city);
            citiesSubject.onNext(getCityList());
            return Completable.complete();
        });
    }

    @Override
    public Single<CityDataModel> getCity(String cityId) {
        return Single.fromCallable(() -> cupboard().withDatabase(getReadableDatabase())
                .query(CityDataModel.class)
                .withSelection(BY_CITY_ID, cityId)
                .get());
    }

    @Override
    public Completable saveWeather(WeatherDataModel weather) {
        return Completable.fromCallable(() -> {
            cupboard().withDatabase(getWritableDatabase())
                    .delete(WeatherDataModel.class, BY_CITY_ID, weather.getCityId());
            cupboard().withDatabase(getWritableDatabase()).put(weather);
            return Completable.complete();
        });
    }

    @Override
    public Single<WeatherDataModel> getWeather(String cityId) {
       return Single.fromCallable(() -> cupboard().withDatabase(getReadableDatabase())
                .query(WeatherDataModel.class)
                .withSelection(BY_CITY_ID, cityId)
                .get());
    }

    @Override
    public Completable saveForecastList(List<ForecastDataModel> forecastList) {
        return Completable.fromCallable(() -> {
            cupboard().withDatabase(getWritableDatabase())
                    .delete(ForecastDataModel.class, BY_CITY_ID, forecastList.get(0).getCityId());
            cupboard().withDatabase(getWritableDatabase()).put(forecastList);
            return Completable.complete();
        });
    }

    @Override
    public Single<List<ForecastDataModel>> getForecastList(String cityId) {
        return Single.fromCallable(() -> cupboard().withDatabase(getReadableDatabase())
                .query(ForecastDataModel.class)
                .withSelection(BY_CITY_ID, cityId)
                .list());
    }

    @Override
    public Completable removeCity(String cityId) {
        return Completable.fromCallable(() -> {
            cupboard().withDatabase(getWritableDatabase())
                    .delete(CityDataModel.class, BY_CITY_ID, cityId);
            cupboard().withDatabase(getWritableDatabase())
                    .delete(WeatherDataModel.class, BY_CITY_ID, cityId);
            cupboard().withDatabase(getWritableDatabase())
                    .delete(ForecastDataModel.class, BY_CITY_ID, cityId);
            return Completable.complete();
        });
    }

    private List<CityDataModel> getCityList() {
        return cupboard().withDatabase(getReadableDatabase())
                .query(CityDataModel.class).list();
    }
}
