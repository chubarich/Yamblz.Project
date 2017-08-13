package ru.karapetiandav.yamblzproject.ui.presenters;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.reactivex.Single;
import ru.karapetiandav.yamblzproject.TestDataDependencies;
import ru.karapetiandav.yamblzproject.business.usecases.GetForecastUseCase;
import ru.karapetiandav.yamblzproject.ui.views.WeatherView;
import ru.karapetiandav.yamblzproject.utils.rx.RxSchedulers;
import ru.karapetiandav.yamblzproject.utils.rx.RxSchedulersTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WeatherPresenterTest {

    @Mock
    WeatherView weatherView;
    @Mock
    GetForecastUseCase getForecastUseCase;
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    private WeatherPresenter<WeatherView> weatherPresenter;
    private RxSchedulers rxSchedulers;
    private TestDataDependencies deps;


    @Before
    public void setUp() {
        deps = new TestDataDependencies();
        rxSchedulers = new RxSchedulersTest();
        weatherPresenter = new WeatherPresenterImpl(getForecastUseCase, rxSchedulers);
    }

    @Test
    public void onAttach_loadWeather_success() {
        when(getForecastUseCase.execute(deps.getCityViewModel().getCityId()))
                .thenReturn(Single.fromCallable(() -> deps.getWeatherViewModelList()));
        weatherPresenter.onAttach(weatherView);
        weatherPresenter.onCityRestored(deps.getCityViewModel());
        weatherPresenter.onSwipeToRefresh();
        verify(weatherView, atLeastOnce()).showWeather(deps.getWeatherViewModelList());
        verify(weatherView, never()).showError();
        verify(weatherView, atLeastOnce()).showTitle(any());
    }

    @Test
    public void onAttach_loadWeather_fail() {
        when(getForecastUseCase.execute(any()))
                .thenReturn(Single.error(deps.getThrowable()));
        weatherPresenter.onAttach(weatherView);
        weatherPresenter.onCityRestored(deps.getCityViewModel());
        verify(weatherView, never()).showWeather(any());
        verify(weatherView, atLeastOnce()).showError();
    }

    @Test
    public void onDetach_test() {
        when(getForecastUseCase.execute(any()))
                .thenReturn(Single.fromCallable(deps::getWeatherViewModelList));
        weatherPresenter.onAttach(weatherView);
        weatherPresenter.onDetach();
        assertThat(weatherPresenter.getView()).isNull();
    }
}
