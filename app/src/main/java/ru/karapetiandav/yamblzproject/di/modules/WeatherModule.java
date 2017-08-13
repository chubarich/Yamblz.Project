package ru.karapetiandav.yamblzproject.di.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import ru.karapetiandav.yamblzproject.business.GetForecastUseCase;
import ru.karapetiandav.yamblzproject.data.repositories.CitiesRepository;
import ru.karapetiandav.yamblzproject.data.repositories.WeatherRepository;
import ru.karapetiandav.yamblzproject.di.scopes.WeatherScope;
import ru.karapetiandav.yamblzproject.ui.adapters.WeatherAdapter;
import ru.karapetiandav.yamblzproject.ui.presenters.WeatherPresenter;
import ru.karapetiandav.yamblzproject.ui.presenters.WeatherPresenterImpl;
import ru.karapetiandav.yamblzproject.ui.views.WeatherView;
import ru.karapetiandav.yamblzproject.utils.mappers.WeatherMapper;
import ru.karapetiandav.yamblzproject.utils.rx.RxSchedulers;

@Module
public class WeatherModule {

    @Provides
    @WeatherScope
    @NonNull
    GetForecastUseCase provideWeatherInteractor(WeatherRepository weatherRepository,
                                                CitiesRepository citiesRepository,
                                                WeatherMapper mapper) {
        return new GetForecastUseCase(weatherRepository, citiesRepository, mapper);
    }

    @Provides
    @NonNull
    @WeatherScope
    WeatherPresenter<WeatherView> provideWeatherPresenter(GetForecastUseCase getForecastUseCase,
                                                          RxSchedulers rxSchedulers) {
        return new WeatherPresenterImpl(getForecastUseCase, rxSchedulers);
    }

    @Provides
    @NonNull
    @WeatherScope
    WeatherAdapter provideWeatherAdapter(Context context) {
        return new WeatherAdapter(context);
    }
}
