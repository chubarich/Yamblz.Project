package ru.karapetiandav.yamblzproject.di.module;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.karapetiandav.yamblzproject.business.weather.mapper.WeatherMapper;
import ru.karapetiandav.yamblzproject.data.db.DBHelper;
import ru.karapetiandav.yamblzproject.data.network.NetworkHelper;
import ru.karapetiandav.yamblzproject.data.repositories.cities.CitiesRepository;
import ru.karapetiandav.yamblzproject.data.repositories.cities.CitiesRepositoryImpl;
import ru.karapetiandav.yamblzproject.data.repositories.weather.WeatherRepository;
import ru.karapetiandav.yamblzproject.data.repositories.weather.WeatherRepositoryImpl;
import ru.karapetiandav.yamblzproject.utils.mappers.CityMapper;

@Module
public class RepositoriesModule {

    @Provides
    @NonNull
    @Singleton
    CitiesRepository provideCitiesRepository(DBHelper dbHelper, NetworkHelper networkHelper,
                                             CityMapper mapper) {
        return new CitiesRepositoryImpl(dbHelper, networkHelper, mapper);
    }

    @Provides
    @Singleton
    @NonNull
    WeatherRepository provideWeatherRepository(NetworkHelper networkHelper, WeatherMapper mapper) {
        return new WeatherRepositoryImpl(networkHelper, mapper);
    }
}
