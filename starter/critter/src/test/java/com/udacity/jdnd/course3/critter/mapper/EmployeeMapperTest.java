package com.udacity.jdnd.course3.critter.mapper;

import com.udacity.jdnd.course3.critter.CritterApplication;
import com.udacity.jdnd.course3.critter.model.Availability;
import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.Skill;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = CritterApplication.class)
class EmployeeMapperTest {

    @Autowired
    private EmployeeMapper mapper;

    @Test
    public void testEntityToDTO() {
        Employee e = new Employee();
        e.setName("aName");
        e.setSkills(Arrays.asList(new Skill(EmployeeSkill.FEEDING)).stream().collect(Collectors.toSet()));
        e.setDaysAvailable(Arrays.asList(new Availability(DayOfWeek.MONDAY), new Availability(DayOfWeek.WEDNESDAY)).stream().collect(Collectors.toSet()));
        EmployeeDTO dto = mapper.entityToDTO(e);
        assertThat(dto).isNotNull();
        assertThat(dto.getName()).isEqualTo(e.getName());
        assertThat(dto.getDaysAvailable()).containsExactlyInAnyOrder(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY);
        assertThat(dto.getSkills()).containsExactlyInAnyOrder(EmployeeSkill.FEEDING);
    }

    @Test
    public void testDTOToEntity() {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(5);
        dto.setName("dtoName");
        Set<EmployeeSkill> skills = Arrays.asList(EmployeeSkill.SHAVING, EmployeeSkill.WALKING).stream().collect(Collectors.toSet());
        dto.setSkills(skills);
        Set<DayOfWeek> daysAvailable = Arrays.asList(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY).stream().collect(Collectors.toSet());
        dto.setDaysAvailable(daysAvailable);
        Employee e = mapper.dtoToEntity(dto);
        assertThat(e).isNotNull();
        assertThat(e.getId()).isEqualTo(dto.getId());
        assertThat(e.getName()).isEqualTo(dto.getName());
        assertThat(e.getDaysAvailable().stream().map(a -> a.getDayOfWeek())).containsExactlyInAnyOrderElementsOf(daysAvailable);
        assertThat(e.getSkills().stream().map(s -> s.getSkill())).containsExactlyInAnyOrderElementsOf(skills);
    }

}