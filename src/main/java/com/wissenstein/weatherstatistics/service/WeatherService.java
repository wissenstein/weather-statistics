package com.wissenstein.weatherstatistics.service;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    public Document getHtmlDocument(String url) throws IOException {
        return Jsoup.connect(url).get();
    }
}
