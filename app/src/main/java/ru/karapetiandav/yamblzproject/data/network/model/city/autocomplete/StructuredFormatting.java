
package ru.karapetiandav.yamblzproject.data.network.model.city.autocomplete;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StructuredFormatting {

    @SerializedName("main_text")
    @Expose
    private String mainText;
    @SerializedName("secondary_text")
    @Expose
    private String secondaryText;

    public String getMainText() {
        return mainText;
    }

    public String getSecondaryText() {
        return secondaryText;
    }

}
