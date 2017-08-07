package ru.karapetiandav.yamblzproject.data.repositories.addcity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import ru.karapetiandav.yamblzproject.data.db.DBHelper;
import ru.karapetiandav.yamblzproject.data.model.CityDataModel;
import ru.karapetiandav.yamblzproject.data.network.NetworkHelper;
import ru.karapetiandav.yamblzproject.utils.mappers.CityMapper;

public class AddCityRepositoryImpl implements AddCityRepository {

    private DBHelper dbHelper;
    private NetworkHelper networkHelper;
    private CityMapper mapper;

    public AddCityRepositoryImpl(NetworkHelper networkHelper,
                                 DBHelper dbHelper, CityMapper mapper) {
        this.networkHelper = networkHelper;
        this.dbHelper = dbHelper;
        this.mapper = mapper;
    }

    @Override
    public Single<List<CityDataModel>> getCitiesMatches(String text) {
        //todo здесь посылаем конкретные ошибки
        return networkHelper.getCities(text)
                .map(cityResponse -> mapper.getCitiesFromResponse(cityResponse));
    }

    @Override
    public Completable chooseCity(CityDataModel cityDataModel) {
        return Completable.fromSingle(networkHelper.getCityDetails(cityDataModel.getId())
                .map(cityDetails -> mapper.getCityDataModelWithCoords(cityDataModel, cityDetails))
                .doOnSuccess(dbHelper::saveCity));
    }
}
