package ru.karapetiandav.yamblzproject.di.component;

import dagger.Subcomponent;
import ru.karapetiandav.yamblzproject.di.module.CitiesModule;
import ru.karapetiandav.yamblzproject.di.scope.CitiesScope;
import ru.karapetiandav.yamblzproject.ui.cities.CitiesFragment;

@CitiesScope
@Subcomponent(modules = CitiesModule.class)
public interface CitiesComponent {
    void inject(CitiesFragment citiesFragment);
}
