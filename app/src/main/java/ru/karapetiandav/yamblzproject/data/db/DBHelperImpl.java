package ru.karapetiandav.yamblzproject.data.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import ru.karapetiandav.yamblzproject.data.model.CityDataModel;
import ru.karapetiandav.yamblzproject.di.qualifiers.DbName;
import ru.karapetiandav.yamblzproject.di.qualifiers.DbVersion;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class DBHelperImpl extends SQLiteOpenHelper implements DBHelper {

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
    public Completable saveCity(CityDataModel cityDataModel) {
        int delete = cupboard().withDatabase(getWritableDatabase())
                .delete(CityDataModel.class, "id = ?", cityDataModel.getId());
        long result = cupboard().withDatabase(getWritableDatabase())
                .put(cityDataModel);
        return Completable.complete();
    }

    @Override
    public Single<List<CityDataModel>> getCities() {
        List<CityDataModel> list = cupboard().withDatabase(getReadableDatabase())
                .query(CityDataModel.class).list();
        return Single.fromCallable(() -> list);
    }
}
