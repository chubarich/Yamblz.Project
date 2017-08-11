package ru.karapetiandav.yamblzproject.utils;


import android.content.res.Resources;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import ru.karapetiandav.yamblzproject.R;

public class TimeUtils {

    private Resources resources;
    private LocalDate localDate;

    public TimeUtils(Resources resources, LocalDate localDate) {
        this.resources = resources;
        this.localDate = localDate;
    }

    public String formatDate(long dateInSeconds) {
        DateTime dateTime = new DateTime(dateInSeconds * 1000L);
        if (dateTime.toLocalDate().equals(localDate)) {
            return resources.getString(R.string.today);
        } else if (dateTime.getYear() == localDate.getYear() &&
                dateTime.getMonthOfYear() == localDate.getMonthOfYear() &&
                dateTime.getDayOfMonth() - localDate.getDayOfMonth() == 1) {
            return resources.getString(R.string.tomorrow);
        } else {
            DateTimeFormatter frmt =
                    DateTimeFormat.forPattern(resources.getString(R.string.format_day_of_week));
            String dayOfWeek = frmt.print(dateTime);
            return upperCaseDayOfWeek(dayOfWeek);
        }
    }

    private String upperCaseDayOfWeek(String dayOfWeek) {
        dayOfWeek = dayOfWeek.toLowerCase();
        return dayOfWeek.substring(0, 1).toUpperCase() + dayOfWeek.substring(1);
    }
}
