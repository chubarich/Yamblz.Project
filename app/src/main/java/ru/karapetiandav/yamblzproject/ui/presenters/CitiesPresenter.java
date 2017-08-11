package ru.karapetiandav.yamblzproject.ui.presenters;


import ru.karapetiandav.yamblzproject.ui.entities.CityViewModel;

public interface CitiesPresenter<V> extends Presenter<V> {

    void onCityClick(CityViewModel city);
    void onAddCityClick();
    void onSwipeToRefresh();
}
