package ru.karapetiandav.yamblzproject.utils;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.os.Build;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ru.karapetiandav.yamblzproject.R;

public class Utils {

    private Resources resources;

    public Utils(Resources resources) {
        this.resources = resources;
    }


    public String formatPressure(double pressure) {
        return String.format(resources.getString(R.string.format_pressure), String.valueOf(Math.round(pressure / 10)));
    }

    public String formatHumidity(double humidity) {
        return String.format(resources.getString(R.string.format_humidity),
                String.valueOf(Math.round(humidity)));
    }

    public String formatWind(double wind) {
        return String.format(resources.getString(R.string.format_wind),
                String.valueOf(Math.round(wind)));
    }

    public String convertUnixTimeToString(long timeInMillis) {
        Date date = new Date(timeInMillis);
        return resources.getString(R.string.weather_fragment_today) + " " +
                new SimpleDateFormat("d MMM").format(date);
    }

    public String formatTemperature(double temperature) {
        if (resources.getString(R.string.pressure_measure_unit).equals("hpa")) {
            return (int) Math.floor((temperature - 273) * 1.8 + 32) + resources.getString(R.string.temp_measure_unit);
        } else {
            return (int) Math.floor(temperature - 273) + resources.getString(R.string.temp_measure_unit);
        }
    }

    public String formatPressure(float pressure) {
        int pressureInt = Math.round(pressure);
        if (getCurrentLocale() == Locale.US) {
            return resources.getString(R.string.weather_fragment_pressure) + " " + pressureInt +
                    " " + resources.getString(R.string.pressure_measure_unit);
        } else {
            return resources.getString(R.string.weather_fragment_pressure) + " " + Math.round(pressureInt * 0.750) +
                    " " + resources.getString(R.string.pressure_measure_unit);
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    public Locale getCurrentLocale() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return resources.getConfiguration().getLocales().get(0);
        } else {
            return resources.getConfiguration().locale;
        }
    }

    public int getIconResourceForWeatherId(int weatherId) {
        if (weatherId >= 200 && weatherId <= 232) {
            return R.drawable.ic_thunderstorm;
        } else if (weatherId >= 300 && weatherId <= 321) {
            return R.drawable.ic_rain;
        } else if (weatherId >= 500 && weatherId <= 504) {
            return R.drawable.ic_rain;
        } else if (weatherId == 511) {
            return R.drawable.ic_snow;
        } else if (weatherId >= 520 && weatherId <= 531) {
            return R.drawable.ic_rain;
        } else if (weatherId >= 600 && weatherId <= 622) {
            return R.drawable.ic_snow;
        } else if (weatherId >= 701 && weatherId <= 761) {
            return R.drawable.ic_fog;
        } else if (weatherId == 761 || weatherId == 781) {
            return R.drawable.ic_thunderstorm;
        } else if (weatherId == 800) {
            return R.drawable.ic_sun;
        } else if (weatherId == 801) {
            return R.drawable.ic_clouds;
        } else if (weatherId >= 802 && weatherId <= 804) {
            return R.drawable.ic_clouds;
        }
        return -1;
    }

    public int getColorResourceForWeatherId(int weatherId) {
        if (weatherId >= 200 && weatherId <= 232) {
            return R.color.color_rain;
        } else if (weatherId >= 300 && weatherId <= 321) {
            return R.color.color_rain;
        } else if (weatherId >= 500 && weatherId <= 504) {
            return R.color.color_rain;
        } else if (weatherId == 511) {
            return R.color.color_snow;
        } else if (weatherId >= 520 && weatherId <= 531) {
            return R.color.color_rain;
        } else if (weatherId >= 600 && weatherId <= 622) {
            return R.color.color_snow;
        } else if (weatherId >= 701 && weatherId <= 761) {
            return R.color.color_clouds;
        } else if (weatherId == 761 || weatherId == 781) {
            return R.color.color_rain;
        } else if (weatherId == 800) {
            return R.color.color_hot;
        } else if (weatherId == 801) {
            return R.color.color_clouds;
        } else if (weatherId >= 802 && weatherId <= 804) {
            return R.color.color_clouds;
        }
        return -1;
    }
}
