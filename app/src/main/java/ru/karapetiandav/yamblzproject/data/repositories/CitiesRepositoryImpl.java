package ru.karapetiandav.yamblzproject.data.repositories;


import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import ru.karapetiandav.yamblzproject.data.db.DBHelper;
import ru.karapetiandav.yamblzproject.data.model.CityDataModel;
import ru.karapetiandav.yamblzproject.data.network.NetworkHelper;
import ru.karapetiandav.yamblzproject.utils.mappers.CityMapper;

public class CitiesRepositoryImpl implements CitiesRepository {

    private DBHelper dbHelper;
    private NetworkHelper networkHelper;
    private CityMapper mapper;

    public CitiesRepositoryImpl(DBHelper dbHelper, NetworkHelper networkHelper, CityMapper mapper) {
        this.dbHelper = dbHelper;
        this.networkHelper = networkHelper;
        this.mapper = mapper;
    }

    @Override
    public Observable<List<CityDataModel>> subscribeOnCities() {
        return dbHelper.subscribeOnCityChanges();
    }

    @Override
    public Single<List<CityDataModel>> getCitiesMatches(String text) {
        return networkHelper.getCities(text)
                .map(cityResponse -> mapper.getCitiesFromResponse(cityResponse));
    }

    @Override
    public Completable chooseCity(CityDataModel city) {
        return Completable.fromSingle(networkHelper.getCityDetails(city.getCityId())
                .map(cityDetails -> mapper.getCityDataModelWithCoords(city, cityDetails))
                .doOnSuccess(cityDataModel -> dbHelper.saveCity(cityDataModel).subscribe()));
    }

    @Override
    public Single<CityDataModel> getCity(String cityId) {
        return dbHelper.getCity(cityId);
    }

    @Override
    public Completable removeCity(String cityId) {
        return dbHelper.removeCity(cityId);
    }
}
