package com.wissenstein.weatherstatistics.controllers;

import com.wissenstein.weatherstatistics.domain.TemperatureByDate;
import com.wissenstein.weatherstatistics.persistence.WeatherRepository;
import com.wissenstein.weatherstatistics.service.WeatherService;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * A simple site to display air temperature in Kharkiv at midnight,
 * in the morning, at midday and in the evening on a day chosen by a user.
 */
@Controller
public class MainController {

    private final WeatherRepository weatherRepository;

    private final WeatherService weatherService;

    @Autowired
    public MainController(final WeatherRepository weatherRepository, final WeatherService weatherService) {
        this.weatherRepository = weatherRepository;
        this.weatherService = weatherService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(final ModelMap model) {
        final List<TemperatureByDate> statistics
                = weatherRepository.findAllTemperatures();

        model.put("statistics", statistics);

        return "index";
    }

    @RequestMapping(value = "/weather-by-date", method=RequestMethod.GET)
    public String weatherByDate() {
        return "weather-by-date";
    }

    // TODO Add date validation
    @Transactional
    @RequestMapping(
            value = "/weather-from-service/{date}",
            method = RequestMethod.GET)
    @ResponseBody
    public TemperatureByDate getWeatherForDate(
            @PathVariable("date") final String dateString)
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

    // TODO Add date validation
    @Transactional
    @RequestMapping(
            value = "/weather-from-service/from/{firstDate}/to/{lastDate}",
            method = RequestMethod.GET)
    @ResponseBody
    public List<TemperatureByDate> getWeatherForPeriod(
            @PathVariable("firstDate") final String firstDateString,
            @PathVariable("lastDate") final String lastDateString)
    throws IOException {
        final Map<String, TemperatureByDate> temperatureMap
                = weatherRepository.findTemperatureByPeriod(
                        firstDateString, lastDateString);

        final LocalDate firstDate = LocalDate.parse(firstDateString);
        final LocalDate lastDate = LocalDate.parse(lastDateString);
        final List<TemperatureByDate> temperatureByPeriod = new ArrayList<>();

        LocalDate currentDate = firstDate;
        while (currentDate.isBefore(lastDate)
                || currentDate.isEqual(lastDate)) {

            final String currentDateString
                    = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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
