package ru.karapetiandav.yamblzproject.di.modules;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import ru.karapetiandav.yamblzproject.data.prefs.PreferenceHelper;
import ru.karapetiandav.yamblzproject.data.prefs.PreferenceHelperImpl;
import ru.karapetiandav.yamblzproject.utils.rx.RxSchedulers;
import ru.karapetiandav.yamblzproject.utils.rx.RxSchedulersImpl;

@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    @NonNull
    Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    @NonNull
    Resources provideResources() {
        return context.getResources();
    }

    @Provides
    @Singleton
    @NonNull
    RxSchedulers provideRxSchedulers() {
        return new RxSchedulersImpl();
    }

    @Provides
    @NonNull
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @Singleton
    @NonNull
    SharedPreferences provideSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    @NonNull
    PreferenceHelper providePreferenceHelper(SharedPreferences sharedPreferences,
                                             Resources resources) {
        return new PreferenceHelperImpl(sharedPreferences, resources);
    }

}
