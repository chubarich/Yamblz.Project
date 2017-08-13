package ru.karapetiandav.yamblzproject.ui.entities;


import android.os.Parcel;
import android.os.Parcelable;

public final class CityViewModel implements Parcelable {

    private final String cityId;
    private final String cityName;
    private final String country;

    public CityViewModel(String cityId, String cityName, String country) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.country = country;
    }

    protected CityViewModel(Parcel in) {
        cityId = in.readString();
        cityName = in.readString();
        country = in.readString();
    }

    public String getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cityId);
        dest.writeString(cityName);
        dest.writeString(country);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CityViewModel> CREATOR = new Creator<CityViewModel>() {
        @Override
        public CityViewModel createFromParcel(Parcel in) {
            return new CityViewModel(in);
        }

        @Override
        public CityViewModel[] newArray(int size) {
            return new CityViewModel[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CityViewModel that = (CityViewModel) o;

        if (getCityId() != null ? !getCityId().equals(that.getCityId()) : that.getCityId() != null)
            return false;
        if (getCityName() != null ? !getCityName().equals(that.getCityName()) : that.getCityName() != null)
            return false;
        return getCountry() != null ? getCountry().equals(that.getCountry()) : that.getCountry() == null;

    }
}
