package ru.karapetiandav.yamblzproject.di.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;
import ru.karapetiandav.yamblzproject.business.usecases.ChooseCityUseCase;
import ru.karapetiandav.yamblzproject.business.usecases.GetCitiesMatchesUseCase;
import ru.karapetiandav.yamblzproject.data.repositories.CitiesRepository;
import ru.karapetiandav.yamblzproject.di.scopes.AddCityScope;
import ru.karapetiandav.yamblzproject.ui.adapters.AddCityAdapter;
import ru.karapetiandav.yamblzproject.ui.presenters.AddCityPresenter;
import ru.karapetiandav.yamblzproject.ui.presenters.AddCityPresenterCache;
import ru.karapetiandav.yamblzproject.ui.presenters.AddCityPresenterImpl;
import ru.karapetiandav.yamblzproject.ui.views.AddCityView;
import ru.karapetiandav.yamblzproject.utils.mappers.CityMapper;
import ru.karapetiandav.yamblzproject.utils.rx.RxSchedulers;

@Module
public class AddCityModule {

    @AddCityScope
    @Provides
    @NonNull
    AddCityPresenter<AddCityView> provideAddCityPresenter(GetCitiesMatchesUseCase getMatchesUseCase,
                                                          ChooseCityUseCase chooseCityuseCase,
                                                          AddCityPresenterCache cache,
                                                          RxSchedulers schedulers) {
        return new AddCityPresenterImpl(getMatchesUseCase, chooseCityuseCase, cache, schedulers);
    }

    @AddCityScope
    @Provides
    @NonNull
    ChooseCityUseCase provideChooseCityUseCase(CitiesRepository repo, CityMapper mapper) {
        return new ChooseCityUseCase(repo, mapper);
    }

    @AddCityScope
    @Provides
    @NonNull
    GetCitiesMatchesUseCase provideMatchesUseCase(CitiesRepository repo, CityMapper mapper) {
        return new GetCitiesMatchesUseCase(repo, mapper);
    }

    @Provides
    @AddCityScope
    @NonNull
    AddCityAdapter provideAddCityAdapter(Context context) {
        return new AddCityAdapter(context);
    }

    @Provides
    @AddCityScope
    @NonNull
    AddCityPresenterCache provideAddCityPresenterCache() {
        return new AddCityPresenterCache();
    }

}
