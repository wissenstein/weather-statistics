package com.wissenstein.weatherstatistics.service;

import com.wissenstein.weatherstatistics.domain.TemperatureByDate;
import com.wissenstein.weatherstatistics.util.Date;
import com.wissenstein.weatherstatistics.util.Strings;
import java.io.IOException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    @Autowired
    private WeatherWebRetrieverService weatherWebRetrieverService;

    public TemperatureByDate getTemperatureByDate(String dateString)
    throws IOException {
        final Document document
                = weatherWebRetrieverService.getWeatherWebPage(dateString);

        TemperatureByDate statistics = new TemperatureByDate();
        statistics.setDate(Date.parse(dateString));

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
