
package ru.karapetiandav.yamblzproject.data.network.model.city.autocomplete;

import java.util.List;

public class CitiesResponse {

    private List<Prediction> predictions = null;

    public List<Prediction> getPredictions() {
        return predictions;
    }

    public void setPredictions(List<Prediction> predictions) {
        this.predictions = predictions;
    }
}
