package com.udacity.jdnd.course3.critter.model;

import com.google.common.collect.Sets;
import com.udacity.jdnd.course3.critter.CritterApplication;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.DayOfWeek;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(classes = CritterApplication.class)
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Test
    void testSaveEmployee() {
        Employee e = new Employee();
        e.setName("employee");
        Availability availability = availabilityRepository.findByDayOfWeek(DayOfWeek.MONDAY).orElseThrow();
        e.setDaysAvailable(Sets.newHashSet(availability));
        Skill skill = skillRepository.findBySkill(EmployeeSkill.FEEDING).orElseThrow();
        e.setSkills(Sets.newHashSet(skill));
        Employee saved = employeeRepository.save(e);
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
    }
}
