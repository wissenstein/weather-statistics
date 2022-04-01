package com.wissenstein.weatherstatistics.persistence;

import com.wissenstein.weatherstatistics.domain.TemperatureByDate;

import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

// TODO Implement selecting statistics by period on database side
@Repository
public class WeatherRepository {

    private final MongoOperations mongo;

    private static final String COL_TEMPERATURE = "temperature";

    @Autowired
    public WeatherRepository(final MongoOperations mongo) {
        this.mongo = mongo;
    }

    public List<TemperatureByDate> findAllTemperatures() {
        final Query query = new Query()
                .with(Sort.by(Sort.Direction.ASC,
                        "date.year", "date.month", "date.day"));

        return mongo.find(query, TemperatureByDate.class, COL_TEMPERATURE);
    }

    public TemperatureByDate findTemperatureByDate(final String date) {
        final Query query = new Query();
        final LocalDate requestedDate = LocalDate.parse(date);

        query.addCriteria(Criteria
                .where("date.year").is(requestedDate.getYear())
                .and("date.month").is(requestedDate.getMonth())
                .and("date.day").is(requestedDate.getDayOfMonth()));

        return mongo.findOne(query, TemperatureByDate.class, COL_TEMPERATURE);
    }

    public void insertTemperature(final TemperatureByDate temperature) {
        mongo.insert(temperature, COL_TEMPERATURE);
    }

    public Map<String, TemperatureByDate> findTemperatureByPeriod(
            final String firstDateString, final String lastDateString) {

        final Query query = new Query()
                .with(Sort.by(Sort.Direction.ASC,
                        "date.year", "date.month", "date.day"));

        final LocalDate firstDate = LocalDate.parse(firstDateString);
        final LocalDate lastDate = LocalDate.parse(lastDateString);

        final List<TemperatureByDate> temperature
                = mongo.find(query, TemperatureByDate.class, COL_TEMPERATURE);

        final Map<String, TemperatureByDate> temperatureByPeriod
                = new HashMap<>(temperature.size());

        final Iterator<TemperatureByDate> tempIterator
                = temperature.iterator();
        while (tempIterator.hasNext()) {
            final TemperatureByDate t = tempIterator.next();
            final LocalDate date = t.getDate();
            if (!date.isBefore(firstDate)) {
                if (!date.isAfter(lastDate)) {
                    temperatureByPeriod.put(date.toString(), t);
                }
                break;
            }
        }
        while (tempIterator.hasNext()) {
            final TemperatureByDate t = tempIterator.next();
            final LocalDate date = t.getDate();
            if (!date.isAfter(lastDate)) {
                temperatureByPeriod.put(date.toString(), t);
            } else {
                break;
            }
        }

        return temperatureByPeriod;
    }
}
