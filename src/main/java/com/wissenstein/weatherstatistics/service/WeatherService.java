package com.wissenstein.weatherstatistics.service;

import com.wissenstein.weatherstatistics.domain.TemperatureByDate;
import com.wissenstein.weatherstatistics.util.Strings;
import java.io.IOException;
import java.time.LocalDate;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    private final WeatherWebRetrieverService weatherWebRetrieverService;

    @Autowired
    public WeatherService(final WeatherWebRetrieverService weatherWebRetrieverService) {
        this.weatherWebRetrieverService = weatherWebRetrieverService;
    }

    public TemperatureByDate getTemperatureByDate(final String dateString)
    throws IOException {
        final Document document
                = weatherWebRetrieverService.getWeatherWebPage(dateString);

        final TemperatureByDate statistics = new TemperatureByDate();
        statistics.setDate(LocalDate.parse(dateString));

        for (final Element temperature
                : document.getElementsByClass("temperature")) {

            for (final Element td : temperature.getElementsByTag("td")) {
                if (td.hasClass("p1")) {
                    statistics.setNight(
                            Strings.parseTemperature(td.text()));
                } else if (td.hasClass("p3")) {
                    statistics.setMorning(
                            Strings.parseTemperature(td.text()));
                } else if (td.hasClass("p5")) {
                    statistics.setMidday(
                            Strings.parseTemperature(td.text()));
                } else if (td.hasClass("p7")) {
                    statistics.setEvening(
                            Strings.parseTemperature(td.text()));
                }
            }
        }

        return statistics;
    }
}
