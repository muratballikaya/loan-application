package com.mb.loan.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static Date calculateFirstDayOfNextMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }
}
