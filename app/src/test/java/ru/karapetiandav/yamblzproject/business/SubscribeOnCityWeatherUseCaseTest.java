package ru.karapetiandav.yamblzproject.business;


import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import ru.karapetiandav.yamblzproject.TestDataDependencies;
import ru.karapetiandav.yamblzproject.business.usecases.SubscribeOnCityWeathersUseCase;
import ru.karapetiandav.yamblzproject.data.repositories.CitiesRepository;
import ru.karapetiandav.yamblzproject.data.repositories.WeatherRepository;
import ru.karapetiandav.yamblzproject.ui.entities.CityWeatherViewModel;
import ru.karapetiandav.yamblzproject.utils.mappers.CityWeatherMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class SubscribeOnCityWeatherUseCaseTest {

    @Mock
    CitiesRepository citiesRepository;
    @Mock
    WeatherRepository weatherRepository;
    @Mock
    CityWeatherMapper mapper;
    @InjectMocks
    SubscribeOnCityWeathersUseCase useCase;
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    private TestDataDependencies deps = new TestDataDependencies();

    @Test
    public void execute_subscribeSuccess() {
        when(citiesRepository.subscribeOnCities())
                .thenReturn(Observable.just(deps.getCityDataModelList()));
        when(weatherRepository.getCurrentWeather(deps.getCityDataModel()))
                .thenReturn( Single.fromCallable(() -> deps.getWeatherDataModel()));
        when(mapper.getCityWeatherViewModel(deps.getCityDataModel(), deps.getWeatherDataModel()))
                .thenReturn(deps.getCityWeatherViewModel());
        TestObserver<CityWeatherViewModel> testObserver = TestObserver.create();
        useCase.execute().subscribe(testObserver);
        testObserver.awaitTerminalEvent();
        testObserver.assertNoErrors();
        testObserver.assertComplete();
        CityWeatherViewModel expected = (CityWeatherViewModel) testObserver.getEvents().get(0)
                .get(0);
        assertThat(expected).isEqualTo(deps.getCityWeatherViewModel());
    }

}
