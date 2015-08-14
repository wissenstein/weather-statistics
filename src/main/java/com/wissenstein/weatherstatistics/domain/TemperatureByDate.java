package com.wissenstein.weatherstatistics.domain;

import com.wissenstein.weatherstatistics.util.Date;
import java.util.Objects;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.date);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (getClass() != obj.getClass()) {
            return false;
        } else {
            final TemperatureByDate other = (TemperatureByDate) obj;

            if (this.date == null) {
                if (other.date == null) {
                    throw new IllegalArgumentException(
                            "Attempt to compare two " + getClass()
                            + " instances with null ID.");
                } else {
                    return false;
                }
            } else {
                return this.date.equals(other.date);
            }
        }
    }
}
