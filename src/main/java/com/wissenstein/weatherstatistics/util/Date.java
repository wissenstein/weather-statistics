package com.wissenstein.weatherstatistics.util;

public class Date {

    private int year;
    private int month;
    private int day;

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
    public String toString() {
        return "Date{" + year + "-" + month + "-" + day + '}';
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
