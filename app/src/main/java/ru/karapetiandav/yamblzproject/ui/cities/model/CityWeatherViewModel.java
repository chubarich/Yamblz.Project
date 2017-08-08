package ru.karapetiandav.yamblzproject.ui.cities.model;


import ru.karapetiandav.yamblzproject.ui.addcity.model.CityViewModel;

public final class CityWeatherViewModel {

    private final CityViewModel cityViewModel;
    private final String temp;
    private final int iconId;
    private final int color;

    public CityWeatherViewModel(CityViewModel cityViewModel, String temp, int iconId, int color) {
        this.cityViewModel = cityViewModel;
        this.temp = temp;
        this.iconId = iconId;
        this.color = color;
    }

    public CityViewModel getCityViewModel() {
        return cityViewModel;
    }

    public String getTemp() {
        return temp;
    }

    public int getIconId() {
        return iconId;
    }

    public int getColor() {
        return color;
    }
}
