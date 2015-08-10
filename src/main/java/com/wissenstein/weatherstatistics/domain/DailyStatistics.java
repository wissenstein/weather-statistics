package com.wissenstein.weatherstatistics.domain;

import com.wissenstein.weatherstatistics.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class DailyStatistics {

    @Id
    private String id;

    private Date date;
    private int nightTemperature;
    private int morningTemperature;
    private int dayTemperature;
    private int eveningTemperature;

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


    public int getNightTemperature() {
        return nightTemperature;
    }

    public void setNightTemperature(int nightTemperature) {
        this.nightTemperature = nightTemperature;
    }
    public int getMorningTemperature() {
        return morningTemperature;
    }

    public void setMorningTemperature(int morningTemperature) {
        this.morningTemperature = morningTemperature;
    }

    public int getDayTemperature() {
        return dayTemperature;
    }

    public void setDayTemperature(int dayTemperature) {
        this.dayTemperature = dayTemperature;
    }

    public int getEveningTemperature() {
        return eveningTemperature;
    }

    public void setEveningTemperature(int eveningTemperature) {
        this.eveningTemperature = eveningTemperature;
    }

    @Override
    public String toString() {
        return "DailyStatistics{"
                + "id=" + id
                + ", date=" + date
                + ", nightTemperature=" + nightTemperature
                + ", morningTemperature=" + morningTemperature
                + ", dayTemperature=" + dayTemperature
                + ", eveningTemperature=" + eveningTemperature + '}';
    }
}
