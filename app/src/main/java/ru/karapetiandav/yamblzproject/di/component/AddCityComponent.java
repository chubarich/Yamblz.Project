package ru.karapetiandav.yamblzproject.di.component;

import dagger.Subcomponent;
import ru.karapetiandav.yamblzproject.di.module.AddCityModule;
import ru.karapetiandav.yamblzproject.di.scope.AddCityScope;
import ru.karapetiandav.yamblzproject.ui.addcity.view.AddCityFragment;

@AddCityScope
@Subcomponent(modules = {AddCityModule.class})
public interface AddCityComponent {
    void inject(AddCityFragment fragment);
}
