package com.wissenstein.weatherstatistics.controllers;

import com.wissenstein.weatherstatistics.domain.DailyStatistics;
import com.wissenstein.weatherstatistics.persistence.WeatherRepository;
import com.wissenstein.weatherstatistics.service.WeatherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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

    @Test
    public void getWeatherFromServiceWhenNotFoundInDatabase()
    throws Exception {
        controller.getWeatherFromService(REQUESTED_DATE);

        verify(weatherRepository).getTemperatureByDate(REQUESTED_DATE);
        verify(weatherService).getTemperatureByDate(REQUESTED_DATE);
        verify(weatherRepository).insertTemperature(any(DailyStatistics.class));
    }
}
