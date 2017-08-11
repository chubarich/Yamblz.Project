package ru.karapetiandav.yamblzproject.di.modules;

import android.content.res.Resources;
import android.support.annotation.NonNull;

import org.joda.time.LocalDate;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.karapetiandav.yamblzproject.utils.TimeUtils;
import ru.karapetiandav.yamblzproject.utils.Utils;

@Module
public class UtilsModule {

    @Provides
    @Singleton
    @NonNull
    Utils provideUtils(Resources resources) {
        return new Utils(resources);
    }

    @Provides
    @Singleton
    @NonNull
    TimeUtils provideTimeUtils(Resources resources) {
        return new TimeUtils(resources, new LocalDate());
    }
}
