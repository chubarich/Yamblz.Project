package ru.karapetiandav.yamblzproject.business.cities;


import java.util.List;

import io.reactivex.Single;
import ru.karapetiandav.yamblzproject.ui.cities.model.CityWeatherViewModel;

public interface CitiesInteractor {

    Single<List<CityWeatherViewModel>> loadCityWeatherList();

}
