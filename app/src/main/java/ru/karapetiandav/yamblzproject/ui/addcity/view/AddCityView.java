package ru.karapetiandav.yamblzproject.ui.addcity.view;


import java.util.List;

import ru.karapetiandav.yamblzproject.ui.addcity.model.CityViewModel;

public interface AddCityView {
    void showCities(List<CityViewModel> cities);
    void showNoMatches();
    void showError();
    void close();
    void showProgress();
    void hideProgress();
}
