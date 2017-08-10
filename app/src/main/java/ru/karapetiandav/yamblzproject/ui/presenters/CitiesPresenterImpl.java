package ru.karapetiandav.yamblzproject.ui.presenters;


import ru.karapetiandav.yamblzproject.business.cities.CitiesInteractor;
import ru.karapetiandav.yamblzproject.ui.entities.CityWeatherViewModel;
import ru.karapetiandav.yamblzproject.ui.views.CitiesView;
import ru.karapetiandav.yamblzproject.utils.rx.RxSchedulers;

public class CitiesPresenterImpl extends BasePresenter<CitiesView>
        implements CitiesPresenter<CitiesView> {

    private CitiesInteractor citiesInteractor;
    private RxSchedulers rxSchedulers;

    private CitiesView view;

    public CitiesPresenterImpl(CitiesInteractor citiesInteractor,
                               RxSchedulers rxSchedulers) {
        this.citiesInteractor = citiesInteractor;
        this.rxSchedulers = rxSchedulers;
    }

    @Override
    public void onAttach(CitiesView view) {
        this.view = view;
        loadCityWeathers();
    }

    private void loadCityWeathers() {
        disposeOnDetach(citiesInteractor.subscribeOnCityWeathers()
                .subscribeOn(rxSchedulers.getIOScheduler())
                .observeOn(rxSchedulers.getMainThreadScheduler())
                .subscribe(this::handleSuccess, this::handleError));
    }

    private void handleSuccess(CityWeatherViewModel city) {
        view.showCity(city);
        view.hideLoading();
        view.showNoCities(false);
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

}
