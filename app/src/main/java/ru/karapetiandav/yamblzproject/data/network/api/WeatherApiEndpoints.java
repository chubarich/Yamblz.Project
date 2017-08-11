package ru.karapetiandav.yamblzproject.data.network.api;


public class WeatherApiEndpoints {
    public static final String BASE_URL = "http://api.openweathermap.org/";

    public static final String LATITUDE = "lat";
    public static final String LONGITUDE = "lon";
    public static final String KEY = "appid";

    public static final String WEATHER = "/data/2.5/weather";

    public static final String FORECAST = "/data/2.5/forecast/daily";
    public static final String COUNT = "cnt";
    public static final String COUNT_DEFAULT = "7";
}
