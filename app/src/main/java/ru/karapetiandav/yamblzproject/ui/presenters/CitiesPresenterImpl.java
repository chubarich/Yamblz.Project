package ru.karapetiandav.yamblzproject.ui.presenters;


import ru.karapetiandav.yamblzproject.business.cities.CitiesInteractor;
import ru.karapetiandav.yamblzproject.ui.entities.CityViewModel;
import ru.karapetiandav.yamblzproject.ui.entities.CityWeatherViewModel;
import ru.karapetiandav.yamblzproject.ui.views.CitiesView;
import ru.karapetiandav.yamblzproject.utils.rx.RxSchedulers;

public class CitiesPresenterImpl extends BasePresenter<CitiesView>
        implements CitiesPresenter<CitiesView> {

    private CitiesInteractor citiesInteractor;
    private RxSchedulers rxSchedulers;

    public CitiesPresenterImpl(CitiesInteractor citiesInteractor,
                               RxSchedulers rxSchedulers) {
        this.citiesInteractor = citiesInteractor;
        this.rxSchedulers = rxSchedulers;
    }

    @Override
    public void onAttach(CitiesView view) {
        super.onAttach(view);
        loadCityWeathers();
    }

    private void loadCityWeathers() {
        disposeOnDetach(citiesInteractor.subscribeOnCityWeathers()
                .subscribeOn(rxSchedulers.getIOScheduler())
                .observeOn(rxSchedulers.getMainThreadScheduler())
                .subscribe(this::handleSuccess, this::handleError));
    }

    private void handleSuccess(CityWeatherViewModel city) {
        getView().showCity(city);
        getView().hideLoading();
        getView().showNoCities(false);
    }

    private void handleError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onCityClick(CityViewModel item) {
        getView().showWeather(item);
    }

    @Override
    public void onAddCityClick() {
        getView().showAddNewCity();
    }

    @Override
    public void onSwipeToRefresh() {
        loadCityWeathers();
    }

}
