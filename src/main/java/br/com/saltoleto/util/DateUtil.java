package br.com.saltoleto.util;

import org.joda.time.DateTime;

import java.util.Date;

public abstract class DateUtil {
    private DateUtil() {}

    public static Date setHoursMinutesSeconds(Date dateParameter, Integer hours, Integer minutes, Integer seconds) {
        return new DateTime(dateParameter)
                .withHourOfDay(hours)
                .withMinuteOfHour(minutes)
                .withSecondOfMinute(seconds).toDate();
    }
}
