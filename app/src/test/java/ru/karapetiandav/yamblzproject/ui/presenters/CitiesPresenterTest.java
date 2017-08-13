package ru.karapetiandav.yamblzproject.ui.presenters;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.reactivex.Completable;
import io.reactivex.Observable;
import ru.karapetiandav.yamblzproject.TestDataDependencies;
import ru.karapetiandav.yamblzproject.business.usecases.RemoveCityUseCase;
import ru.karapetiandav.yamblzproject.business.usecases.SubscribeOnCityWeathersUseCase;
import ru.karapetiandav.yamblzproject.ui.views.CitiesView;
import ru.karapetiandav.yamblzproject.utils.rx.RxSchedulers;
import ru.karapetiandav.yamblzproject.utils.rx.RxSchedulersTest;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CitiesPresenterTest {

    @Mock
    SubscribeOnCityWeathersUseCase subscribeOnCityWeathersUseCase;
    @Mock
    RemoveCityUseCase removeCityUseCase;
    @Mock
    CitiesView citiesView;
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    private RxSchedulers rxSchedulers;
    private TestDataDependencies deps;

    private CitiesPresenter<CitiesView> presenter;

    @Before
    public void setUp() {
        rxSchedulers = new RxSchedulersTest();
        presenter = new CitiesPresenterImpl(subscribeOnCityWeathersUseCase, rxSchedulers,
                removeCityUseCase);
        deps = new TestDataDependencies();
    }

    @Test
    public void test_success() {
        when(subscribeOnCityWeathersUseCase.execute())
                .thenReturn(Observable.just(deps.getCityWeatherViewModel()));
        when(removeCityUseCase.execute(deps.getCityViewModel().getCityId()))
                .thenReturn(Completable.complete());
        presenter.onAttach(citiesView);
        presenter.onCityClick(deps.getCityViewModel());
        presenter.onAddCityClick();
        presenter.onSwipeToRefresh();
        presenter.onCityRemoved(deps.getCityViewModel());
        verify(citiesView, atLeastOnce()).showCity(deps.getCityWeatherViewModel());
        verify(citiesView, atLeastOnce()).showLoading();
        verify(citiesView, atLeastOnce()).hideLoading();
        verify(citiesView, atLeastOnce()).showWeatherActivity(deps.getCityViewModel());
        verify(citiesView, atLeastOnce()).showAddNewCity();
        verify(citiesView, never()).showError();
    }

    @Test
    public void test_error() {
        when(subscribeOnCityWeathersUseCase.execute())
                .thenReturn(Observable.error(deps.getThrowable()));
        presenter.onAttach(citiesView);
        verify(citiesView, atLeastOnce()).showError();
    }
}
