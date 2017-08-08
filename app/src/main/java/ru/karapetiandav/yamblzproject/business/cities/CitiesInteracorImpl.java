package ru.karapetiandav.yamblzproject.business.cities;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Single;
import ru.karapetiandav.yamblzproject.R;
import ru.karapetiandav.yamblzproject.ui.addcity.model.CityViewModel;
import ru.karapetiandav.yamblzproject.ui.cities.model.CityWeatherViewModel;

public class CitiesInteracorImpl implements CitiesInteractor {

    private static final List<CityWeatherViewModel> CITIES = new ArrayList<>();

    static {
        CITIES.add(new CityWeatherViewModel(
                new CityViewModel("id", "Лондон", "Великобритания"),
                "12°C",
                R.drawable.ic_rain,
                R.color.color_rain));
        CITIES.add(new CityWeatherViewModel(
                new CityViewModel("id", "Сальск", "Ростовская область"),
                "44°C",
                R.drawable.ic_clouds,
                R.color.color_clouds));
        CITIES.add(new CityWeatherViewModel(
                new CityViewModel("id", "Москва", "Россия"),
                "26°C",
                R.drawable.ic_sun,
                R.color.color_hot));
    }

    @Override
    public Single<List<CityWeatherViewModel>> loadCityWeatherList() {
        return Observable.timer(1, TimeUnit.SECONDS)
                .singleOrError()
                .flatMap(ignore -> Single.fromCallable(() -> CITIES));
    }
}
