package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.model.EmployeeRepository;
import com.udacity.jdnd.course3.critter.model.PetRepository;
import com.udacity.jdnd.course3.critter.model.Schedule;
import com.udacity.jdnd.course3.critter.model.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    private ScheduleRepository repository;
    private EmployeeRepository employeeRepository;
    private PetRepository petRepository;

    public ScheduleService(ScheduleRepository repository, EmployeeRepository employeeRepository, PetRepository petRepository) {
        this.repository = repository;
        this.employeeRepository = employeeRepository;
        this.petRepository = petRepository;
    }

    public Schedule createSchedule(Schedule schedule) {
        schedule.setEmployees(employeeRepository.findAllById(schedule.getEmployees().stream().map(e -> e.getId()).collect(Collectors.toList())).stream().collect(Collectors.toSet()));
        schedule.setPets(petRepository.findAllById(schedule.getPets().stream().map(p -> p.getId()).collect(Collectors.toList())).stream().collect(Collectors.toSet()));
        schedule = repository.save(schedule);
        return schedule;
    }

    public List<Schedule> getAllSchedules() {
        return repository.findAll();
    }

    public List<Schedule> getScheduleForPet(long petId) {
        return repository.findByPets_id(petId);
    }

    public List<Schedule> getScheduleForEmployee(long employeeId) {
        return repository.findByEmployees_Id(employeeId);
    }

    public List<Schedule> getScheduleForCustomer(long customerId) {
        return petRepository.findByOwnerId(customerId).stream().flatMap(p -> repository.findByPets_id(p.getId()).stream()).collect(Collectors.toList());
    }
}
