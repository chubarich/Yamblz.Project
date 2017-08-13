package ru.karapetiandav.yamblzproject.utils.mappers;


import ru.karapetiandav.yamblzproject.data.model.CityDataModel;
import ru.karapetiandav.yamblzproject.data.model.WeatherDataModel;
import ru.karapetiandav.yamblzproject.ui.entities.CityWeatherViewModel;
import ru.karapetiandav.yamblzproject.utils.Utils;

public class CityWeatherMapper {

    private CityMapper cityMapper;
    private Utils utils;

    public CityWeatherMapper(CityMapper cityMapper, Utils utils) {
        this.cityMapper = cityMapper;
        this.utils = utils;
    }

    public CityWeatherViewModel getCityWeatherViewModel(CityDataModel city,
                                                        WeatherDataModel currentWeather) {
        return new CityWeatherViewModel(
                cityMapper.getViewModel(city),
                utils.formatTemperature(currentWeather.getTemp()),
                utils.getIconResourceForWeatherId(currentWeather.getWeatherId()),
                utils.getColorResourceForWeatherId(currentWeather.getWeatherId())
        );

    }
}
