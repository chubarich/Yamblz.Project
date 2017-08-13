package ru.karapetiandav.yamblzproject;


import java.util.ArrayList;
import java.util.List;

import ru.karapetiandav.yamblzproject.data.model.CityDataModel;
import ru.karapetiandav.yamblzproject.data.model.ForecastDataModel;
import ru.karapetiandav.yamblzproject.data.model.WeatherDataModel;
import ru.karapetiandav.yamblzproject.ui.entities.CityViewModel;
import ru.karapetiandav.yamblzproject.ui.entities.CityWeatherViewModel;
import ru.karapetiandav.yamblzproject.ui.entities.WeatherViewModel;

public class TestDataDependencies {

    private String notEmptyString;
    private String emptyString;
    private String nullString;
    private CityViewModel cityViewModel;
    private CityDataModel cityDataModel;
    private List<CityViewModel> cityViewModelList;
    private List<CityDataModel> cityDataModelList;
    private ForecastDataModel forecastDataModel;
    private List<ForecastDataModel> forecastDataModelList;
    private WeatherViewModel weatherViewModel;
    private List<WeatherViewModel> weatherViewModelList;
    private Throwable throwable;
    private CityWeatherViewModel cityWeatherViewModel;
    private WeatherDataModel weatherDataModel;

    public TestDataDependencies() {
        notEmptyString = "123";
        emptyString = "    ";
        nullString = null;
        cityViewModelList = new ArrayList<>();
        cityViewModel = new CityViewModel("12345", "Moscow", "Russia");
        cityViewModelList.add(cityViewModel);
        cityDataModel = new CityDataModel("12345", "Moscow", "Russia", 1d, 2d);
        cityDataModelList = new ArrayList<>();
        cityDataModelList.add(cityDataModel);
        forecastDataModel =
                new ForecastDataModel(1, 1, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d, "id");
        forecastDataModelList = new ArrayList<>();
        forecastDataModelList.add(forecastDataModel);
        weatherViewModel = new WeatherViewModel.Builder()
                .setColorId(1)
                .setDayOfWeek("Mon")
                .setDayTemp("temp")
                .setMaxTemp("45")
                .setMinTemp("34")
                .setMorningTemp("22")
                .setEveningTemp("56")
                .setNightTemp("56")
                .setIconId(1)
                .setHumidity("45")
                .setWind("1")
                .setPressure("1").build();
        weatherViewModelList = new ArrayList<>();
        weatherViewModelList.add(weatherViewModel);
        throwable = new Throwable();
        cityWeatherViewModel = new CityWeatherViewModel(
                cityViewModel,
                "45",
                1,
                1
        );
        weatherDataModel = new WeatherDataModel(1, 1d, "id");
    }

    public String getNotEmptyString() {
        return notEmptyString;
    }

    public String getEmptyString() {
        return emptyString;
    }

    public String getNullString() {
        return nullString;
    }

    public CityViewModel getCityViewModel() {
        return cityViewModel;
    }

    public CityDataModel getCityDataModel() {
        return cityDataModel;
    }

    public List<CityViewModel> getCityViewModelList() {
        return cityViewModelList;
    }

    public List<CityDataModel> getCityDataModelList() {
        return cityDataModelList;
    }

    public ForecastDataModel getForecastDataModel() {
        return forecastDataModel;
    }

    public List<ForecastDataModel> getForecastDataModelList() {
        return forecastDataModelList;
    }

    public WeatherViewModel getWeatherViewModel() {
        return weatherViewModel;
    }

    public List<WeatherViewModel> getWeatherViewModelList() {
        return weatherViewModelList;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public CityWeatherViewModel getCityWeatherViewModel() {
        return cityWeatherViewModel;
    }

    public WeatherDataModel getWeatherDataModel() {
        return weatherDataModel;
    }
}
