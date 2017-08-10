package ru.karapetiandav.yamblzproject.di.module;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import ru.karapetiandav.yamblzproject.business.weather.WeatherInteractor;
import ru.karapetiandav.yamblzproject.business.weather.WeatherInteractorImpl;
import ru.karapetiandav.yamblzproject.business.weather.mapper.WeatherMapper;
import ru.karapetiandav.yamblzproject.data.repositories.weather.WeatherRepository;
import ru.karapetiandav.yamblzproject.di.scope.WeatherScope;
import ru.karapetiandav.yamblzproject.ui.presenters.WeatherPresenter;
import ru.karapetiandav.yamblzproject.ui.presenters.WeatherPresenterImpl;
import ru.karapetiandav.yamblzproject.ui.views.WeatherView;
import ru.karapetiandav.yamblzproject.utils.rx.RxSchedulers;

@Module
public class WeatherModule {

    @Provides
    @WeatherScope
    @NonNull
    WeatherInteractor provideWeatherInteractor(WeatherRepository weatherRepository,
                                               WeatherMapper mapper) {
        return new WeatherInteractorImpl(weatherRepository, mapper);
    }

    @Provides
    @NonNull
    @WeatherScope
    WeatherPresenter<WeatherView> provideWeatherPresenter(WeatherInteractor weatherInteractor,
                                                          CompositeDisposable compositeDisposable,
                                                          RxSchedulers rxSchedulers) {
        return new WeatherPresenterImpl(weatherInteractor, rxSchedulers);
    }
}
