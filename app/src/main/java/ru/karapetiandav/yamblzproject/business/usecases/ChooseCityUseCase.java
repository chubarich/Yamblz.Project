package ru.karapetiandav.yamblzproject.business.usecases;


import io.reactivex.Completable;
import ru.karapetiandav.yamblzproject.data.repositories.CitiesRepository;
import ru.karapetiandav.yamblzproject.ui.entities.CityViewModel;
import ru.karapetiandav.yamblzproject.utils.mappers.CityMapper;

public class ChooseCityUseCase {

    private CitiesRepository citiesRepository;
    private CityMapper cityMapper;

    public ChooseCityUseCase(CitiesRepository citiesRepository, CityMapper cityMapper) {
        this.citiesRepository = citiesRepository;
        this.cityMapper = cityMapper;
    }

    public Completable execute(CityViewModel city) {
        return citiesRepository.chooseCity(cityMapper.getCityDataModel(city));
    }
}
