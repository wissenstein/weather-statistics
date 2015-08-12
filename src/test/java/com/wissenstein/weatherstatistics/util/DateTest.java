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
}
