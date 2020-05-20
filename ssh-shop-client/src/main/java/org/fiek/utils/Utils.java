package org.fiek.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Utils {

    public static String dateFormat = "yyyy-MM-d";

    public static String formatDate(LocalDate date) {
        if (date == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        return date.format(formatter);
    }
}
