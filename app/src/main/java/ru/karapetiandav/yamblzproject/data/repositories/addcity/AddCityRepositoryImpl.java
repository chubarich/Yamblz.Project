package ru.karapetiandav.yamblzproject.data.repositories.addcity;


import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import ru.karapetiandav.yamblzproject.data.db.DBHelper;
import ru.karapetiandav.yamblzproject.data.model.CityDataModel;
import ru.karapetiandav.yamblzproject.data.model.Language;
import ru.karapetiandav.yamblzproject.data.prefs.PreferenceHelper;

public class AddCityRepositoryImpl implements AddCityRepository {

    private DBHelper dbHelper;
    private PreferenceHelper preferenceHelper;

    public AddCityRepositoryImpl(DBHelper dbHelper, PreferenceHelper preferenceHelper) {
        this.dbHelper = dbHelper;
        this.preferenceHelper = preferenceHelper;
    }

    @Override
    public Single<List<CityDataModel>> getCitiesMatches(String text, Language language) {
        return dbHelper.getCities(text, language);
    }

    @Override
    public Completable saveCity(CityDataModel city) {
        return preferenceHelper.saveCity(city)
                .doOnComplete(() -> preferenceHelper.clearWeatherCache().subscribe());
    }
}
