package ru.karapetiandav.yamblzproject.ui.presenters;

import ru.karapetiandav.yamblzproject.business.weather.WeatherInteractor;
import ru.karapetiandav.yamblzproject.ui.entities.WeatherViewModel;
import ru.karapetiandav.yamblzproject.ui.views.WeatherView;
import ru.karapetiandav.yamblzproject.utils.rx.RxSchedulers;

public class WeatherPresenterImpl extends BasePresenter<WeatherView>
        implements WeatherPresenter<WeatherView> {

    private WeatherInteractor weatherInteractor;
    private RxSchedulers rxSchedulers;

    public WeatherPresenterImpl(WeatherInteractor weatherInteractor,
                                RxSchedulers rxSchedulers) {
        this.weatherInteractor = weatherInteractor;
        this.rxSchedulers = rxSchedulers;
    }

    @Override
    public void onAttach(WeatherView view) {
        super.onAttach(view);
        loadWeather();
    }

    private void loadWeather() {
        disposeOnDetach((weatherInteractor.getWeather()
                .subscribeOn(rxSchedulers.getIOScheduler())
                .observeOn(rxSchedulers.getMainThreadScheduler())
                .subscribe(this::handleSuccess, this::handleError)));
    }

    private void handleSuccess(WeatherViewModel weatherViewModel) {
        getView().showWeather(weatherViewModel);
    }

    private void handleError(Throwable throwable) {
        getView().showError();
    }

}
