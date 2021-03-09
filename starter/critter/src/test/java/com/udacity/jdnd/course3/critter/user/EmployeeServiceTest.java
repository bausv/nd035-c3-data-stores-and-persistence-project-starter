package com.udacity.jdnd.course3.critter.user;

import com.google.common.collect.Sets;
import com.udacity.jdnd.course3.critter.CritterApplication;
import com.udacity.jdnd.course3.critter.mapper.EmployeeMapper;
import com.udacity.jdnd.course3.critter.model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.DayOfWeek;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = CritterApplication.class)
class EmployeeServiceTest {

    @Autowired
    private EmployeeService service;

    @Autowired
    private EmployeeMapper mapper;

    @Test
    void testSaveAndEdit() throws EmployeeNotFoundException {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName("name");
        dto.setSkills(Sets.newHashSet(EmployeeSkill.SHAVING, EmployeeSkill.FEEDING));
        EmployeeDTO saved = mapper.entityToDTO(service.saveEmployee(mapper.dtoToEntity(dto)));
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull().isGreaterThan(0l);
        assertThat(saved.getName()).isEqualTo(dto.getName());

        Employee reloaded = service.getEmployee(saved.getId());
        HashSet<DayOfWeek> daysAvailable = Sets.newHashSet(DayOfWeek.MONDAY, DayOfWeek.TUESDAY);
        reloaded.setDaysAvailable(daysAvailable);
        EmployeeDTO saved2 = mapper.entityToDTO(service.saveEmployee(reloaded));
        assertThat(saved2).isNotNull();
        assertThat(saved2.getDaysAvailable()).isNotNull().isNotEmpty().containsExactlyInAnyOrderElementsOf(daysAvailable);

    }

}