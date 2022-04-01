package com.wissenstein.weatherstatistics.service;

import com.wissenstein.weatherstatistics.controllers.MainControllerTest;
import com.wissenstein.weatherstatistics.domain.TemperatureByDate;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WeatherServiceTest {

    @Mock
    private WeatherWebRetrieverService weatherWebRetrieverService;

    @InjectMocks
    private WeatherService service;

    private static final String REQUESTED_DATE = "2015-08-02";
    private static final String REQUEST_URL
            = WeatherWebRetrieverService.WEATHER_SERVICE_URL + REQUESTED_DATE;
    private static Document HTML_DOCUMENT;

    @BeforeClass
    public static void setUpClass() throws Exception {
        final StringBuilder weatherResponse = new StringBuilder();

        try (final InputStream weatherResponseStream = MainControllerTest
                .class
                .getResourceAsStream("/weather-service-response.html")) {

            final BufferedReader weatherResponseReader = new BufferedReader(
                    new InputStreamReader(weatherResponseStream, StandardCharsets.UTF_8));

            String inputLine;
            while ((inputLine = weatherResponseReader.readLine()) != null) {
                weatherResponse.append(inputLine).append("\n");
            }
        }

        final String htmlText = weatherResponse.toString();
        HTML_DOCUMENT = Jsoup.parse(htmlText);
    }

    @Before
    public void setUp() throws Exception {
        when(weatherWebRetrieverService.getWeatherWebPage(REQUESTED_DATE))
                .thenReturn(HTML_DOCUMENT);
    }

    @Test
    public void getTemperatureByDate() throws Exception {
        final TemperatureByDate temperatureByDate
                = service.getTemperatureByDate(REQUESTED_DATE);

        assertEquals(16, temperatureByDate.getNight());
        assertEquals(15, temperatureByDate.getMorning());
        assertEquals(24, temperatureByDate.getMidday());
        assertEquals(25, temperatureByDate.getEvening());
        assertEquals(LocalDate.parse(REQUESTED_DATE), temperatureByDate.getDate());
    }
}
