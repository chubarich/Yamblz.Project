package ru.karapetiandav.yamblzproject.ui.presenters;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import ru.karapetiandav.yamblzproject.TestDataDependencies;
import ru.karapetiandav.yamblzproject.business.usecases.ChooseCityUseCase;
import ru.karapetiandav.yamblzproject.business.usecases.GetCitiesMatchesUseCase;
import ru.karapetiandav.yamblzproject.ui.views.AddCityView;
import ru.karapetiandav.yamblzproject.utils.rx.RxSchedulers;
import ru.karapetiandav.yamblzproject.utils.rx.RxSchedulersTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddCityPresenterTest {
    @Mock
    GetCitiesMatchesUseCase getCitiesMatchesUseCase;
    @Mock
    ChooseCityUseCase chooseCityUseCase;
    @Mock
    AddCityPresenterCache cache;
    @Mock
    AddCityView citiesView;
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    private AddCityPresenter<AddCityView> presenter;
    private RxSchedulers schedulers;

    private PublishSubject<CharSequence> inputCityTextListener;
    private TestDataDependencies deps = new TestDataDependencies();

    @Before
    public void setUp() {
        schedulers = new RxSchedulersTest();
        presenter = new AddCityPresenterImpl(getCitiesMatchesUseCase, chooseCityUseCase, cache,
                schedulers);
        inputCityTextListener = PublishSubject.create();
    }

    @Test
    public void onAttach_cacheExists_showList() {
        when(cache.isCacheExist()).thenReturn(true);
        when(cache.getCities()).thenReturn(deps.getCityViewModelList());
        presenter.onAttach(citiesView);
        verify(citiesView, atLeastOnce()).showCities(deps.getCityViewModelList());
        verify(citiesView, never()).showError();
        verify(citiesView, never()).showNoMatches();
        verify(citiesView, never()).close();
    }

    @Test
    public void onAttach_cacheNotExists_doNothing() {
        when(cache.isCacheExist()).thenReturn(false);
        presenter.onAttach(citiesView);
        verify(citiesView, never()).showCities(anyList());
        verify(citiesView, never()).showError();
        verify(citiesView, never()).showNoMatches();
        verify(citiesView, never()).close();
    }

    @Test
    public void onAttach_cacheExistsButHaveNoElements_showNoMatches() {
        when(cache.isCacheExist()).thenReturn(true);
        when(cache.getCities()).thenReturn(new ArrayList<>());
        presenter.onAttach(citiesView);
        verify(citiesView, never()).showCities(anyList());
        verify(citiesView, never()).showError();
        verify(citiesView, only()).showNoMatches();
        verify(citiesView, never()).close();
    }

    @Test
    public void observeChanges_sendValidText_showList() {
        when(getCitiesMatchesUseCase.execute(deps.getNotEmptyString()))
                .thenReturn(Observable.fromCallable(() -> deps.getCityViewModelList()));
        when(cache.getLastText()).thenReturn(deps.getNotEmptyString());
        when(cache.isCacheExist()).thenReturn(Boolean.FALSE);
        presenter.onAttach(citiesView);
        presenter.observeInputChanges(inputCityTextListener);
        inputCityTextListener.onNext(deps.getNotEmptyString());
        verify(citiesView, atLeastOnce()).showCities(deps.getCityViewModelList());
        verify(citiesView, atLeastOnce()).showProgress();
        verify(citiesView, atLeastOnce()).hideProgress();
        verify(citiesView, never()).showError();
        verify(citiesView, never()).showNoMatches();
        verify(citiesView, never()).close();
    }

    @Test
    public void observeChanges_sendEmptyText_showEmptyList() {
        when(cache.getLastText())
                .thenReturn(deps.getEmptyString());
        when(getCitiesMatchesUseCase.execute(any())).thenReturn(Observable.just(new ArrayList<>()));
        presenter.onAttach(citiesView);
        presenter.observeInputChanges(inputCityTextListener);
        inputCityTextListener.onNext(deps.getEmptyString());
        verify(getCitiesMatchesUseCase, atLeastOnce()).execute(any());
        verify(citiesView, atLeastOnce()).showCities(new ArrayList<>());
        verify(citiesView, atLeastOnce()).hideProgress();
        verify(citiesView, never()).showError();
        verify(citiesView, atLeastOnce()).showNoMatches();
        verify(citiesView, never()).close();
    }

    @Test
    public void observeChanges_sendInvalidCityName_showNoMatches() {
        when(cache.getLastText()).thenReturn(deps.getNotEmptyString());
        when(getCitiesMatchesUseCase.execute(deps.getNotEmptyString()))
                .thenReturn(Observable.fromCallable(ArrayList::new));
        presenter.onAttach(citiesView);
        presenter.observeInputChanges(inputCityTextListener);
        inputCityTextListener.onNext(deps.getNotEmptyString());
        verify(citiesView, never()).showCities(anyList());
        verify(citiesView, never()).showError();
        verify(citiesView, atLeastOnce()).showNoMatches();
        verify(citiesView, never()).close();
    }

    @Test
    public void observeChanged_error_showError() {
        presenter.onAttach(citiesView);
        presenter.observeInputChanges(inputCityTextListener);
        inputCityTextListener.onError(new Throwable());
        verify(getCitiesMatchesUseCase, never()).execute(any());
        verify(citiesView, never()).showCities(new ArrayList<>());
        verify(citiesView, atLeastOnce()).showError();
        verify(citiesView, atLeastOnce()).hideProgress();
        verify(citiesView, never()).showNoMatches();
        verify(citiesView, never()).close();
    }

    @Test
    public void onCityClick_sendValidCity_finish() {
        when(chooseCityUseCase.execute(deps.getCityViewModel())).thenReturn(Completable.complete());
        presenter.onAttach(citiesView);
        presenter.onCityClick(deps.getCityViewModel());
        verify(citiesView, never()).showCities(anyList());
        verify(citiesView, never()).showError();
        verify(citiesView, never()).showNoMatches();
        verify(citiesView, atLeastOnce()).close();
    }

    @Test
    public void onDetach_success() {
        presenter.onAttach(citiesView);
        presenter.observeInputChanges(inputCityTextListener);
        presenter.onDetach();
        assertThat(presenter.getView()).isNull();
    }

}
