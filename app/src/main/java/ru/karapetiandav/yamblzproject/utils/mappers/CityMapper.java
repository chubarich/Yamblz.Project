package ru.karapetiandav.yamblzproject.utils.mappers;


import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import ru.karapetiandav.yamblzproject.data.model.CityDataModel;
import ru.karapetiandav.yamblzproject.data.network.model.city.autocomplete.CitiesResponse;
import ru.karapetiandav.yamblzproject.data.network.model.city.autocomplete.Prediction;
import ru.karapetiandav.yamblzproject.data.network.model.city.details.CityDetails;
import ru.karapetiandav.yamblzproject.data.network.model.city.details.Location;
import ru.karapetiandav.yamblzproject.ui.entities.CityViewModel;

public class CityMapper {

    private static final float NO_COORDS = 0f;

    public List<CityDataModel> getCitiesFromResponse(CitiesResponse response) {
        List<CityDataModel> cityDataModels = new ArrayList<>();
        for(Prediction prediction : response.getPredictions()) {
            cityDataModels.add(getCityDataModel(prediction));
        }
        return cityDataModels;
    }


    public CityDataModel getCityDataModel(Prediction prediction) {
        return new CityDataModel(
                prediction.getPlaceId(),
                prediction.getStructuredFormatting().getMainText(),
                prediction.getStructuredFormatting().getSecondaryText(),
                NO_COORDS,
                NO_COORDS
        );
    }

    public CityDataModel getCityDataModelWithCoords(CityDataModel city, CityDetails cityDetails) {
        Location location = cityDetails.getResult().getGeometry().getLocation();
        return new CityDataModel(
                city.getCityId(),
                city.getCity(),
                city.getCountry(),
                location.getLat(),
                location.getLng()
        );
    }

    public List<CityViewModel> getViewModelList(@NonNull List<CityDataModel> dataModels) {
        List<CityViewModel> viewModels = new ArrayList<>();
        if (dataModels.size() == 0) return viewModels;
        for(CityDataModel dataModel : dataModels) {
            viewModels.add(getViewModel(dataModel));
        }
        return viewModels;
    }

    public CityViewModel getViewModel(CityDataModel dataModel) {
        return new CityViewModel(dataModel.getCityId(), dataModel.getCity(),
                dataModel.getCountry());
    }

    public CityDataModel getCityDataModel(CityViewModel viewModel) {
        return new CityDataModel(viewModel.getCityId(), viewModel.getCityName(),
                viewModel.getCountry(), 0f, 0f);
    }

}
