package com.wissenstein.weatherstatistics.persistence;

import com.wissenstein.weatherstatistics.domain.TemperatureByDate;
import com.wissenstein.weatherstatistics.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

@Repository
public class WeatherRepository {

    @Autowired
    private MongoOperations mongo;

    public TemperatureByDate getTemperatureByDate(String date) {
        final List<TemperatureByDate> statistics
                = mongo.findAll(TemperatureByDate.class, "temperature");

        Date requestedDate = Date.parse(date);
        TemperatureByDate temperatureByDate = null;
        for (TemperatureByDate t : statistics) {
            if (t.getDate().equals(requestedDate)) {
                temperatureByDate = t;
            }
        }

        return temperatureByDate;
    }

    public void insertTemperature(TemperatureByDate temperature) {
        mongo.insert(temperature, "temperature");
    }
}
