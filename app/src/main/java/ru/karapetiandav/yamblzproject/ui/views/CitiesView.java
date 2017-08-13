package ru.karapetiandav.yamblzproject.ui.views;


import ru.karapetiandav.yamblzproject.ui.entities.CityViewModel;
import ru.karapetiandav.yamblzproject.ui.entities.CityWeatherViewModel;

public interface CitiesView {

    void showNoCities(boolean show);
//    void showCities(Day<CityWeatherViewModel> cities);
    void showLoading();
    void hideLoading();
    void showError(boolean show);
    void showAddNewCity();
    void showWeatherActivity(CityViewModel city);
    void showCity(CityWeatherViewModel city);
}
