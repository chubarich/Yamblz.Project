package ru.karapetiandav.yamblzproject.ui.entities;


public final class CityViewModel {

    private final String cityId;
    private final String cityName;
    private final String country;

    public CityViewModel(String cityId, String cityName, String country) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.country = country;
    }

    public String getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CityViewModel viewModel = (CityViewModel) o;

        if (getCityId() != null ? !getCityId().equals(viewModel.getCityId()) : viewModel.getCityId() != null)
            return false;
        if (getCityName() != null ? !getCityName().equals(viewModel.getCityName()) : viewModel.getCityName() != null)
            return false;
        return getCountry() != null ? getCountry().equals(viewModel.getCountry()) : viewModel.getCountry() == null;

    }
}
