package ru.karapetiandav.yamblzproject.utils.mappers;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ru.karapetiandav.yamblzproject.data.model.CityDataModel;
import ru.karapetiandav.yamblzproject.data.network.model.city.autocomplete.CitiesResponse;
import ru.karapetiandav.yamblzproject.data.network.model.city.autocomplete.Prediction;
import ru.karapetiandav.yamblzproject.data.network.model.city.autocomplete.StructuredFormatting;
import ru.karapetiandav.yamblzproject.data.network.model.city.details.CityDetails;
import ru.karapetiandav.yamblzproject.data.network.model.city.details.Geometry;
import ru.karapetiandav.yamblzproject.data.network.model.city.details.Location;
import ru.karapetiandav.yamblzproject.data.network.model.city.details.Result;
import ru.karapetiandav.yamblzproject.ui.entities.CityViewModel;

import static org.assertj.core.api.Assertions.assertThat;

public class CityMapperTest {

    private CityMapper cityMapper;

    private CitiesResponse response;

    private CityDataModel cityDataModel;

    private CityDetails cityDetails;

    private List<CityDataModel> cityDataModels;
    private List<CityViewModel> cityViewModels;

    @Before
    public void setUp() {
        cityDetails = new CityDetails();
        Location location = new Location();
        location.setLat(1d);
        location.setLng(2d);
        Geometry geometry = new Geometry();
        geometry.setLocation(location);
        Result result = new Result();
        result.setGeometry(geometry);
        cityDetails.setResult(result);

        response = new CitiesResponse();
        List<Prediction> predictions = new ArrayList<>();
        StructuredFormatting structuredFormatting = new StructuredFormatting();
        structuredFormatting.setMainText("main");
        structuredFormatting.setSecondaryText("sec");
        Prediction prediction = new Prediction();
        prediction.setPlaceId("qwer");
        prediction.setStructuredFormatting(structuredFormatting);
        predictions.add(prediction);
        response.setPredictions(predictions);
        cityDataModel = new CityDataModel("qwer", "main", "sec", 0d, 0d);
        cityMapper = new CityMapper();

        cityDataModels = new ArrayList<>();
        cityViewModels = new ArrayList<>();
        cityDataModels.add(new CityDataModel("id", "id", "id", 4d, 5d));
        cityViewModels.add(new CityViewModel("id", "id", "id"));
    }

    @Test
    public void test() {
        List<CityDataModel> expected = new ArrayList<>();
        expected.add(cityDataModel);
        List<CityDataModel> real = cityMapper.getCitiesFromResponse(response);
        assertThat(real).isEqualTo(expected);

        CityDataModel dataModel = cityMapper.getCityDataModelWithCoords(cityDataModel, cityDetails);
        CityDataModel dataModel1 = new CityDataModel("qwer", "main", "sec", 1d, 2d);
        assertThat(dataModel).isEqualTo(dataModel1);

        List<CityViewModel> expectedViews = cityMapper.getViewModelList(cityDataModels);
        assertThat(cityViewModels).isEqualTo(expectedViews);

        CityDataModel dm = cityMapper.getCityDataModel(new CityViewModel("qwer", "main", "sec"));
        assertThat(dm).isEqualTo(cityDataModel);
    }
}
