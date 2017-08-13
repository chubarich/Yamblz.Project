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
import ru.karapetiandav.yamblzproject.business.usecases.GetCitiesMatchesUseCase;
import ru.karapetiandav.yamblzproject.data.repositories.CitiesRepository;
import ru.karapetiandav.yamblzproject.ui.entities.CityViewModel;
import ru.karapetiandav.yamblzproject.utils.mappers.CityMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class GetCitiesMatchesUseCaseTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    @Mock
    CitiesRepository citiesRepository;
    @Mock
    CityMapper cityMapper;
    @InjectMocks
    GetCitiesMatchesUseCase useCase;

    private TestDataDependencies deps;

    @Before
    public void setUp() {
        deps = new TestDataDependencies();
    }

    @Test
    public void execute_notEmptyString_returnList() {
        when(citiesRepository.getCitiesMatches(deps.getNotEmptyString()))
                .thenReturn(Single.just(deps.getCityDataModelList()));
        when(cityMapper.getViewModelList(deps.getCityDataModelList()))
                .thenReturn(deps.getCityViewModelList());
        TestObserver<List<CityViewModel>> testObserver = TestObserver.create();
        useCase.execute(deps.getNotEmptyString()).subscribe(testObserver);
        testObserver.awaitTerminalEvent();
        testObserver.assertNoErrors();
        testObserver.assertComplete();
        List<List<Object>> events = testObserver.getEvents();
        List<CityViewModel> expected = (List<CityViewModel>) events.get(0).get(0);
        events.size();
        assertThat(expected).isEqualTo(deps.getCityViewModelList());
    }

    @Test
    public void execute_emptyString_returnEmptyList() {
        TestObserver<List<CityViewModel>> testObserver = TestObserver.create();
        useCase.execute(deps.getEmptyString()).subscribe(testObserver);
        testObserver.awaitTerminalEvent();
        testObserver.assertNoErrors();
        testObserver.assertComplete();
        List<List<Object>> events = testObserver.getEvents();
        List<CityViewModel> expected = (List<CityViewModel>) events.get(0).get(0);
        assertThat(expected.size()).isEqualTo(0);
    }

    @Test
    public void execute_nullString_returnEmptyList() {
        TestObserver<List<CityViewModel>> testObserver = TestObserver.create();
        useCase.execute(deps.getNullString()).subscribe(testObserver);
        testObserver.awaitTerminalEvent();
        testObserver.assertNoErrors();
        testObserver.assertComplete();
        List<List<Object>> events = testObserver.getEvents();
        List<CityViewModel> expected = (List<CityViewModel>) events.get(0).get(0);
        assertThat(expected.size()).isEqualTo(0);
    }
}
