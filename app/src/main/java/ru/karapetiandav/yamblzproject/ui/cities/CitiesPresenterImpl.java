package ru.karapetiandav.yamblzproject.ui.cities;


import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import ru.karapetiandav.yamblzproject.business.cities.CitiesInteractor;
import ru.karapetiandav.yamblzproject.ui.cities.model.CityWeatherViewModel;
import ru.karapetiandav.yamblzproject.utils.rx.RxSchedulers;

public class CitiesPresenterImpl implements CitiesPresenter<CitiesView> {

    private CitiesInteractor citiesInteractor;
    private CompositeDisposable compositeDisposable;
    private RxSchedulers rxSchedulers;

    private CitiesView view;

    public CitiesPresenterImpl(CitiesInteractor citiesInteractor,
                               CompositeDisposable compositeDisposable,
                               RxSchedulers rxSchedulers) {
        this.citiesInteractor = citiesInteractor;
        this.compositeDisposable = compositeDisposable;
        this.rxSchedulers = rxSchedulers;
    }

    @Override
    public void onAttach(CitiesView view) {
        this.view = view;
        loadCityWeathers();
    }

    private void loadCityWeathers() {
        compositeDisposable.add(citiesInteractor.loadCityWeatherList()
                .doOnSubscribe(ignore -> view.showLoading())
                .observeOn(rxSchedulers.getMainThreadScheduler())
                .doAfterTerminate(view::hideLoading)
                .subscribeOn(rxSchedulers.getIOScheduler())
                .observeOn(rxSchedulers.getMainThreadScheduler())
                .subscribe(this::handleSuccess, this::handleError));
    }

    private void handleSuccess(List<CityWeatherViewModel> cities) {
        if (cities.size() != 0) {
            view.showNoCities(false);
            view.showCities(cities);
        }

    }

    private void handleError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onCityWeatherClick(CityWeatherViewModel item) {

    }

    @Override
    public void onAddCityClick() {
        view.showAddNewCity();
    }

    @Override
    public void onSwipeToRefresh() {
        loadCityWeathers();
    }

    @Override
    public void onDetach() {
        view = null;
        compositeDisposable.clear();
    }
}
