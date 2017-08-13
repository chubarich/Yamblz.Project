package ru.karapetiandav.yamblzproject.business;


import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.reactivex.Completable;
import io.reactivex.observers.TestObserver;
import ru.karapetiandav.yamblzproject.TestDataDependencies;
import ru.karapetiandav.yamblzproject.business.usecases.RemoveCityUseCase;
import ru.karapetiandav.yamblzproject.data.repositories.CitiesRepository;

import static org.mockito.Mockito.when;

public class RemoveCityUseCaseTest {

    @Mock
    CitiesRepository citiesRepository;
    @InjectMocks
    RemoveCityUseCase useCase;
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    private TestDataDependencies deps = new TestDataDependencies();

    @Test
    public void execute_complete() {
        when(citiesRepository.removeCity(deps.getNotEmptyString()))
                .thenReturn(Completable.complete());
        TestObserver testObserver = TestObserver.create();
        useCase.execute(deps.getNotEmptyString()).subscribe(testObserver);
        testObserver.awaitTerminalEvent();
        testObserver.assertNoErrors();
        testObserver.assertComplete();
    }

    @Test
    public void execute_error() {
        when(citiesRepository.removeCity(deps.getEmptyString()))
                .thenReturn(Completable.error(deps.getThrowable()));
        TestObserver testObserver = TestObserver.create();
        useCase.execute(deps.getEmptyString()).subscribe(testObserver);
        testObserver.awaitTerminalEvent();
        testObserver.assertError(deps.getThrowable());
        testObserver.assertNotComplete();
    }
}
