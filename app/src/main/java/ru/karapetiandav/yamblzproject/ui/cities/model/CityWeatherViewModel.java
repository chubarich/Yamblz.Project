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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CityWeatherViewModel that = (CityWeatherViewModel) o;

        if (getIconId() != that.getIconId()) return false;
        if (getColor() != that.getColor()) return false;
        if (getCityViewModel() != null ? !getCityViewModel().equals(that.getCityViewModel()) : that.getCityViewModel() != null)
            return false;
        return getTemp() != null ? getTemp().equals(that.getTemp()) : that.getTemp() == null;

    }
}
