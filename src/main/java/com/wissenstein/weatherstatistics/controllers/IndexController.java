package com.wissenstein.weatherstatistics.controllers;

import com.wissenstein.weatherstatistics.domain.DailyStatistics;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @Autowired
    private MongoOperations mongo;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.put("testMessage", "Spring MVC works.");

        final List<DailyStatistics> statistics
                = mongo.findAll(DailyStatistics.class, "temperature");

        model.put("statistics", statistics);

        return "index";
    }
}
