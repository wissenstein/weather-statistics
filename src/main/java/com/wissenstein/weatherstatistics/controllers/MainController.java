package com.wissenstein.weatherstatistics.controllers;

import com.wissenstein.weatherstatistics.domain.TemperatureByDate;
import com.wissenstein.weatherstatistics.persistence.WeatherRepository;
import com.wissenstein.weatherstatistics.service.WeatherService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private WeatherService weatherService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model) {
        final List<TemperatureByDate> statistics
                = weatherRepository.findAllTemperatures();

        model.put("statistics", statistics);

        return "index";
    }

    @RequestMapping(value = "/weather-by-date", method=RequestMethod.GET)
    public String weatherByDate() {
        return "weather-by-date";
    }

    @Transactional
    @RequestMapping(
            value = "/weather-from-service/{date}",
            method = RequestMethod.GET)
    @ResponseBody
    public TemperatureByDate getWeatherForDate(
            @PathVariable("date") String dateString)
    throws IOException {
        TemperatureByDate temperatureByDate
                = weatherRepository.findTemperatureByDate(dateString);

        if (temperatureByDate == null) {
            temperatureByDate
                    = weatherService.getTemperatureByDate(dateString);

            weatherRepository.insertTemperature(temperatureByDate);
        }

        return temperatureByDate;
    }

    @RequestMapping(value = "/weather-by-period", method = RequestMethod.GET)
    public String weatherByPeriod() {
        return "weather-by-period";
    }

    @Transactional
    @RequestMapping(
            value = "/weather-from-service/from/{firstDate}/to/{lastDate}",
            method = RequestMethod.GET)
    @ResponseBody
    public List<TemperatureByDate> getWeatherForPeriod(
            @PathVariable("firstDate") String firstDateString,
            @PathVariable("lastDate") String lastDateString)
    throws IOException {
        Map<String, TemperatureByDate> temperatureMap
                = weatherRepository.findTemperatureByPeriod(
                        firstDateString, lastDateString);

        final DateTime firstDate = DateTime.parse(firstDateString);
        final DateTime lastDate = DateTime.parse(lastDateString);
        List<TemperatureByDate> temperatureByPeriod = new ArrayList<>();

        DateTime currentDate = firstDate;
        while (currentDate.isBefore(lastDate)
                || currentDate.isEqual(lastDate)) {
            
            final String currentDateString
                    = currentDate.toString("yyyy-MM-dd");
            if (temperatureMap.containsKey(currentDateString)) {
                temperatureByPeriod.add(temperatureMap.get(currentDateString));
            } else {
                final TemperatureByDate t = weatherService
                        .getTemperatureByDate(currentDateString);
                weatherRepository.insertTemperature(t);

                temperatureByPeriod.add(t);
            }

            currentDate = currentDate.plusDays(1);
        }

        return temperatureByPeriod;
    }
}