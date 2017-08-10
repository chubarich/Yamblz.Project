package ru.karapetiandav.yamblzproject.ui.views;


import ru.karapetiandav.yamblzproject.ui.entities.WeatherViewModel;

public interface WeatherView {
    void showWeather(WeatherViewModel weatherViewModel);
    void showError();
}
