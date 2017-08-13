package ru.karapetiandav.yamblzproject.data.model;


public class ForecastDataModel {

    private int date;
    private int weatherId;
    private double min;
    private double max;
    private double morn;
    private double day;
    private double eve;
    private double night;
    private double pressure;
    private double humidity;
    private double speed;
    private double deg;
    private String cityId;

    public ForecastDataModel() {
    }

    public ForecastDataModel(int date, int weatherId, double min, double max, double morn,
                             double day, double eve, double night, double pressure,
                             double humidity, double speed, double deg, String cityId) {
        this.date = date;
        this.weatherId = weatherId;
        this.min = min;
        this.max = max;
        this.morn = morn;
        this.day = day;
        this.eve = eve;
        this.night = night;
        this.pressure = pressure;
        this.humidity = humidity;
        this.speed = speed;
        this.deg = deg;
        this.cityId = cityId;
    }

    public int getDate() {
        return date;
    }

    public int getWeatherId() {
        return weatherId;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public double getMorn() {
        return morn;
    }

    public double getDay() {
        return day;
    }

    public double getEve() {
        return eve;
    }

    public double getNight() {
        return night;
    }

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDeg() {
        return deg;
    }

    public String getCityId() {
        return cityId;
    }

    public static class Builder {
        private int date;
        private int weatherId;
        private double min;
        private double max;
        private double morn;
        private double day;
        private double eve;
        private double night;
        private double pressure;
        private double humidity;
        private double speed;
        private double deg;
        private String cityId;

        public Builder setDate(int date) {
            this.date = date;
            return this;
        }

        public Builder setWeatherId(int weatherId) {
            this.weatherId = weatherId;
            return this;
        }

        public Builder setMin(double min) {
            this.min = min;
            return this;
        }

        public Builder setMax(double max) {
            this.max = max;
            return this;
        }

        public Builder setMorn(double morn) {
            this.morn = morn;
            return this;
        }

        public Builder setDay(double day) {
            this.day = day;
            return this;
        }

        public Builder setEve(double eve) {
            this.eve = eve;
            return this;
        }

        public Builder setNight(double night) {
            this.night = night;
            return this;
        }

        public Builder setPressure(double pressure) {
            this.pressure = pressure;
            return this;
        }

        public Builder setHumidity(double humidity) {
            this.humidity = humidity;
            return this;
        }

        public Builder setSpeed(double speed) {
            this.speed = speed;
            return this;
        }

        public Builder setDeg(double deg) {
            this.deg = deg;
            return this;
        }

        public Builder setCityId(String cityId) {
            this.cityId = cityId;
            return this;
        }

        public ForecastDataModel build() {
            return new ForecastDataModel(date, weatherId, min, max, morn, day, eve, night, pressure,
                    humidity, speed, deg, cityId);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ForecastDataModel that = (ForecastDataModel) o;

        if (getDate() != that.getDate()) return false;
        if (getWeatherId() != that.getWeatherId()) return false;
        if (Double.compare(that.getMin(), getMin()) != 0) return false;
        if (Double.compare(that.getMax(), getMax()) != 0) return false;
        if (Double.compare(that.getMorn(), getMorn()) != 0) return false;
        if (Double.compare(that.getDay(), getDay()) != 0) return false;
        if (Double.compare(that.getEve(), getEve()) != 0) return false;
        if (Double.compare(that.getNight(), getNight()) != 0) return false;
        if (Double.compare(that.getPressure(), getPressure()) != 0) return false;
        if (Double.compare(that.getHumidity(), getHumidity()) != 0) return false;
        if (Double.compare(that.getSpeed(), getSpeed()) != 0) return false;
        if (Double.compare(that.getDeg(), getDeg()) != 0) return false;
        return getCityId() != null ? getCityId().equals(that.getCityId()) : that.getCityId() == null;

    }
}
