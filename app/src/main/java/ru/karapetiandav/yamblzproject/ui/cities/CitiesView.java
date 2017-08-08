package ru.karapetiandav.yamblzproject.ui.cities;


import java.util.List;

import ru.karapetiandav.yamblzproject.ui.cities.model.CityWeatherViewModel;

public interface CitiesView {

    void showNoCities();
    void showCities(List<CityWeatherViewModel> cities);
    void showLoading();
    void hideLoading();
    void showError();
}
