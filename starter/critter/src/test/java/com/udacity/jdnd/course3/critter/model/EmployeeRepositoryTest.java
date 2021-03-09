package com.udacity.jdnd.course3.critter.model;

import com.google.common.collect.Sets;
import com.udacity.jdnd.course3.critter.CritterApplication;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.DayOfWeek;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = CritterApplication.class)
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void testSaveEmployee() {
        Employee e = new Employee();
        e.setName("employee");
        e.setDaysAvailable(Sets.newHashSet(DayOfWeek.MONDAY));
        e.setSkills(Sets.newHashSet(EmployeeSkill.FEEDING));
        Employee saved = employeeRepository.save(e);
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
    }
}
