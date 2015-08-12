package com.wissenstein.weatherstatistics.controllers;

import com.wissenstein.weatherstatistics.domain.DailyStatistics;
import com.wissenstein.weatherstatistics.service.WeatherService;
import com.wissenstein.weatherstatistics.util.Date;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest {

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private final IndexController controller = new IndexController();

    private static final String REQUESTED_DATE = "2015-08-02";
    private static final String REQUEST_URL
            = IndexController.WEATHER_SERVICE_URL + REQUESTED_DATE;
    private static String HTML_TEXT;
    private static Document HTML_DOCUMENT;

    @BeforeClass
    public static void setUpClass() throws Exception {
        final StringBuilder weatherResponse = new StringBuilder();

        try (final InputStream weatherResponseStream = IndexControllerTest
                .class
                .getResourceAsStream("/weather-service-response.html")) {

            final BufferedReader weatherResponseReader = new BufferedReader(
                    new InputStreamReader(weatherResponseStream, "UTF-8"));

            String inputLine;
            while ((inputLine = weatherResponseReader.readLine()) != null) {
                weatherResponse.append(inputLine).append("\n");
            }
        }

        HTML_TEXT = weatherResponse.toString();
        HTML_DOCUMENT = Jsoup.parse(HTML_TEXT);
    }

    @Before
    public void setUp() throws Exception {
        when(weatherService.getHtmlDocument(REQUEST_URL))
                .thenReturn(HTML_DOCUMENT);
    }

    @Test
    public void getWeatherFromService() throws Exception {
        final DailyStatistics temperatureByDate
                = controller.getWeatherFromService(REQUESTED_DATE);

        assertEquals(16, temperatureByDate.getNightTemperature());
        assertEquals(15, temperatureByDate.getMorningTemperature());
        assertEquals(24, temperatureByDate.getDayTemperature());
        assertEquals(25, temperatureByDate.getEveningTemperature());
        assertEquals(Date.parse(REQUESTED_DATE), temperatureByDate.getDate());
    }
}
