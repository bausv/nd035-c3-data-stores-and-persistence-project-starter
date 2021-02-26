package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.mapper.EmployeeMapper;
import com.udacity.jdnd.course3.critter.model.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private EmployeeMapper mapper;
    private EmployeeRepository repository;

    public EmployeeService(EmployeeMapper mapper, EmployeeRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public EmployeeDTO saveEmployee(EmployeeDTO in) {
        return mapper.entityToDTO(repository.save(mapper.dtoToEntity(in)));
    }

    public EmployeeDTO getEmployee(long employeeId) throws EmployeeNotFoundException {
        return mapper.entityToDTO(repository.findById(employeeId).orElseThrow(EmployeeNotFoundException::new));
    }
}
