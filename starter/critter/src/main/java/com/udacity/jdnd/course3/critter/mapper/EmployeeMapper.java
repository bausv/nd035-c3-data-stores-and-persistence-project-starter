package com.udacity.jdnd.course3.critter.mapper;

import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class EmployeeMapper {

    public EmployeeMapper() {
    }

    public EmployeeDTO entityToDTO(Employee e) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(e.getId() != null ? e.getId() : -1);
        dto.setName(e.getName());
        dto.setSkills(e.getSkills());
        if (e.getDaysAvailable() != null) {
            dto.setDaysAvailable(e.getDaysAvailable());
        }
        return dto;
    }

    public Employee dtoToEntity(EmployeeDTO dto) {
        Employee e = new Employee();
        e.setId(dto.getId());
        e.setName(dto.getName());
        if (dto.getDaysAvailable() != null) {
            e.setDaysAvailable(dto.getDaysAvailable());
        }
        if (dto.getSkills() != null) {
            e.setSkills(dto.getSkills());
        }
        return e;
    }
}
