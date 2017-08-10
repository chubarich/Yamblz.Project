package ru.karapetiandav.yamblzproject.di.module;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import ru.karapetiandav.yamblzproject.business.addcity.AddCityInteractor;
import ru.karapetiandav.yamblzproject.business.addcity.AddCityInteractorImpl;
import ru.karapetiandav.yamblzproject.data.repositories.cities.CitiesRepository;
import ru.karapetiandav.yamblzproject.di.scope.AddCityScope;
import ru.karapetiandav.yamblzproject.ui.adapters.AddCityAdapter;
import ru.karapetiandav.yamblzproject.ui.presenters.AddCityPresenter;
import ru.karapetiandav.yamblzproject.ui.presenters.AddCityPresenterCache;
import ru.karapetiandav.yamblzproject.ui.presenters.AddCityPresenterImpl;
import ru.karapetiandav.yamblzproject.ui.views.AddCityView;
import ru.karapetiandav.yamblzproject.utils.mappers.CityMapper;
import ru.karapetiandav.yamblzproject.utils.rx.RxSchedulers;

@Module
public class AddCityModule {

    @Provides
    @AddCityScope
    @NonNull
    AddCityPresenter<AddCityView> provideAddCityPresenter(AddCityInteractor interactor,
                                                          AddCityPresenterCache cache,
                                                          RxSchedulers schedulers) {
        return new AddCityPresenterImpl(interactor, cache, schedulers);
    }

    @Provides
    @AddCityScope
    @NonNull
    AddCityAdapter provideAddCityAdapter() {
        return new AddCityAdapter();
    }

    @Provides
    @AddCityScope
    @NonNull
    AddCityInteractor addCityInteractor(CitiesRepository repository, CityMapper mapper) {
        return new AddCityInteractorImpl(repository, mapper);
    }

    @Provides
    @AddCityScope
    @NonNull
    AddCityPresenterCache provideAddCityPresenterCache() {
        return new AddCityPresenterCache();
    }

}
