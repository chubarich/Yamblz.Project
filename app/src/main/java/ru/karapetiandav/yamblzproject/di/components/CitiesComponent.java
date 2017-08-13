package ru.karapetiandav.yamblzproject.di.components;

import dagger.Subcomponent;
import ru.karapetiandav.yamblzproject.di.modules.CitiesModule;
import ru.karapetiandav.yamblzproject.di.scopes.CitiesScope;
import ru.karapetiandav.yamblzproject.ui.fragments.CitiesFragment;

@CitiesScope
@Subcomponent(modules = CitiesModule.class)
public interface CitiesComponent {
    void inject(CitiesFragment citiesFragment);
}
