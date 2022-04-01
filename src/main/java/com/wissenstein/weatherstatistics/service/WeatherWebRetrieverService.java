package com.wissenstein.weatherstatistics.service;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class WeatherWebRetrieverService {

    public static final String WEATHER_SERVICE_URL = "http://sinoptik.ua/"
            + "%D0%BF%D0%BE%D0%B3%D0%BE%D0%B4%D0%B0-"
            + "%D1%85%D0%B0%D1%80%D1%8C%D0%BA%D0%BE%D0%B2/";

    public Document getWeatherWebPage(final String dateString) throws IOException {
        final String url = WEATHER_SERVICE_URL + dateString;

        return Jsoup.connect(url).get();
    }
}
