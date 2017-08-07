package ru.karapetiandav.yamblzproject.data.network.api;


import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.karapetiandav.yamblzproject.data.network.model.city.autocomplete.CitiesResponse;
import ru.karapetiandav.yamblzproject.data.network.model.city.details.CityDetails;

public interface CityApi {

    @GET(CityApiEndpoints.AUTOCOMPLETE)
    Single<CitiesResponse> getCities(
            @Query(CityApiEndpoints.INPUT) String input,
            @Query(CityApiEndpoints.KEY) String key,
            @Query(CityApiEndpoints.TYPES) String types
    );

    @GET(CityApiEndpoints.DETAILS)
    Single<CityDetails> getCityDetails(
            @Query(CityApiEndpoints.PLACE_ID) String placeId,
            @Query(CityApiEndpoints.KEY) String key
    );

}
