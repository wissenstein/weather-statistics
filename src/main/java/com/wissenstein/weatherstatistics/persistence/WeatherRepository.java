package com.wissenstein.weatherstatistics.persistence;

import com.wissenstein.weatherstatistics.domain.DailyStatistics;
import com.wissenstein.weatherstatistics.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

@Repository
public class WeatherRepository {

    @Autowired
    private MongoOperations mongo;

    public DailyStatistics getTemperatureByDate(String date) {
        final List<DailyStatistics> statistics
                = mongo.findAll(DailyStatistics.class, "temperature");

        Date requestedDate = Date.parse(date);
        DailyStatistics temperatureByDate = null;
        for (DailyStatistics t : statistics) {
            if (t.getDate().equals(requestedDate)) {
                temperatureByDate = t;
            }
        }

        return temperatureByDate;
    }

    public void insertTemperature(DailyStatistics temperature) {
        mongo.insert(temperature, "temperature");
    }
}
