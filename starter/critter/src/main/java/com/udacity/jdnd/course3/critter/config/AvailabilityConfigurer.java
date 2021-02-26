package com.udacity.jdnd.course3.critter.config;

import com.udacity.jdnd.course3.critter.model.Availability;
import com.udacity.jdnd.course3.critter.model.AvailabilityRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.DayOfWeek;

@Service
public class AvailabilityConfigurer {

    private AvailabilityRepository availabilityRepository;

    public AvailabilityConfigurer(AvailabilityRepository availabilityRepository) {
        this.availabilityRepository = availabilityRepository;
    }

    @PostConstruct
    public void initAvailability() {
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            availabilityRepository.findByDayOfWeek(dayOfWeek).orElseGet(() -> {
               return availabilityRepository.save(new Availability(dayOfWeek));
            });
        }
    }
}
