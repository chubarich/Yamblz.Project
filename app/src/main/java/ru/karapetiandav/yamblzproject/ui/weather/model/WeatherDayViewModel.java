package ru.karapetiandav.yamblzproject.ui.weather.model;


import android.os.Parcel;
import android.os.Parcelable;

public final class WeatherDayViewModel implements Parcelable {

    private final int imageResourceId;
    private final String temp;
    private final String humidity;
    private final String wind;

    public WeatherDayViewModel(int imageResourceId, String temp, String humidity, String wind) {
        this.imageResourceId = imageResourceId;
        this.temp = temp;
        this.humidity = humidity;
        this.wind = wind;
    }

    protected WeatherDayViewModel(Parcel in) {
        imageResourceId = in.readInt();
        temp = in.readString();
        humidity = in.readString();
        wind = in.readString();
    }

    public static final Creator<WeatherDayViewModel> CREATOR = new Creator<WeatherDayViewModel>() {
        @Override
        public WeatherDayViewModel createFromParcel(Parcel in) {
            return new WeatherDayViewModel(in);
        }

        @Override
        public WeatherDayViewModel[] newArray(int size) {
            return new WeatherDayViewModel[size];
        }
    };

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getTemp() {
        return temp;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getWind() {
        return wind;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageResourceId);
        dest.writeString(temp);
        dest.writeString(humidity);
        dest.writeString(wind);
    }
}
