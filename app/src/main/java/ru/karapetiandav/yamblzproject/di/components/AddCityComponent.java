package ru.karapetiandav.yamblzproject.di.components;

import dagger.Subcomponent;
import ru.karapetiandav.yamblzproject.di.modules.AddCityModule;
import ru.karapetiandav.yamblzproject.di.scopes.AddCityScope;
import ru.karapetiandav.yamblzproject.ui.activities.AddCityActivity;

@AddCityScope
@Subcomponent(modules = {AddCityModule.class})
public interface AddCityComponent {
    void inject(AddCityActivity addCityActivity);
}
