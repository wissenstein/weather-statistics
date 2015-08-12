package com.wissenstein.weatherstatistics.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringsTest {

    @Test
    public void parsePositiveTemperature() {
        assertEquals(24, Strings.parseTemperature("+24°"));
        assertEquals(4, Strings.parseTemperature("+4°"));
    }

    @Test
    public void parseNegativeTemperature() {
        assertEquals(-17, Strings.parseTemperature("-17°"));
        assertEquals(-9, Strings.parseTemperature("-9°"));
    }

    @Test
    public void parseZero() {
        assertEquals(0, Strings.parseTemperature("0°"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseIllegalString() {
        Strings.parseTemperature("-17°!");
    }
}
