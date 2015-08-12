package com.wissenstein.weatherstatistics.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Strings {

    public static final String TEMPERATURE_PATTERN = "^[\\+\\-]?\\d{1,2}°$";

    public static int parseTemperature(String tempString) {
        Pattern tempPattern = Pattern.compile(TEMPERATURE_PATTERN);
        Matcher tempMatcher = tempPattern.matcher(tempString);
        if (tempMatcher.find()) {
            final int finalIndex = tempString.length() - 1;

            return Integer.parseInt(tempString.substring(0, finalIndex));
        } else {
            throw new IllegalArgumentException(
                    "Unable to parse string [" + tempString
                            + "] as a temperature value");
        }
    }
}
