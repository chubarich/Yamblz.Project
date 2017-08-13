package ru.karapetiandav.yamblzproject.business;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import ru.karapetiandav.yamblzproject.TestDataDependencies;
import ru.karapetiandav.yamblzproject.business.usecases.GetForecastUseCase;
import ru.karapetiandav.yamblzproject.data.repositories.CitiesRepository;
import ru.karapetiandav.yamblzproject.data.repositories.WeatherRepository;
import ru.karapetiandav.yamblzproject.ui.entities.WeatherViewModel;
import ru.karapetiandav.yamblzproject.utils.mappers.WeatherMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class GetForecastUseCaseTest {

    @Mock
    WeatherRepository weatherRepository;
    @Mock
    CitiesRepository citiesRepository;
    @Mock
    WeatherMapper weatherMapper;
    @InjectMocks
    GetForecastUseCase getForecastUseCase;
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();


    private TestDataDependencies deps;

    @Before
    public void setUp() {
        deps = new TestDataDependencies();
    }

    @Test
    public void execute_success() {
        when(citiesRepository.getCity(deps.getNotEmptyString()))
                .thenReturn(Single.just(deps.getCityDataModel()));
        when(weatherRepository.getForecast(deps.getCityDataModel()))
                .thenReturn(Single.just(deps.getForecastDataModelList()));
        when(weatherMapper.getWeatherViewModels(deps.getForecastDataModelList()))
                .thenReturn(deps.getWeatherViewModelList());
        TestObserver<List<WeatherViewModel>> testObserver = TestObserver.create();
        getForecastUseCase.execute(deps.getNotEmptyString()).subscribe(testObserver);
        testObserver.awaitTerminalEvent();
        testObserver.assertNoErrors();
        testObserver.assertComplete();
        List<List<Object>> events = testObserver.getEvents();
        List<WeatherViewModel> expected = (List<WeatherViewModel>) events.get(0).get(0);
        assertThat(expected).isEqualTo(deps.getWeatherViewModelList());
    }
}
