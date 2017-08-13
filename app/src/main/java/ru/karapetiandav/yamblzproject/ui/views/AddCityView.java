package ru.karapetiandav.yamblzproject.ui.views;


import java.util.List;

import ru.karapetiandav.yamblzproject.ui.entities.CityViewModel;

public interface AddCityView {
    void showCities(List<CityViewModel> cities);
    void showNoMatches();
    void showError();
    void close();
    void showProgress();
    void hideProgress();
}
