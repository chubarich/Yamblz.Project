package ru.karapetiandav.yamblzproject.ui.addcity.presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.PublishSubject;
import ru.karapetiandav.yamblzproject.business.addcity.interactor.AddCityInteractor;
import ru.karapetiandav.yamblzproject.ui.addcity.model.CityViewModel;
import ru.karapetiandav.yamblzproject.ui.addcity.view.AddCityView;
import ru.karapetiandav.yamblzproject.utils.rx.RxSchedulers;
import ru.karapetiandav.yamblzproject.utils.rx.RxSchedulersTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddCityPresenterTest {

    private AddCityPresenter<AddCityView> addCityPresenter;
    private AddCityInteractor addCityInteractor;
    private AddCityView addCityView;
    private AddCityPresenterCache addCityPresenterCache;
    private CompositeDisposable compositeDisposable;
    private RxSchedulers rxSchedulers;
    private PublishSubject<CharSequence> inputCityTextListener;

    private final List<CityViewModel> CITIES = new ArrayList<>();
    private final List<CityViewModel> CITIES_EMPTY = new ArrayList<>();

    @Before
    public void setUp() {
        compositeDisposable = new CompositeDisposable();
        addCityInteractor = mock(AddCityInteractor.class);
        addCityView = mock(AddCityView.class);
        addCityPresenterCache = mock(AddCityPresenterCache.class);
        rxSchedulers = new RxSchedulersTest();
        addCityPresenter = new AddCityPresenterImpl(addCityInteractor, compositeDisposable,
                addCityPresenterCache, rxSchedulers);
        inputCityTextListener  = PublishSubject.create();;

        CITIES.add(new CityViewModel("Москва", "524901"));
        CITIES.add(new CityViewModel("Катманду", "524901"));
        CITIES.add(new CityViewModel("Дели", "1273294"));
        CITIES.add(new CityViewModel("Киев", "703448"));
        CITIES.add(new CityViewModel("Покхара", "1282898"));
        CITIES.add(new CityViewModel("Мерида", "3632308"));

    }

    @Test
    public void onAttach_cacheExists_showList() {
        when(addCityPresenterCache.isCacheExist()).thenReturn(true);
        when(addCityPresenterCache.getCities()).thenReturn(CITIES);
        addCityPresenter.onAttach(addCityView);
        verify(addCityView, only()).showCities(CITIES);
        verify(addCityView, never()).showError();
        verify(addCityView, never()).showNoMatches();
        verify(addCityView, never()).close();
    }

    @Test
    public void onAttach_cacheNotExists_doNothing() {
        when(addCityPresenterCache.isCacheExist()).thenReturn(false);
        addCityPresenter.onAttach(addCityView);
        verify(addCityView, never()).showCities(anyList());
        verify(addCityView, never()).showError();
        verify(addCityView, never()).showNoMatches();
        verify(addCityView, never()).close();
    }

    @Test
    public void onAttach_cacheExistsButHaveNoElements_showNoMatches() {
        when(addCityPresenterCache.isCacheExist()).thenReturn(true);
        when(addCityPresenterCache.getCities()).thenReturn(CITIES_EMPTY);
        addCityPresenter.onAttach(addCityView);
        verify(addCityView, never()).showCities(anyList());
        verify(addCityView, never()).showError();
        verify(addCityView, only()).showNoMatches();
        verify(addCityView, never()).close();
    }

    @Test
    public void observeChanges_sendValidText_showList() {
        String text = "Mos";
        when(addCityInteractor.getCitiesMatches(text))
                .thenReturn(Observable.fromCallable(() -> CITIES));
        when(addCityPresenterCache.getLastText())
                .thenReturn(text);
        addCityPresenter.onAttach(addCityView);
        addCityPresenter.observeInputChanges(inputCityTextListener);
        inputCityTextListener.onNext(text);
        verify(addCityView, only()).showCities(CITIES);
        verify(addCityView, never()).showError();
        verify(addCityView, never()).showNoMatches();
        verify(addCityView, never()).close();
    }

    @Test
    public void observeChanges_sendEmptyText_showEmptyList() {
        String text = "";
        when(addCityPresenterCache.getLastText())
                .thenReturn(text);
        addCityPresenter.onAttach(addCityView);
        addCityPresenter.observeInputChanges(inputCityTextListener);
        inputCityTextListener.onNext(text);
        verify(addCityInteractor, never()).getCitiesMatches(Mockito.any());
        verify(addCityView, only()).showCities(CITIES_EMPTY);
        verify(addCityView, never()).showError();
        verify(addCityView, never()).showNoMatches();
        verify(addCityView, never()).close();
    }

    @Test
    public void observeChanges_sendInvalidCityName_showNoMatches() {
        String text = "qwscxfg";
        when(addCityPresenterCache.getLastText()).thenReturn(text);
        when(addCityInteractor.getCitiesMatches(text))
                .thenReturn(Observable.fromCallable(() -> CITIES_EMPTY));
        addCityPresenter.onAttach(addCityView);
        addCityPresenter.observeInputChanges(inputCityTextListener);
        inputCityTextListener.onNext(text);
        verify(addCityView, never()).showCities(anyList());
        verify(addCityView, never()).showError();
        verify(addCityView, only()).showNoMatches();
        verify(addCityView, never()).close();
    }

    @Test
    public void observeChanged_error_showError() {
        addCityPresenter.onAttach(addCityView);
        addCityPresenter.observeInputChanges(inputCityTextListener);
        inputCityTextListener.onError(new Throwable());
        verify(addCityInteractor, never()).getCitiesMatches(Mockito.any());
        verify(addCityView, never()).showCities(CITIES_EMPTY);
        verify(addCityView, only()).showError();
        verify(addCityView, never()).showNoMatches();
        verify(addCityView, never()).close();
    }

    @Test
    public void onCityClick_sendValidCity_finish() {
        CityViewModel city = CITIES.get(0);
        when(addCityInteractor.saveCity(city)).thenReturn(Completable.complete());
        addCityPresenter.onAttach(addCityView);
        addCityPresenter.onCityClick(city);
        verify(addCityView, never()).showCities(anyList());
        verify(addCityView, never()).showError();
        verify(addCityView, never()).showNoMatches();
        verify(addCityView, only()).close();
    }

    @Test
    public void onDetach_success() {
        addCityPresenter.onAttach(addCityView);
        addCityPresenter.observeInputChanges(inputCityTextListener);
        addCityPresenter.onDetach();
        assertThat(compositeDisposable.size()).isEqualTo(0);
    }
}
