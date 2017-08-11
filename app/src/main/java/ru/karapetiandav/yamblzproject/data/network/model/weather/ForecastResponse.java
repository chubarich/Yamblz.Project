
package ru.karapetiandav.yamblzproject.data.network.model.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ForecastResponse {

    @SerializedName("list")
    @Expose
    private List<Day> days = new ArrayList<>();

    public List<Day> getDays() {
        return days;
    }

}
