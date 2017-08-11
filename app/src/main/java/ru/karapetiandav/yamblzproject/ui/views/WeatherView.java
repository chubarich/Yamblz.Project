package ru.karapetiandav.yamblzproject.ui.views;


import java.util.List;

import ru.karapetiandav.yamblzproject.ui.entities.WeatherViewModel;

public interface WeatherView {
    void showTitle(String title);
    void showWeather(List<WeatherViewModel> weatherList);
    void showError();
    void showLoading();
    void hideLoading();
}
