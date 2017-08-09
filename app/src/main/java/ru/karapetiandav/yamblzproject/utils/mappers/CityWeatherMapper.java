package ru.karapetiandav.yamblzproject.utils.mappers;


import ru.karapetiandav.yamblzproject.data.model.CityDataModel;
import ru.karapetiandav.yamblzproject.data.model.CurrentWeatherDataModel;
import ru.karapetiandav.yamblzproject.ui.cities.model.CityWeatherViewModel;
import ru.karapetiandav.yamblzproject.utils.Utils;

public class CityWeatherMapper {

    private CityMapper cityMapper;
    private Utils utils;

    public CityWeatherMapper(CityMapper cityMapper, Utils utils) {
        this.cityMapper = cityMapper;
        this.utils = utils;
    }

    public CityWeatherViewModel get(CityDataModel city, CurrentWeatherDataModel currentWeather) {
        return new CityWeatherViewModel(
                cityMapper.getViewModel(city),
                utils.formatTemperature(currentWeather.getTemp()),
                utils.getIconResourceForWeatherId(currentWeather.getWeatherId()),
                utils.getColorResourceForWeatherId(currentWeather.getWeatherId())
        );

    }
}
