package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static com.udacity.jdnd.course3.critter.model.EmployeeSpecifications.byDaysAndSkills;

@Service
@Transactional
public class EmployeeService {
    private EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee saveEmployee(Employee in) {
        Optional<Employee> existing = repository.findById(in.getId());
        Employee employee;
        if (existing.isPresent()) {
            employee = existing.get();
            employee.setDaysAvailable(in.getDaysAvailable());
            employee.setSkills(in.getSkills());
            employee.setName(in.getName());
        } else {
            employee = in;
        }
        return repository.save(employee);
    }

    public Employee getEmployee(long employeeId) throws EmployeeNotFoundException {
        return repository.findById(employeeId).orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> findEmployeesForService(DayOfWeek dayOfWeek, Set<EmployeeSkill> skills) {
        return repository.findAll(byDaysAndSkills(newHashSet(dayOfWeek), skills));
    }
}
