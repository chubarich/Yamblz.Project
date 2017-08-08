package ru.karapetiandav.yamblzproject.ui.cities;


import ru.karapetiandav.yamblzproject.ui.base.Presenter;
import ru.karapetiandav.yamblzproject.ui.cities.model.CityWeatherViewModel;

public interface CitiesPresenter<V> extends Presenter<V> {

    void onCityWeatherClick(CityWeatherViewModel item);
    void onAddCityClick();
    void onSwipeToRefresh();
}
