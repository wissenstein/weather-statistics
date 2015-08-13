package com.wissenstein.weatherstatistics.controllers;

import com.wissenstein.weatherstatistics.domain.TemperatureByDate;
import com.wissenstein.weatherstatistics.persistence.WeatherRepository;
import com.wissenstein.weatherstatistics.service.WeatherService;
import com.wissenstein.weatherstatistics.util.Date;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest {

    @Mock
    private WeatherRepository weatherRepository;

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private final IndexController controller = new IndexController();

    private static final String REQUESTED_DATE = "2015-08-02";

    private TemperatureByDate expectedTemperatureByDate;

    @Before
    public void setUp() {
        expectedTemperatureByDate = new TemperatureByDate();
        expectedTemperatureByDate.setDate(Date.parse(REQUESTED_DATE));
        expectedTemperatureByDate.setNight(16);
        expectedTemperatureByDate.setMorning(15);
        expectedTemperatureByDate.setMidday(24);
        expectedTemperatureByDate.setEvening(25);
    }

    @Test
    public void getWeatherFromServiceWhenNotFoundInDatabase()
    throws Exception {
        when(weatherRepository.getTemperatureByDate(REQUESTED_DATE))
                .thenReturn(null);
        when(weatherService.getTemperatureByDate(REQUESTED_DATE))
                .thenReturn(expectedTemperatureByDate);

        controller.getWeatherFromService(REQUESTED_DATE);

        verify(weatherRepository).insertTemperature(expectedTemperatureByDate);
    }

    @Test
    public void getWeatherFromServiceWhenFoundInDatabase() throws Exception {
        when(weatherRepository.getTemperatureByDate(REQUESTED_DATE))
                .thenReturn(expectedTemperatureByDate);

        final TemperatureByDate temperature
                = controller.getWeatherFromService(REQUESTED_DATE);

        assertEquals(expectedTemperatureByDate, temperature);
        verify(weatherService, never())
                .getTemperatureByDate(anyString());
        verify(weatherRepository, never())
                .insertTemperature(any(TemperatureByDate.class));
    }

    @Test
    public void getTemperatureByPeriod() {
//        fail("Implement the test and the method.");
    }
}
