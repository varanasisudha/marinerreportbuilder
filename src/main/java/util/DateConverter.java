package util;

import com.opencsv.bean.AbstractBeanField;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static util.Constants.DATE_FORMAT;

public class DateConverter extends AbstractBeanField {
    @Override
    protected Object convert(String s) {
        ZonedDateTime date = null;
        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
            date = ZonedDateTime.parse(s, formatter);
        } catch (DateTimeParseException ex) {
            System.out.print(ex.getMessage());
        }
        return date;

    }
}