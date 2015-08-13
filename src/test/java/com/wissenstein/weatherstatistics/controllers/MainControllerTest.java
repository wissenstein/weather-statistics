package com.wissenstein.weatherstatistics.controllers;

import com.wissenstein.weatherstatistics.domain.TemperatureByDate;
import com.wissenstein.weatherstatistics.persistence.WeatherRepository;
import com.wissenstein.weatherstatistics.service.WeatherService;
import com.wissenstein.weatherstatistics.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MainControllerTest {

    @Mock
    private WeatherRepository weatherRepository;

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private final MainController controller = new MainController();

    private static final String SINGLE_DATE = "2015-08-02";
    private static final String FIRST_DATE = "2015-07-08";
    private static final String LAST_DATE = "2015-07-15";

    private TemperatureByDate expectedTemperatureByDate;
    private List<TemperatureByDate> expectedTemperatureByPeriod;

    @Before
    public void setUp() {
        expectedTemperatureByDate = new TemperatureByDate();
        expectedTemperatureByDate.setDate(Date.parse(SINGLE_DATE));
        expectedTemperatureByDate.setNight(16);
        expectedTemperatureByDate.setMorning(15);
        expectedTemperatureByDate.setMidday(24);
        expectedTemperatureByDate.setEvening(25);

        expectedTemperatureByPeriod = new LinkedList<>();

        final DateTime firstDate = DateTime.parse(FIRST_DATE);
        final DateTime lastDate = DateTime.parse(LAST_DATE);

        DateTime currentDate = firstDate;
        int atNight = 18;
        int inMorning = 17;
        int atMidday = 20;
        int inEvening = 19;
        while (currentDate.isBefore(lastDate)
                || currentDate.isEqual(lastDate)) {

            TemperatureByDate currentTemp = new TemperatureByDate();
            currentTemp.setDate(
                    Date.parse(currentDate.toString("yyyy-MM-dd")));
            currentTemp.setNight(atNight ++);
            currentTemp.setMorning(inMorning++);
            currentTemp.setMidday(atMidday++);
            currentTemp.setEvening(inEvening++);

            expectedTemperatureByPeriod.add(currentTemp);

            currentDate = currentDate.plusDays(1);
        }

        when(weatherRepository.findTemperatureByPeriod(FIRST_DATE, LAST_DATE))
                .thenReturn(expectedTemperatureByPeriod);
    }

    @Test
    public void getWeatherFromServiceWhenNotFoundInDatabase()
    throws Exception {
        when(weatherRepository.findTemperatureByDate(SINGLE_DATE))
                .thenReturn(null);
        when(weatherService.getTemperatureByDate(SINGLE_DATE))
                .thenReturn(expectedTemperatureByDate);

        controller.getWeatherForDate(SINGLE_DATE);

        verify(weatherRepository).insertTemperature(expectedTemperatureByDate);
    }

    @Test
    public void getWeatherFromServiceWhenFoundInDatabase() throws Exception {
        when(weatherRepository.findTemperatureByDate(SINGLE_DATE))
                .thenReturn(expectedTemperatureByDate);

        final TemperatureByDate temperature
                = controller.getWeatherForDate(SINGLE_DATE);

        assertEquals(expectedTemperatureByDate, temperature);
        verify(weatherService, never())
                .getTemperatureByDate(anyString());
        verify(weatherRepository, never())
                .insertTemperature(any(TemperatureByDate.class));
    }

    @Test
    public void getWeatherForPeriodWhenFoundInDatabase() throws Exception {
        final List<TemperatureByDate> weatherForPeriod
                = controller.getWeatherForPeriod(FIRST_DATE, LAST_DATE);

        assertEquals(expectedTemperatureByPeriod, weatherForPeriod);
    }
}
