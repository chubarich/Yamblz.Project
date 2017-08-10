package ru.karapetiandav.yamblzproject.ui.presenters;


import io.reactivex.Observable;
import ru.karapetiandav.yamblzproject.ui.entities.CityViewModel;

public interface AddCityPresenter<V> extends Presenter<V> {
    void onCityClick(CityViewModel city);
    void observeInputChanges(Observable<CharSequence> inputChanges);
}
