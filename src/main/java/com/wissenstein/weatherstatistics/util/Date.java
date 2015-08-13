package com.wissenstein.weatherstatistics.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Simplistic custom class representing date
 * which is not bound to any time zone.
 * <p>NOTE: this class is not protected
 * against wrong month and day numbers yet!</p>
 *
 * @author Oleksij Lupandin
 */
// TODO add means to hold month and day numbers in legal limits
public class Date implements Comparable<Date> {

    private int year;
    private int month;
    private int day;

    public Date() {
    }

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Parses string in format "yyyy-MM-dd" to a Date object.
     * Does not recognize illegal month and day numbers yet.
     *
     * @param dateString
     * @return
     */
    // TODO Add means to recognize legal limits for month and day numbers.
    public static Date parse(String dateString) {
        Pattern datePattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
        Matcher matcher = datePattern.matcher(dateString);
        if (matcher.find()) {
        final int year = Integer.parseInt(dateString.substring(0, 4));
        final int month = Integer.parseInt(dateString.substring(5, 7));
        final int day = Integer.parseInt(dateString.substring(8, 10));

        return new Date(year, month, day);
        } else {
            throw new IllegalArgumentException("Expression \"" + dateString
                    + "\" does not correspond to format \"yyyy-MM-dd\"");
        }
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public int compareTo(Date other) {
        if (this.year < other.year) {
            return -1;
        } else if (this.year > other.year) {
            return 1;
        } else if (this.month < other.month) {
            return -1;
        } else if (this.month > other.month) {
            return 1;
        } else if (this.day > other.day) {
            return 1;
        } else if (this.day < other.day) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return String.format("%04d-%02d-%02d", year, month, day);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.year;
        hash = 89 * hash + this.month;
        hash = 89 * hash + this.day;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (getClass() != obj.getClass()) {
            return false;
        } else {
            final Date other = (Date) obj;

            return (this.year == other.year)
                    && (this.month == other.month)
                    && (this.day == other.day);
        }
    }
}
