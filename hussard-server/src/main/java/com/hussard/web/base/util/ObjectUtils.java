package com.hussard.web.base.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Matthew on 2015-01-22.
 */
public class ObjectUtils {

    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyyMMdd");
    private static final SimpleDateFormat DATETIME_FORMATTER = new SimpleDateFormat("yyyyMMddHHmm");

    private ObjectUtils() {

    }

    public static String hashcodeByCurrentDate() {
        String current = DATE_FORMATTER.format(new Date());

        return String.format("%08x", current.hashCode());
    }

    public static String hashcodeByCurrentHour() {
        String current = DATETIME_FORMATTER.format(new Date());

        return String.format("%08x", current.hashCode());
    }

    public static String concatFilenames(List<String> folders) {
        StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < folders.size(); i++) {
            buffer.append(folders.get(i));
            if (i < (folders.size() - 1)) {
                buffer.append(File.separator);
            }
        }

        return buffer.toString();
    }
}
