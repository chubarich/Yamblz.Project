package ru.karapetiandav.yamblzproject.business.addcity;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import ru.karapetiandav.yamblzproject.business.addcity.interactor.AddCityInteractor;
import ru.karapetiandav.yamblzproject.business.addcity.interactor.AddCityInteractorImpl;
import ru.karapetiandav.yamblzproject.business.addcity.mapper.CityMapper;
import ru.karapetiandav.yamblzproject.data.model.CityDataModel;
import ru.karapetiandav.yamblzproject.data.model.Language;
import ru.karapetiandav.yamblzproject.data.repositories.addcity.AddCityRepository;
import ru.karapetiandav.yamblzproject.ui.addcity.model.CityViewModel;
import ru.karapetiandav.yamblzproject.utils.LanguageUtils;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddCityInteractorTest {

    private AddCityRepository addCityRepository;
    private CityMapper cityMapper;
    private AddCityInteractor interactor;
    private LanguageUtils languageUtils;

    private List<CityDataModel> CITIES = new ArrayList<>();

    @Before
    public void setUp() {
        addCityRepository = mock(AddCityRepository.class);
        cityMapper = mock(CityMapper.class);
        languageUtils = mock(LanguageUtils.class);
        interactor = new AddCityInteractorImpl(addCityRepository, cityMapper, languageUtils);
        CITIES.add(new CityDataModel(12345, "Moscow", "RU"));
    }

    @Test
    public void getCitiesMatches_sendValid() {
        String text = "text";
        when(languageUtils.getSupportedLanguageByText(text)).thenReturn(Language.ENG);
        when(addCityRepository
                .getCitiesMatches(text, languageUtils.getSupportedLanguageByText(text)))
                .thenReturn(Single.fromCallable(() -> CITIES));
        TestObserver<List<CityViewModel>> testObserver = TestObserver.create();
        interactor.getCitiesMatches(text).subscribe(testObserver);
        testObserver.awaitTerminalEvent();
        testObserver.assertNoErrors();
        testObserver.assertComplete();
    }

    @Test
    public void getCitiesMatches_error() {
        String text = "text";
        Throwable throwable = new Throwable();
        when(languageUtils.getSupportedLanguageByText(text)).thenReturn(Language.ENG);
        when(addCityRepository
                .getCitiesMatches(text, languageUtils.getSupportedLanguageByText(text)))
                .thenReturn(Single.error(throwable));
        TestObserver<List<CityViewModel>> testObserver = TestObserver.create();
        interactor.getCitiesMatches(text).subscribe(testObserver);
        testObserver.awaitTerminalEvent();
        testObserver.assertError(throwable);
        testObserver.assertNotComplete();
    }

    @Test
    public void saveCity_sendValid() {
        CityViewModel cityViewModel = new CityViewModel("Moscow", "12345");
        when(cityMapper.getDataModel(cityViewModel)).thenReturn(any());
        when(addCityRepository.saveCity(cityMapper.getDataModel(cityViewModel)))
                .thenReturn(Completable.complete());
        TestObserver testObserver = TestObserver.create();
        interactor.saveCity(cityViewModel).subscribe(testObserver);
        testObserver.awaitTerminalEvent();
        testObserver.assertNoErrors();
        testObserver.assertComplete();
    }
}
