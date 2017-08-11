package ru.karapetiandav.yamblzproject.di.modules;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.karapetiandav.yamblzproject.utils.mappers.WeatherMapper;
import ru.karapetiandav.yamblzproject.utils.Utils;
import ru.karapetiandav.yamblzproject.utils.mappers.CityMapper;
import ru.karapetiandav.yamblzproject.utils.mappers.CityWeatherMapper;

@Module
public class MappersModule {

    @Provides
    @Singleton
    @NonNull
    CityMapper provideCityMapper() {
        return new CityMapper();
    }

    @Provides
    @NonNull
    @Singleton
    WeatherMapper provideWeatherMapper(Utils utils) {
        return new WeatherMapper(utils);
    }

    @Provides
    @NonNull
    @Singleton
    CityWeatherMapper provideCityWeatherMapper(CityMapper cityMapper, Utils utils) {
        return new CityWeatherMapper(cityMapper, utils);
    }
}
