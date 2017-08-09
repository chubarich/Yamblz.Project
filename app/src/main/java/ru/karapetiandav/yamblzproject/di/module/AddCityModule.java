package ru.karapetiandav.yamblzproject.di.module;

import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import ru.karapetiandav.yamblzproject.business.addcity.AddCityInteractor;
import ru.karapetiandav.yamblzproject.business.addcity.AddCityInteractorImpl;
import ru.karapetiandav.yamblzproject.data.repositories.cities.CitiesRepository;
import ru.karapetiandav.yamblzproject.di.scope.AddCityScope;
import ru.karapetiandav.yamblzproject.ui.addcity.adapter.AddCityAdapter;
import ru.karapetiandav.yamblzproject.ui.addcity.presenter.AddCityPresenter;
import ru.karapetiandav.yamblzproject.ui.addcity.presenter.AddCityPresenterCache;
import ru.karapetiandav.yamblzproject.ui.addcity.presenter.AddCityPresenterImpl;
import ru.karapetiandav.yamblzproject.ui.addcity.view.AddCityView;
import ru.karapetiandav.yamblzproject.utils.mappers.CityMapper;
import ru.karapetiandav.yamblzproject.utils.rx.RxSchedulers;

@Module
public class AddCityModule {

    @Provides
    @AddCityScope
    @NonNull
    AddCityPresenter<AddCityView> provideAddCityPresenter(AddCityInteractor interactor,
                                                          CompositeDisposable compositeDisposable,
                                                          AddCityPresenterCache cache,
                                                          RxSchedulers schedulers) {
        return new AddCityPresenterImpl(interactor, compositeDisposable, cache, schedulers);
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
