package ru.karapetiandav.yamblzproject.ui.presenters;


import ru.karapetiandav.yamblzproject.ui.entities.CityWeatherViewModel;

public interface CitiesPresenter<V> extends Presenter<V> {

    void onCityWeatherClick(CityWeatherViewModel item);
    void onAddCityClick();
    void onSwipeToRefresh();
}
