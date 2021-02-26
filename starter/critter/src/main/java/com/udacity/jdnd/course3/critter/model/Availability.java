package com.udacity.jdnd.course3.critter.model;

import javax.persistence.Entity;
import java.time.DayOfWeek;

@Entity
public class Availability extends AbstractBaseEntity {

    private DayOfWeek dayOfWeek;

    public Availability(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Availability() {
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
