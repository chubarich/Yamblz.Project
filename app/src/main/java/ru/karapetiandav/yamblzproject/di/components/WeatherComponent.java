package ru.karapetiandav.yamblzproject.di.components;

import dagger.Subcomponent;
import ru.karapetiandav.yamblzproject.di.modules.WeatherModule;
import ru.karapetiandav.yamblzproject.di.scopes.WeatherScope;
import ru.karapetiandav.yamblzproject.ui.fragments.WeatherFragment;

@WeatherScope
@Subcomponent(modules = WeatherModule.class)
public interface WeatherComponent {
    void inject(WeatherFragment weatherFragment);
}
