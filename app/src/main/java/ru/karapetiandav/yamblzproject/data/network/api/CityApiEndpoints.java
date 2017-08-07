package ru.karapetiandav.yamblzproject.data.network.api;


public class CityApiEndpoints {
    public static final String BASE_URL = "https://maps.googleapis.com/";
    public static final String KEY = "key";
//    autocomplete
    public static final String AUTOCOMPLETE = "/maps/api/place/autocomplete/json";
    public static final String INPUT = "input";
//    details
    public static final String DETAILS = "/maps/api/place/details/json";
    public static final String PLACE_ID = "placeid";

    public static final String TYPES = "types";
    public static final String TYPES_VALUE = "(cities)";
}
