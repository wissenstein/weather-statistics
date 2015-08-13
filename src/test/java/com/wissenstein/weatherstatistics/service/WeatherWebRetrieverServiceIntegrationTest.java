package com.wissenstein.weatherstatistics.service;

import org.jsoup.nodes.Document;

import static org.junit.Assert.*;

public class WeatherWebRetrieverServiceIntegrationTest {

    public static void main(String[] args) throws Exception {
        WeatherWebRetrieverServiceIntegrationTest test
                = new WeatherWebRetrieverServiceIntegrationTest();

        test.run();
    }

    public void run() throws Exception {
        WeatherWebRetrieverService service = new WeatherWebRetrieverService();
        Document document = service.getWeatherWebPage("2015-08-01");

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
