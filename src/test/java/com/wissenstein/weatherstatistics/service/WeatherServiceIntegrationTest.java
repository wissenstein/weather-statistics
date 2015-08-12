package com.wissenstein.weatherstatistics.service;

import com.wissenstein.weatherstatistics.controllers.IndexController;
import org.jsoup.nodes.Document;

import static org.junit.Assert.*;

public class WeatherServiceIntegrationTest {

    public static void main(String[] args) throws Exception {
        WeatherServiceIntegrationTest weatherService = new WeatherServiceIntegrationTest();

        weatherService.run();
    }

    public void run() throws Exception {
        WeatherService service = new WeatherService();
        Document document = service.getHtmlDocument(
                IndexController.WEATHER_SERVICE_URL + "2015-08-01");

        try {
            assertNotNull(
                    "Expected that document is not null, actually it is null",
                    document);
            System.out.println(">>>> Test passed!");
        } catch (AssertionError e) {
            System.err.println(">>>> Test failed. " + e.getMessage());
        }
    }
}
