package ru.javawebinar.topjava.util;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DatesUtils {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("YYYY-mm-dd hh:ss");
    public static String format(LocalDateTime localDateTime){
        return localDateTime.format(FORMATTER);
    }
}
