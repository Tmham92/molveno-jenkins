package com.capgemini.molveno.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class FormDateParser {

    public static Date convertDateStringToDate(String dateString) {
        String[] splitDate = dateString.split("-");
        StringBuilder sb = new StringBuilder();
        for (int i = splitDate.length-1; i >= 0; i--) {
            if (i != 0) {
                sb.append(splitDate[i] + "-");
            } else {
                sb.append(splitDate[i]);
            }
        }
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        Date date = new Date();

        try {
            date = format.parse(sb.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String convertStringToDateFormat(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

}
