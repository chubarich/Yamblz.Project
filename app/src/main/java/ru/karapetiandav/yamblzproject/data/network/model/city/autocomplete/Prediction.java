
package ru.karapetiandav.yamblzproject.data.network.model.city.autocomplete;

import com.google.gson.annotations.SerializedName;

public class Prediction {

    @SerializedName("place_id")
    private String placeId;
    @SerializedName("structured_formatting")
    private StructuredFormatting structuredFormatting;

    public String getPlaceId() {
        return placeId;
    }

    public StructuredFormatting getStructuredFormatting() {
        return structuredFormatting;
    }

}
