package com.udacity.jdnd.course3.critter.user;

import com.google.common.collect.Sets;
import com.udacity.jdnd.course3.critter.mapper.EmployeeMapper;
import com.udacity.jdnd.course3.critter.model.AvailabilityRepository;
import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.EmployeeRepository;
import com.udacity.jdnd.course3.critter.model.SkillRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private EmployeeMapper mapper;
    private EmployeeRepository repository;
    private SkillRepository skillRepository;
    private AvailabilityRepository availabilityRepository;

    public EmployeeService(EmployeeMapper mapper, EmployeeRepository repository, SkillRepository skillRepository, AvailabilityRepository availabilityRepository) {
        this.mapper = mapper;
        this.repository = repository;
        this.skillRepository = skillRepository;
        this.availabilityRepository = availabilityRepository;
    }

    public EmployeeDTO saveEmployee(EmployeeDTO in) {
        return mapper.entityToDTO(repository.save(mapper.dtoToEntity(in)));
    }

    public EmployeeDTO getEmployee(long employeeId) throws EmployeeNotFoundException {
        return mapper.entityToDTO(repository.findById(employeeId).orElseThrow(EmployeeNotFoundException::new));
    }

    public List<EmployeeDTO> findEmployeesForService(EmployeeRequestDTO employeeDTO) {
        DayOfWeek dayOfWeek = employeeDTO.getDate().getDayOfWeek();
        Set<String> skills = employeeDTO.getSkills().stream().map(s -> s.toString()).collect(Collectors.toSet());

        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreNullValues().withIgnorePaths("version");
        Employee e = new Employee();
        e.setSkills(employeeDTO.getSkills().stream().map(s -> skillRepository.findBySkill(s).orElseThrow()).collect(Collectors.toSet()));
        e.setDaysAvailable(Sets.newHashSet(availabilityRepository.findByDayOfWeek(employeeDTO.getDate().getDayOfWeek()).orElseThrow()));
        return repository.findAll(Example.of(e, matcher)).stream().map(en -> mapper.entityToDTO(en)).collect(Collectors.toList());

//        return repository.getEmployeesByServiceAndDayOfWeek(dayOfWeek.toString(), skills).stream().map(e -> mapper.entityToDTO(e)).collect(Collectors.toList());
    }
}
