package ru.karapetiandav.yamblzproject.utils.mappers;


import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

import ru.karapetiandav.yamblzproject.data.model.ForecastDataModel;
import ru.karapetiandav.yamblzproject.data.model.WeatherDataModel;
import ru.karapetiandav.yamblzproject.data.network.model.weather.Day;
import ru.karapetiandav.yamblzproject.data.network.model.weather.ForecastResponse;
import ru.karapetiandav.yamblzproject.data.network.model.weather.Main;
import ru.karapetiandav.yamblzproject.data.network.model.weather.Temp;
import ru.karapetiandav.yamblzproject.data.network.model.weather.Weather;
import ru.karapetiandav.yamblzproject.data.network.model.weather.WeatherResponse;
import ru.karapetiandav.yamblzproject.utils.TimeUtils;
import ru.karapetiandav.yamblzproject.utils.Utils;

import static org.assertj.core.api.Assertions.assertThat;

public class WeatherMapperTest {

    @Mock
    Utils utils;
    @Mock
    TimeUtils timeUtils;

    @InjectMocks
    WeatherMapper weatherMapper;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Test
    public void getWeatherFromResponse_test() {
        Main main = new Main();
        main.setTemp(1d);
        Weather weather = new Weather();
        weather.setId(1);
        WeatherResponse weatherResponse = new WeatherResponse();
        weatherResponse.setMain(main);
        List<Weather> list = new ArrayList<>();
        list.add(weather);
        weatherResponse.setWeather(list);
        String cityId = "id";
        WeatherDataModel expected = new WeatherDataModel(1, 1d, cityId);
        WeatherDataModel real = weatherMapper.getWeatherFromResponse(weatherResponse, cityId);
        assertThat(real).isEqualTo(expected);
    }

    @Test
    public void getForecastListFromResponse_test() {
        String cityId = "cityId";
        ForecastResponse forecastResponse = new ForecastResponse();
        Weather weather = new Weather();
        weather.setId(456);
        List<Day> list = new ArrayList<>();
        forecastResponse.setDays(list);
        Day day = new Day();
        list.add(day);
        List<Weather> weatherList = new ArrayList<>();
        weatherList.add(weather);
        day.setWeather(weatherList);
        day.setDt(0);
        day.setDeg(45d);
        day.setHumidity(1d);
        day.setPressure(3d);
        day.setSpeed(67d);
        Temp temp = new Temp();
        temp.setDay(1d);
        temp.setMorn(1d);
        temp.setEve(1d);
        temp.setNight(1d);
        temp.setMax(1d);
        temp.setMin(1d);
        day.setTemp(temp);
        List<ForecastDataModel> real =
                weatherMapper.getForecastListFromResponse(forecastResponse, cityId);
        ForecastDataModel forecastDataModel = new ForecastDataModel.Builder().setCityId(cityId)
                .setDate(0).setWeatherId(456).setDeg(45d).setHumidity(1d).setPressure(3d)
                .setSpeed(67d).setDay(1d).setMorn(1d).setEve(1d).setNight(1d).setMax(1d).setMin(1d)
                .build();
        List<ForecastDataModel> expected = new ArrayList<>();
        expected.add(forecastDataModel);
        assertThat(real).isEqualTo(expected);
    }
}
