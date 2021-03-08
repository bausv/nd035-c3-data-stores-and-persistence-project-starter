package com.udacity.jdnd.course3.critter.model;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "SELECT\te.*\n" +
            "FROM\temployee e\n" +
            "INNER JOIN\temployee_availability ea\n" +
            "\tON\te.id = ea.employee_id\n" +
            "INNER JOIN\tavailability a\n" +
            "\tON\tea.availability_id = a.id\n" +
            "INNER JOIN\temployee_skill es\n" +
            "\tON\te.id = es.employee_id\n" +
            "INNER JOIN\tskill s\n" +
            "\tON\tes.skill_id = s.id\n" +
            "WHERE\ta.day_of_week = :dayOfWeek\n" +
            "AND\t\ts.skill IN (:skills)", nativeQuery = true)
//    @Query("SELECT e FROM Employee e JOIN Availability a JOIN Skill s WHERE a.dayOfWeek = :dayOfWeek AND s.skill IN :skills")
    List<Employee> getEmployeesByServiceAndDayOfWeek(@Param("dayOfWeek") String dayOfWeek, @Param("skills") Set<String> skills);
}
