package ru.karapetiandav.yamblzproject.ui.addcity.presenter;


import android.support.annotation.NonNull;

import io.reactivex.Observable;
import ru.karapetiandav.yamblzproject.ui.base.presenter.Presenter;
import ru.karapetiandav.yamblzproject.ui.addcity.model.CityViewModel;

public interface AddCityPresenter<V> extends Presenter<V> {
    void onCityClick(@NonNull CityViewModel city);
    void observeInputChanges(Observable<CharSequence> inputChanges);
}
