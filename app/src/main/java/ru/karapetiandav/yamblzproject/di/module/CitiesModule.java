package ru.karapetiandav.yamblzproject.di.module;

import android.content.Context;
import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import ru.karapetiandav.yamblzproject.business.cities.CitiesInteractor;
import ru.karapetiandav.yamblzproject.business.cities.CitiesInteractorImpl;
import ru.karapetiandav.yamblzproject.data.repositories.cities.CitiesRepository;
import ru.karapetiandav.yamblzproject.data.repositories.weather.WeatherRepository;
import ru.karapetiandav.yamblzproject.di.scope.CitiesScope;
import ru.karapetiandav.yamblzproject.ui.cities.CitiesAdapter;
import ru.karapetiandav.yamblzproject.ui.cities.CitiesPresenter;
import ru.karapetiandav.yamblzproject.ui.cities.CitiesPresenterImpl;
import ru.karapetiandav.yamblzproject.ui.cities.CitiesView;
import ru.karapetiandav.yamblzproject.utils.mappers.CityWeatherMapper;
import ru.karapetiandav.yamblzproject.utils.rx.RxSchedulers;

@Module
public class CitiesModule {

    @Provides
    @NonNull
    @CitiesScope
    CitiesPresenter<CitiesView> provideCitiesPresenter(CitiesInteractor interactor, RxSchedulers rxSchedulers) {
        return new CitiesPresenterImpl(interactor, new CompositeDisposable(), rxSchedulers);
    }

    @Provides
    @NonNull
    @CitiesScope
    CitiesAdapter provideCitiesAdapter(Context context) {
        return new CitiesAdapter(context);
    }

    @Provides
    @NonNull
    @CitiesScope
    CitiesInteractor provideCitiesInteractor(CitiesRepository citiesRepository,
                                             WeatherRepository weatherRepository,
                                             RxSchedulers rxSchedulers,
                                             CityWeatherMapper mapper) {
        return new CitiesInteractorImpl(citiesRepository, weatherRepository, rxSchedulers, mapper);
    }

}
