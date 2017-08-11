package ru.karapetiandav.yamblzproject.ui.entities;


public final class WeatherViewModel {

    private final int iconId;
    private final int colorId;
    private final String dayOfWeek;
    private final String maxTemp;
    private final String minTemp;
    private final String humidity;
    private final String wind;
    private final String pressure;
    private final String morningTemp;
    private final String dayTemp;
    private final String eveningTemp;
    private final String nightTemp;


    private WeatherViewModel(int iconId, int colorId, String dayOfWeek, String maxTemp,
                             String minTemp, String humidity, String wind, String pressure,
                             String morningTemp, String dayTemp, String eveningTemp,
                             String nightTemp) {
        this.iconId = iconId;
        this.colorId = colorId;
        this.dayOfWeek = dayOfWeek;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.humidity = humidity;
        this.wind = wind;
        this.pressure = pressure;
        this.morningTemp = morningTemp;
        this.dayTemp = dayTemp;
        this.eveningTemp = eveningTemp;
        this.nightTemp = nightTemp;
    }

    public int getIconId() {
        return iconId;
    }

    public int getColorId() {
        return colorId;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getWind() {
        return wind;
    }

    public String getPressure() {
        return pressure;
    }

    public String getMorningTemp() {
        return morningTemp;
    }

    public String getDayTemp() {
        return dayTemp;
    }

    public String getEveningTemp() {
        return eveningTemp;
    }

    public String getNightTemp() {
        return nightTemp;
    }

    public static class Builder {
        private int iconId;
        private int colorId;
        private String dayOfWeek;
        private String maxTemp;
        private String minTemp;
        private String humidity;
        private String wind;
        private String pressure;
        private String morningTemp;
        private String dayTemp;
        private String eveningTemp;
        private String nightTemp;

        public Builder setIconId(int iconId) {
            this.iconId = iconId;
            return this;
        }

        public Builder setColorId(int colorId) {
            this.colorId = colorId;
            return this;
        }

        public Builder setDayOfWeek(String dayOfWeek) {
            this.dayOfWeek = dayOfWeek;
            return this;
        }

        public Builder setMaxTemp(String maxTemp) {
            this.maxTemp = maxTemp;
            return this;
        }

        public Builder setMinTemp(String minTemp) {
            this.minTemp = minTemp;
            return this;
        }

        public Builder setHumidity(String humidity) {
            this.humidity = humidity;
            return this;
        }

        public Builder setWind(String wind) {
            this.wind = wind;
            return this;
        }

        public Builder setPressure(String pressure) {
            this.pressure = pressure;
            return this;
        }

        public Builder setMorningTemp(String morningTemp) {
            this.morningTemp = morningTemp;
            return this;
        }

        public Builder setDayTemp(String dayTemp) {
            this.dayTemp = dayTemp;
            return this;
        }

        public Builder setEveningTemp(String eveningTemp) {
            this.eveningTemp = eveningTemp;
            return this;
        }

        public Builder setNightTemp(String nightTemp) {
            this.nightTemp = nightTemp;
            return this;
        }

        public WeatherViewModel build() {
            return new WeatherViewModel(iconId, colorId, dayOfWeek, maxTemp, minTemp, humidity,
                    wind, pressure, morningTemp, dayTemp, eveningTemp, nightTemp);
        }
    }
}
