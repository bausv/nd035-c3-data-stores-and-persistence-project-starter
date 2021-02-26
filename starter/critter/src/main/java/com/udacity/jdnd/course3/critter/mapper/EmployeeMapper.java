package com.udacity.jdnd.course3.critter.mapper;

import com.udacity.jdnd.course3.critter.model.AvailabilityRepository;
import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.SkillRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class EmployeeMapper {

    private AvailabilityRepository availabilityRepository;
    private SkillRepository skillRepository;

    public EmployeeMapper(AvailabilityRepository availabilityRepository, SkillRepository skillRepository) {
        this.availabilityRepository = availabilityRepository;
        this.skillRepository = skillRepository;
    }

    public EmployeeDTO entityToDTO(Employee e) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(e.getId() != null ? e.getId() : -1);
        dto.setName(e.getName());
        dto.setSkills(e.getSkills().stream().map(s -> s.getSkill()).collect(Collectors.toSet()));
        dto.setDaysAvailable(e.getDaysAvailable().stream().map(a -> a.getDayOfWeek()).collect(Collectors.toSet()));
        return dto;
    }

    public Employee dtoToEntity(EmployeeDTO dto) {
        Employee e = new Employee();
        e.setId(dto.getId());
        e.setName(dto.getName());
        if (dto.getDaysAvailable() != null) {
            e.setDaysAvailable(dto.getDaysAvailable().stream().map(d -> availabilityRepository.findByDayOfWeek(d).orElseThrow()).collect(Collectors.toSet()));
        }
        if (dto.getSkills() != null) {
            e.setSkills(dto.getSkills().stream().map(s -> skillRepository.findBySkill(s).orElseThrow()).collect(Collectors.toSet()));
        }
        return e;
    }
}
