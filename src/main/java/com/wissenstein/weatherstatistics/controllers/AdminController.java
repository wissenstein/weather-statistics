package com.wissenstein.weatherstatistics.controllers;

import com.wissenstein.weatherstatistics.domain.DailyStatistics;
import com.wissenstein.weatherstatistics.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private MongoOperations mongo;

    @RequestMapping
    public String admin() {
        final Date testDate = new Date();
        testDate.setYear(2015);
        testDate.setMonth(8);
        testDate.setDay(10);

        final DailyStatistics statistics = new DailyStatistics();
        statistics.setDate(testDate);
        statistics.setMorningTemperature(20);
        statistics.setDayTemperature(32);
        statistics.setEveningTemperature(33);
        statistics.setNightTemperature(24);

        mongo.save(statistics, "temperature");

        return "admin";
    }
}
