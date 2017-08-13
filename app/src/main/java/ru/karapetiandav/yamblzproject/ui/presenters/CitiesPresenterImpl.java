package ru.karapetiandav.yamblzproject.ui.presenters;


import ru.karapetiandav.yamblzproject.business.RemoveCityUseCase;
import ru.karapetiandav.yamblzproject.business.cities.CitiesInteractor;
import ru.karapetiandav.yamblzproject.ui.entities.CityViewModel;
import ru.karapetiandav.yamblzproject.ui.entities.CityWeatherViewModel;
import ru.karapetiandav.yamblzproject.ui.views.CitiesView;
import ru.karapetiandav.yamblzproject.utils.rx.RxSchedulers;

public class CitiesPresenterImpl extends BasePresenter<CitiesView>
        implements CitiesPresenter<CitiesView> {

    private CitiesInteractor citiesInteractor;
    private RxSchedulers rxSchedulers;
    private RemoveCityUseCase removeCityUseCase;

    public CitiesPresenterImpl(CitiesInteractor citiesInteractor, RxSchedulers rxSchedulers,
                               RemoveCityUseCase removeCityUseCase) {
        this.citiesInteractor = citiesInteractor;
        this.rxSchedulers = rxSchedulers;
        this.removeCityUseCase = removeCityUseCase;
    }

    @Override
    public void onAttach(CitiesView view) {
        super.onAttach(view);
        loadCityWeathers();
    }

    @Override
    public void onCityClick(CityViewModel item) {
        getView().showWeatherActivity(item);
    }

    @Override
    public void onAddCityClick() {
        getView().showAddNewCity();
    }

    @Override
    public void onSwipeToRefresh() {
        loadCityWeathers();
    }

    @Override
    public void onCityRemoved(CityViewModel city) {
        removeCityUseCase.execute(city.getCityId()).subscribe();
    }

    private void loadCityWeathers() {
        disposeOnDetach(citiesInteractor.subscribeOnCityWeathers()
                .observeOn(rxSchedulers.getMainThreadScheduler())
                .doOnSubscribe(ignore -> getView().showLoading())
                .subscribeOn(rxSchedulers.getIOScheduler())
                .observeOn(rxSchedulers.getMainThreadScheduler())
                .subscribe(this::handleSuccess, this::handleError));
    }

    private void handleSuccess(CityWeatherViewModel city) {
        getView().showCity(city);
        getView().hideLoading();
    }

    private void handleError(Throwable throwable) {
        throwable.printStackTrace();
    }


}
