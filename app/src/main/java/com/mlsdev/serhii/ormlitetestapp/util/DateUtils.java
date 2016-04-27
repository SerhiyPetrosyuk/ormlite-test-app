package com.mlsdev.serhii.ormlitetestapp.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {

    public static String getFormattedDate(long time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return dateFormat.format(calendar.getTime());
    }

    public static long getCurrentDateTime() {
        return Calendar.getInstance().getTimeInMillis();
    }

}
