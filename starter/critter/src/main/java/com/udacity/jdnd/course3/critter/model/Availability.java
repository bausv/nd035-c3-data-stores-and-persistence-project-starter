package com.udacity.jdnd.course3.critter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.DayOfWeek;

@Entity
public class Availability extends AbstractBaseEntity {

    @Enumerated(EnumType.STRING)
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
