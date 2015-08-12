package com.wissenstein.weatherstatistics.controllers;

import com.wissenstein.weatherstatistics.domain.DailyStatistics;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private MongoOperations mongo;

    @RequestMapping(method = RequestMethod.GET)
    public String admin(ModelMap model) {
        final List<DailyStatistics> statistics
                = mongo.findAll(DailyStatistics.class, "temperature");
        Collections.sort(statistics, new Comparator<DailyStatistics>() {

            @Override
            public int compare(DailyStatistics d1, DailyStatistics d2) {
                return d1.getDate().compareTo(d2.getDate());
            }
        });

        model.put("statistics", statistics);

        return "admin/admin";
    }

    @RequestMapping(value = "/add-day", method = RequestMethod.GET)
    public String addDay() {
        return "admin/add-day";
    }

    @RequestMapping(
            value = "/save-day",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void saveDay(
            @RequestBody DailyStatistics dailyStatistics) {

        mongo.save(dailyStatistics, "temperature");
    }
}
