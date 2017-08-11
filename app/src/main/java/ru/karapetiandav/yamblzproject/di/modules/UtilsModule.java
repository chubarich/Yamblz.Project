package ru.karapetiandav.yamblzproject.di.modules;

import android.content.res.Resources;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.karapetiandav.yamblzproject.utils.Utils;

@Module
public class UtilsModule {
    @Provides
    @Singleton
    @NonNull
    Utils provideUtils(Resources resources) {
        return new Utils(resources);
    }
}
