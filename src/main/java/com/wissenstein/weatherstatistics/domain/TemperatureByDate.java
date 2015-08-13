package com.wissenstein.weatherstatistics.domain;

import com.wissenstein.weatherstatistics.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TemperatureByDate {

    @Id
    private String id;

    private Date date;
    private int night;
    private int morning;
    private int midday;
    private int evening;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public int getNight() {
        return night;
    }

    public void setNight(int night) {
        this.night = night;
    }
    public int getMorning() {
        return morning;
    }

    public void setMorning(int morning) {
        this.morning = morning;
    }

    public int getMidday() {
        return midday;
    }

    public void setMidday(int midday) {
        this.midday = midday;
    }

    public int getEvening() {
        return evening;
    }

    public void setEvening(int evening) {
        this.evening = evening;
    }

    @Override
    public String toString() {
        return "DailyStatistics{"
                + "id=" + id
                + ", date=" + date
                + ", nightTemperature=" + night
                + ", morningTemperature=" + morning
                + ", dayTemperature=" + midday
                + ", eveningTemperature=" + evening + '}';
    }
}
