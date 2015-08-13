package com.wissenstein.weatherstatistics.controllers;

import com.wissenstein.weatherstatistics.domain.TemperatureByDate;
import com.wissenstein.weatherstatistics.persistence.WeatherRepository;
import com.wissenstein.weatherstatistics.service.WeatherService;
import java.io.IOException;
import java.util.List;
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
    private WeatherRepository weatherRepository;

    @Autowired
    private WeatherService weatherService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model) {
        final List<TemperatureByDate> statistics
                = mongo.findAll(TemperatureByDate.class, "temperature");

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
    public TemperatureByDate getWeatherFromService(
            @PathVariable("date") String dateString)
    throws IOException {
        TemperatureByDate temperatureByDate
                = weatherRepository.getTemperatureByDate(dateString);

        if (temperatureByDate == null) {
            temperatureByDate
                    = weatherService.getTemperatureByDate(dateString);

            weatherRepository.insertTemperature(temperatureByDate);
        }

        return temperatureByDate;
    }
}
