package ru.karapetiandav.yamblzproject.di.module;

import android.content.Context;
import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import ru.karapetiandav.yamblzproject.business.cities.CitiesInteractor;
import ru.karapetiandav.yamblzproject.business.cities.CitiesInteracorImpl;
import ru.karapetiandav.yamblzproject.di.scope.CitiesScope;
import ru.karapetiandav.yamblzproject.ui.cities.CitiesAdapter;
import ru.karapetiandav.yamblzproject.ui.cities.CitiesPresenter;
import ru.karapetiandav.yamblzproject.ui.cities.CitiesPresenterImpl;
import ru.karapetiandav.yamblzproject.ui.cities.CitiesView;
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
    CitiesInteractor provideCitiesInteractor() {
        return new CitiesInteracorImpl();
    }
}
