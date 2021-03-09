package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.mapper.ScheduleMapper;
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
    private ScheduleMapper mapper;
    private EmployeeRepository employeeRepository;
    private PetRepository petRepository;

    public ScheduleService(ScheduleRepository repository, ScheduleMapper mapper, EmployeeRepository employeeRepository, PetRepository petRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.employeeRepository = employeeRepository;
        this.petRepository = petRepository;
    }

    public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO) {
        Schedule s = mapper.dtoToEntity(scheduleDTO);
        s.setEmployees(employeeRepository.findAllById(scheduleDTO.getEmployeeIds()).stream().collect(Collectors.toSet()));
        s.setPets(petRepository.findAllById(scheduleDTO.getPetIds()).stream().collect(Collectors.toSet()));
        s = repository.save(s);
        return mapper.entityToDTO(s);
    }

    public List<ScheduleDTO> getAllSchedules() {
        return repository.findAll().stream().map(s -> mapper.entityToDTO(s)).collect(Collectors.toList());
    }

    public List<ScheduleDTO> getScheduleForPet(long petId) {
        return repository.findByPets_id(petId).stream().map(s -> mapper.entityToDTO(s)).collect(Collectors.toList());
    }

    public List<ScheduleDTO> getScheduleForEmployee(long employeeId) {
        return repository.findByEmployees_Id(employeeId).stream().map(s -> mapper.entityToDTO(s)).collect(Collectors.toList());
    }

    public List<ScheduleDTO> getScheduleForCustomer(long customerId) {
        return petRepository.findByOwnerId(customerId).stream().flatMap(p -> repository.findByPets_id(p.getId()).stream()).map(s -> mapper.entityToDTO(s)).collect(Collectors.toList());
    }
}
