package ru.karapetiandav.yamblzproject.business.cities;


import io.reactivex.Observable;
import ru.karapetiandav.yamblzproject.ui.cities.model.CityWeatherViewModel;

public interface CitiesInteractor {

    Observable<CityWeatherViewModel> subscribeOnCityWeathers();

}
