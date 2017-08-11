package ru.karapetiandav.yamblzproject.business;


import io.reactivex.Completable;
import ru.karapetiandav.yamblzproject.data.repositories.CitiesRepository;

public class RemoveCityUseCase {

    private CitiesRepository citiesRepository;

    public RemoveCityUseCase(CitiesRepository citiesRepository) {
        this.citiesRepository = citiesRepository;
    }

    public Completable execute(String cityId) {
        return citiesRepository.removeCity(cityId);
    }
}
