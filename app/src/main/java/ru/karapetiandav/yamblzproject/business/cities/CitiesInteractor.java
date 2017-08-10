package ru.karapetiandav.yamblzproject.business.cities;


import io.reactivex.Observable;
import ru.karapetiandav.yamblzproject.ui.entities.CityWeatherViewModel;

public interface CitiesInteractor {

    Observable<CityWeatherViewModel> subscribeOnCityWeathers();

}
