package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.mapper.EmployeeMapper;
import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.google.common.collect.Sets.newHashSet;
import static com.udacity.jdnd.course3.critter.model.EmployeeSpecifications.byDaysAndSkills;

@Service
public class EmployeeService {

    private EmployeeMapper mapper;
    private EmployeeRepository repository;

    public EmployeeService(EmployeeMapper mapper, EmployeeRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public EmployeeDTO saveEmployee(EmployeeDTO in) {
        Employee mappedInput = mapper.dtoToEntity(in);
        Optional<Employee> existing = repository.findById(in.getId());
        Employee employee;
        if (existing.isPresent()) {
            employee = existing.get();
            employee.setDaysAvailable(mappedInput.getDaysAvailable());
            employee.setSkills(mappedInput.getSkills());
            employee.setName(mappedInput.getName());
        } else {
            employee = mappedInput;
        }
        return mapper.entityToDTO(repository.save(employee));
    }

    public EmployeeDTO getEmployee(long employeeId) throws EmployeeNotFoundException {
        return mapper.entityToDTO(repository.findById(employeeId).orElseThrow(EmployeeNotFoundException::new));
    }

    public List<EmployeeDTO> findEmployeesForService(EmployeeRequestDTO employeeDTO) {
        DayOfWeek dayOfWeek = employeeDTO.getDate().getDayOfWeek();
        Set<EmployeeSkill> skills = employeeDTO.getSkills();

        return repository.findAll(byDaysAndSkills(newHashSet(dayOfWeek), skills)).stream().map(e -> mapper.entityToDTO(e)).collect(Collectors.toList());
    }
}
