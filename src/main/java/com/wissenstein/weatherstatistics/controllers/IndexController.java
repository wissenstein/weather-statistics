package com.wissenstein.weatherstatistics.controllers;

import com.wissenstein.weatherstatistics.domain.DailyStatistics;
import com.wissenstein.weatherstatistics.service.WeatherService;
import com.wissenstein.weatherstatistics.util.Date;
import com.wissenstein.weatherstatistics.util.Strings;
import java.io.IOException;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @Autowired
    private MongoOperations mongo;

    @Autowired
    private WeatherService weatherService;

    public static final String WEATHER_SERVICE_URL = "http://sinoptik.ua/"
            + "%D0%BF%D0%BE%D0%B3%D0%BE%D0%B4%D0%B0-"
            + "%D1%85%D0%B0%D1%80%D1%8C%D0%BA%D0%BE%D0%B2/";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.put("testMessage", "Spring MVC works.");

        final List<DailyStatistics> statistics
                = mongo.findAll(DailyStatistics.class, "temperature");

        model.put("statistics", statistics);

        return "index";
    }

    @RequestMapping(value = "/weather-by-date", method=RequestMethod.GET)
    public String weatherByDate() {
        return "weather-by-date";
    }

    @RequestMapping(
            value = "/weather-from-service/{date}",
            method = RequestMethod.GET)
    @ResponseBody
    public DailyStatistics getWeatherFromService(
            @PathVariable("date") String dateString)
    throws IOException {
        final String requestUrlString = WEATHER_SERVICE_URL + dateString;
        final Document document
                = weatherService.getHtmlDocument(requestUrlString);

        DailyStatistics statistics = new DailyStatistics();
        statistics.setDate(Date.parse(dateString));

        for (final Element temperature
                : document.getElementsByClass("temperature")) {

            for (final Element td : temperature.getElementsByTag("td")) {
                if (td.hasClass("p1")) {
                    statistics.setNightTemperature(
                            Strings.parseTemperature(td.text()));
                } else if (td.hasClass("p3")) {
                    statistics.setMorningTemperature(
                            Strings.parseTemperature(td.text()));
                } else if (td.hasClass("p5")) {
                    statistics.setDayTemperature(
                            Strings.parseTemperature(td.text()));
                } else if (td.hasClass("p7")) {
                    statistics.setEveningTemperature(
                            Strings.parseTemperature(td.text()));
                }
            }
        }

        return statistics;
    }
}
