package com.wissenstein.weatherstatistics.controllers;

import com.wissenstein.weatherstatistics.domain.DailyStatistics;
import com.wissenstein.weatherstatistics.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private MongoOperations mongo;

    @RequestMapping
    public String admin() {
        return "admin";
    }

    @RequestMapping(value = "/save-day", method = RequestMethod.POST)
    @ResponseBody
    public String saveDay(HttpServletRequest request) {
        System.out.println(">>>> " + request);

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
