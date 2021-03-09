package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.mapper.ScheduleMapper;
import com.udacity.jdnd.course3.critter.model.Schedule;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private ScheduleService service;
    private ScheduleMapper mapper;

    public ScheduleController(ScheduleService service, ScheduleMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        return mapper.entityToDTO(service.createSchedule(mapper.dtoToEntity(scheduleDTO)));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return service.getAllSchedules().stream().map(s -> mapper.entityToDTO(s)).collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return service.getScheduleForPet(petId).stream().map(s -> mapper.entityToDTO(s)).collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return service.getScheduleForEmployee(employeeId).stream().map(s -> mapper.entityToDTO(s)).collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> scheduleForCustomer = service.getScheduleForCustomer(customerId);
        return scheduleForCustomer.stream().map(s -> mapper.entityToDTO(s)).collect(Collectors.toList());
    }
}
