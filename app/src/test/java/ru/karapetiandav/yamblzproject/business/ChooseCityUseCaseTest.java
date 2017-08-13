package ru.karapetiandav.yamblzproject.business;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.reactivex.Completable;
import io.reactivex.observers.TestObserver;
import ru.karapetiandav.yamblzproject.TestDataDependencies;
import ru.karapetiandav.yamblzproject.data.repositories.CitiesRepository;
import ru.karapetiandav.yamblzproject.utils.mappers.CityMapper;

import static org.mockito.Mockito.when;

public class ChooseCityUseCaseTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    @Mock
    CitiesRepository citiesRepository;
    @Mock
    CityMapper cityMapper;
    @InjectMocks
    ChooseCityUseCase useCase;

    private TestDataDependencies deps;

    @Before
    public void setUp() {
        deps = new TestDataDependencies();
    }

    @Test
    public void execute_success() {
        when(cityMapper.getCityDataModel(deps.getCityViewModel()))
                .thenReturn(deps.getCityDataModel());
        when(citiesRepository.chooseCity(deps.getCityDataModel()))
                .thenReturn(Completable.complete());
        TestObserver testObserver = TestObserver.create();
        useCase.execute(deps.getCityViewModel()).subscribe(testObserver);
        testObserver.awaitTerminalEvent();
        testObserver.assertNoErrors();
        testObserver.assertComplete();
    }

}
