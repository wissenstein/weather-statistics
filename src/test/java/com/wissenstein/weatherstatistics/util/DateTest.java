package com.wissenstein.weatherstatistics.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class DateTest {

    @Test
    public void toStringInDifferentCases() {
        Date date = new Date(2015, 11, 16);
        assertEquals("2015-11-16", date.toString());

        date = new Date(2015, 6, 2);
        assertEquals("2015-06-02", date.toString());
    }

    @Test
    public void compareWhenYearsDiffer() {
        final Date earlier = new Date(2014, 7, 7);
        final Date later = new Date(2015, 7, 7);

        assertEquals(-1, earlier.compareTo(later));
        assertEquals(1, later.compareTo(earlier));
    }

    @Test
    public void compareWhenMonthsDiffer() {
        final Date earlier = new Date(2015, 8, 14);
        final Date later = new Date(2015, 9, 14);

        assertEquals(-1, earlier.compareTo(later));
        assertEquals(1, later.compareTo(earlier));
    }

    @Test
    public void compareWhenDaysDiffer() {
        final Date earlier = new Date(2015, 8, 9);
        final Date later = new Date(2015, 8, 12);

        assertEquals(-1, earlier.compareTo(later));
        assertEquals(1, later.compareTo(earlier));
    }

    @Test
    public void compareWhenNoDifference() {
        final Date date1 = new Date(2015, 8, 8);
        final Date date2 = new Date(2015, 8, 8);

        assertEquals(0, date1.compareTo(date2));
    }

    @Test
    public void parse() {
        Date expectedDate = new Date(2015, 11, 8);
        Date date = Date.parse("2015-11-08");

        assertEquals(expectedDate, date);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseIllegalString() {
        Date.parse("1234567890");
    }
}
