package com.wissenstein.weatherstatistics.persistence;

import com.wissenstein.weatherstatistics.domain.TemperatureByDate;
import com.wissenstein.weatherstatistics.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class WeatherRepository {

    @Autowired
    private MongoOperations mongo;

    private static final String COL_TEMPERATURE = "temperature";

    public List<TemperatureByDate> findAllTemperatures() {
        Query query = new Query()
                .with(new Sort(Sort.Direction.ASC,
                        "date.year", "date.month", "date.day"));

        return mongo.find(query, TemperatureByDate.class, COL_TEMPERATURE);
    }

    public TemperatureByDate findTemperatureByDate(String date) {
        Query query = new Query();
        Date requestedDate = Date.parse(date);

        query.addCriteria(Criteria
                .where("date.year").is(requestedDate.getYear())
                .and("date.month").is(requestedDate.getMonth())
                .and("date.day").is(requestedDate.getDay()));

        TemperatureByDate temperatureByDate = mongo
                .findOne(query, TemperatureByDate.class, COL_TEMPERATURE);

        return temperatureByDate;
    }

    public void insertTemperature(TemperatureByDate temperature) {
        mongo.insert(temperature, COL_TEMPERATURE);
    }

    public List<TemperatureByDate> findTemperatureByPeriod(
            String firstDateString, String lastDateString) {

        Query query = new Query()
                .with(new Sort(Sort.Direction.ASC,
                        "date.year", "date.month", "date.day"));

        final List<TemperatureByDate> temperatureByPeriod
                = mongo.find(query, TemperatureByDate.class, COL_TEMPERATURE);

        return temperatureByPeriod;
    }
}
