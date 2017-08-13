package ru.karapetiandav.yamblzproject.ui.presenters;

import java.util.List;

import ru.karapetiandav.yamblzproject.business.GetForecastUseCase;
import ru.karapetiandav.yamblzproject.ui.entities.CityViewModel;
import ru.karapetiandav.yamblzproject.ui.entities.WeatherViewModel;
import ru.karapetiandav.yamblzproject.ui.views.WeatherView;
import ru.karapetiandav.yamblzproject.utils.rx.RxSchedulers;

public class WeatherPresenterImpl extends BasePresenter<WeatherView>
        implements WeatherPresenter<WeatherView> {

    private GetForecastUseCase getForecastUseCase;
    private RxSchedulers schedulers;

    private CityViewModel city;

    public WeatherPresenterImpl(GetForecastUseCase getForecastUseCase,
                                RxSchedulers schedulers) {
        this.getForecastUseCase = getForecastUseCase;
        this.schedulers = schedulers;
    }

    @Override
    public void onCityRestored(CityViewModel city) {
        this.city = city;
        getView().showTitle(city.getCityName());
        loadWeather();
    }

    private void loadWeather() {
        disposeOnDetach((getForecastUseCase.execute(city.getCityId())
                .observeOn(schedulers.getMainThreadScheduler())
                .doAfterTerminate(getView()::hideLoading)
                .subscribeOn(schedulers.getIOScheduler())
                .observeOn(schedulers.getMainThreadScheduler())
                .subscribe(this::handleSuccess, this::handleError)));
    }

    private void handleSuccess(List<WeatherViewModel> weatherList) {
        getView().showWeather(weatherList);
    }

    private void handleError(Throwable throwable) {
        getView().showError();
    }

    @Override
    public void onSwipeToRefresh() {
        loadWeather();
    }
}
