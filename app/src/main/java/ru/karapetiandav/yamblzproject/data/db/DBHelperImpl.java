package ru.karapetiandav.yamblzproject.data.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.subjects.ReplaySubject;
import ru.karapetiandav.yamblzproject.data.model.CityDataModel;
import ru.karapetiandav.yamblzproject.di.qualifiers.DbName;
import ru.karapetiandav.yamblzproject.di.qualifiers.DbVersion;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class DBHelperImpl extends SQLiteOpenHelper implements DBHelper {

    private ReplaySubject<CityDataModel> citiesSubject = ReplaySubject.create();

    public DBHelperImpl(Context context,@DbName String name,
                        @DbVersion int version) {
        super(context, name, null, version);
    }

    static {
        cupboard().register(CityDataModel.class);
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
    public Observable<CityDataModel> subscribe() {
        List<CityDataModel> list = cupboard().withDatabase(getReadableDatabase())
            .query(CityDataModel.class).list();
        for (CityDataModel city : list) {
            citiesSubject.onNext(city);
        }
        return citiesSubject;
    }

    @Override
    public Completable saveCity(CityDataModel cityDataModel) {
        int delete = cupboard().withDatabase(getWritableDatabase())
                .delete(CityDataModel.class, "id = ?", cityDataModel.getId());
        long result = cupboard().withDatabase(getWritableDatabase())
                .put(cityDataModel);
        citiesSubject.onNext(cityDataModel);
        return Completable.complete();
    }
}
