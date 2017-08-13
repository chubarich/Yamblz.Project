package ru.karapetiandav.yamblzproject.ui.presenters;


import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import ru.karapetiandav.yamblzproject.business.usecases.ChooseCityUseCase;
import ru.karapetiandav.yamblzproject.business.usecases.GetCitiesMatchesUseCase;
import ru.karapetiandav.yamblzproject.ui.entities.CityViewModel;
import ru.karapetiandav.yamblzproject.ui.views.AddCityView;
import ru.karapetiandav.yamblzproject.utils.rx.RxSchedulers;

public class AddCityPresenterImpl extends BasePresenter<AddCityView>
        implements AddCityPresenter<AddCityView> {

    private GetCitiesMatchesUseCase getCitiesMatchesUseCase;
    private ChooseCityUseCase chooseCityUseCase;
    private AddCityPresenterCache cache;
    private RxSchedulers schedulers;

    public AddCityPresenterImpl(GetCitiesMatchesUseCase getCitiesMatchesUseCase,
                                ChooseCityUseCase chooseCityUseCase,
                                AddCityPresenterCache cache,
                                RxSchedulers schedulers) {
        this.getCitiesMatchesUseCase = getCitiesMatchesUseCase;
        this.chooseCityUseCase = chooseCityUseCase;
        this.cache = cache;
        this.schedulers = schedulers;
    }

    @Override
    public void onAttach(AddCityView view) {
        super.onAttach(view);
        if (cache.isCacheExist()) {
            List<CityViewModel> cities = cache.getCities();
            if (cities.size() == 0) {
                view.showNoMatches();
            } else {
                view.showCities(cache.getCities());
            }
        }
    }

    @Override
    public void observeInputChanges(Observable<CharSequence> inputChanges) {
        disposeOnDetach(inputChanges
                .map(CharSequence::toString)
                .observeOn(schedulers.getMainThreadScheduler())
                .doOnNext(this::handleText)
                .observeOn(schedulers.getIOScheduler())
                .flatMap(getCitiesMatchesUseCase::execute)
                .observeOn(schedulers.getMainThreadScheduler())
                .doAfterNext(ignore -> getView().hideProgress())
                .doAfterTerminate(getView()::hideProgress)
                .subscribeOn(schedulers.getIOScheduler())
                .observeOn(schedulers.getMainThreadScheduler())
                .subscribe(this::handleNext, this::handleError));
    }

    private void handleText(String text) {
        if (text.trim().equals("")) {
            getView().showCities(new ArrayList<>());
            getView().hideProgress();
        } else {
            getView().showProgress();
        }
        cache.setLastText(text);
    }

    private void handleNext(@NonNull List<CityViewModel> cities) {
        if (!cities.isEmpty() || cache.getLastText().equals("")) {
            getView().showCities(cities);
        } else {
            getView().showNoMatches();
        }
        cache.updateData(cities);
    }

    private void handleError(Throwable throwable) {
        getView().showError();
    }

    @Override
    public void onCityClick(@NonNull CityViewModel city) {
        chooseCityUseCase.execute(city)
                .subscribeOn(schedulers.getIOScheduler())
                .observeOn(schedulers.getMainThreadScheduler())
                .subscribe(getView()::close, Throwable::printStackTrace);
    }


}
