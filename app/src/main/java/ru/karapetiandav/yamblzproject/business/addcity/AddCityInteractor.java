package ru.karapetiandav.yamblzproject.business.addcity;


import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import ru.karapetiandav.yamblzproject.ui.entities.CityViewModel;

public interface AddCityInteractor {

    Observable<List<CityViewModel>> getCitiesMatches(String text);
    Completable chooseCity(CityViewModel city);

}
