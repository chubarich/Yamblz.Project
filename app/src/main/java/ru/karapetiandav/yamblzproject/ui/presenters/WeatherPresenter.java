package ru.karapetiandav.yamblzproject.ui.presenters;


import ru.karapetiandav.yamblzproject.ui.entities.CityViewModel;

public interface WeatherPresenter<V> extends Presenter<V> {
    void onCityRestored(CityViewModel city);
    void onSwipeToRefresh();
}
