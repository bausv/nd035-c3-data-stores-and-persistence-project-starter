package com.udacity.jdnd.course3.critter.mapper;

import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.model.Schedule;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ScheduleMapper {

    public Schedule dtoToEntity(ScheduleDTO scheduleDTO) {
        Schedule s = new Schedule();
        s.setActivities(scheduleDTO.getActivities());
        s.setDate(scheduleDTO.getDate());
        return s;
    }

    public ScheduleDTO entityToDTO(Schedule s) {
        ScheduleDTO dto = new ScheduleDTO();
        dto.setActivities(s.getActivities());
        dto.setDate(s.getDate());
        dto.setEmployeeIds(s.getEmployees().stream().map(Employee::getId).collect(Collectors.toList()));
        dto.setPetIds(s.getPets().stream().map(Pet::getId).collect(Collectors.toList()));
        return dto;
    }
}
