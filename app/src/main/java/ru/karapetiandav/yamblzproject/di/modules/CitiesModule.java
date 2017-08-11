package ru.karapetiandav.yamblzproject.di.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import ru.karapetiandav.yamblzproject.business.RemoveCityUseCase;
import ru.karapetiandav.yamblzproject.business.cities.CitiesInteractor;
import ru.karapetiandav.yamblzproject.business.cities.CitiesInteractorImpl;
import ru.karapetiandav.yamblzproject.data.repositories.CitiesRepository;
import ru.karapetiandav.yamblzproject.data.repositories.WeatherRepository;
import ru.karapetiandav.yamblzproject.di.scopes.CitiesScope;
import ru.karapetiandav.yamblzproject.ui.adapters.CitiesAdapter;
import ru.karapetiandav.yamblzproject.ui.presenters.CitiesPresenter;
import ru.karapetiandav.yamblzproject.ui.presenters.CitiesPresenterImpl;
import ru.karapetiandav.yamblzproject.ui.views.CitiesView;
import ru.karapetiandav.yamblzproject.utils.mappers.CityWeatherMapper;
import ru.karapetiandav.yamblzproject.utils.rx.RxSchedulers;

@Module
public class CitiesModule {

    @Provides
    @NonNull
    @CitiesScope
    CitiesPresenter<CitiesView> provideCitiesPresenter(CitiesInteractor interactor,
                                                       RxSchedulers rxSchedulers,
                                                       RemoveCityUseCase removeCityUseCase) {
        return new CitiesPresenterImpl(interactor, rxSchedulers, removeCityUseCase);
    }

    @Provides
    @NonNull
    @CitiesScope
    RemoveCityUseCase provideRemoveCityUseCase(CitiesRepository citiesRepository) {
        return new RemoveCityUseCase(citiesRepository);
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
