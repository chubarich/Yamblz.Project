package ru.karapetiandav.yamblzproject.di.components;

import javax.inject.Singleton;

import dagger.Component;
import ru.karapetiandav.yamblzproject.data.job.SyncWeatherJobCreator;
import ru.karapetiandav.yamblzproject.di.modules.AppModule;
import ru.karapetiandav.yamblzproject.di.modules.AddCityModule;
import ru.karapetiandav.yamblzproject.di.modules.CitiesModule;
import ru.karapetiandav.yamblzproject.di.modules.DBModule;
import ru.karapetiandav.yamblzproject.di.modules.MappersModule;
import ru.karapetiandav.yamblzproject.di.modules.NetworkModule;
import ru.karapetiandav.yamblzproject.di.modules.RepositoriesModule;
import ru.karapetiandav.yamblzproject.di.modules.UtilsModule;
import ru.karapetiandav.yamblzproject.di.modules.WeatherModule;

@Singleton
@Component(modules = {AppModule.class, DBModule.class, NetworkModule.class, MappersModule.class,
        RepositoriesModule.class, UtilsModule.class})
public interface AppComponent {

    AddCityComponent plusAddCityComponent(AddCityModule module);
    CitiesComponent plusCitiesComponent(CitiesModule module);
    WeatherComponent plusWeatherComponent(WeatherModule module);
    void inject(SyncWeatherJobCreator creator);

}
