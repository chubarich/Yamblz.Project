package ru.karapetiandav.yamblzproject.business.addcity.interactor;


import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import ru.karapetiandav.yamblzproject.ui.addcity.model.CityViewModel;

public interface AddCityInteractor {

    Observable<List<CityViewModel>> getCitiesMatches(String text);
    Completable saveCity(CityViewModel city);

}
