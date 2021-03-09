package com.udacity.jdnd.course3.critter.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByEmployees_Id(long employeeId);

    List<Schedule> findByPets_id(long petId);
}
