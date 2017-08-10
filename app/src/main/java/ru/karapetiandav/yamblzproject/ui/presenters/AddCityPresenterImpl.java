package ru.karapetiandav.yamblzproject.ui.presenters;


import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.karapetiandav.yamblzproject.business.addcity.AddCityInteractor;
import ru.karapetiandav.yamblzproject.ui.entities.CityViewModel;
import ru.karapetiandav.yamblzproject.ui.views.AddCityView;
import ru.karapetiandav.yamblzproject.utils.rx.RxSchedulers;

public class AddCityPresenterImpl extends BasePresenter<AddCityView>
        implements AddCityPresenter<AddCityView> {

    private final static int DEBOUNCE_BEFORE_QUERING_DATA = 500;

    private AddCityInteractor addCityInteractor;
    private AddCityPresenterCache cache;
    private RxSchedulers schedulers;

    private AddCityView view;

    public AddCityPresenterImpl(AddCityInteractor addCityInteractor,
                                AddCityPresenterCache cache,
                                RxSchedulers schedulers) {
        this.addCityInteractor = addCityInteractor;
        this.cache = cache;
        this.schedulers = schedulers;
    }

    @Override
    public void onAttach(AddCityView view) {
        this.view = view;
        if (cache.isCacheExist()) {
            List<CityViewModel> cities = cache.getCities();
            if (cities.size() == 0) {
                view.showNoMatches();
            } else {
                view.showCities(cache.getCities());
            }
        }
    }

    @Override
    public void observeInputChanges(Observable<CharSequence> inputChanges) {
        disposeOnDetach(inputChanges
                .map(CharSequence::toString)
                .doOnNext(this::handleText)
                .debounce(DEBOUNCE_BEFORE_QUERING_DATA, TimeUnit.MILLISECONDS)
                .observeOn(schedulers.getIOScheduler())
                .flatMap(addCityInteractor::getCitiesMatches)
                .observeOn(schedulers.getMainThreadScheduler())
                .doAfterNext(ignore -> view.hideProgress())
                .doAfterTerminate(view::hideProgress)
                .subscribeOn(schedulers.getIOScheduler())
                .observeOn(schedulers.getMainThreadScheduler())
                .subscribe(this::handleNext, this::handleError));
    }

    private void handleText(String text) {
        if (text.equals("")) {
            view.showCities(new ArrayList<>());
            view.hideProgress();
        } else {
            view.showProgress();
        }
        cache.setLastText(text);
    }

    private void handleNext(@NonNull List<CityViewModel> cities) {
        if (!cities.isEmpty() || cache.getLastText().equals("")) {
            view.showCities(cities);
        } else {
            view.showNoMatches();
        }
        cache.updateData(cities);
    }

    private void handleError(Throwable throwable) {
        view.showError();
    }

    @Override
    public void onCityClick(@NonNull CityViewModel city) {
        addCityInteractor.chooseCity(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(view::close, Throwable::printStackTrace);
    }
}
