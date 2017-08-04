package ru.karapetiandav.yamblzproject.di.component;

import javax.inject.Singleton;

import dagger.Component;
import ru.karapetiandav.yamblzproject.data.job.SyncWeatherJobCreator;
import ru.karapetiandav.yamblzproject.di.module.AppModule;
import ru.karapetiandav.yamblzproject.di.module.AddCityModule;
import ru.karapetiandav.yamblzproject.di.module.DBModule;
import ru.karapetiandav.yamblzproject.di.module.NetworkModule;
import ru.karapetiandav.yamblzproject.di.module.WeatherModule;

@Singleton
@Component(modules = {AppModule.class, DBModule.class, NetworkModule.class})
public interface AppComponent {

    AddCityComponent plusCitiesComponent(AddCityModule module);
    WeatherComponent plusWeatherComponent(WeatherModule module);
    void inject(SyncWeatherJobCreator creator);

}
