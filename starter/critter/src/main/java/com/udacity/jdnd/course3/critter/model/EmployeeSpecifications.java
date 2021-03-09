package com.udacity.jdnd.course3.critter.model;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.time.DayOfWeek;
import java.util.Collection;
import java.util.Set;

public class EmployeeSpecifications {

    public static Specification<Employee> byDaysAndSkills(Set<DayOfWeek> byDays, Set<EmployeeSkill> skills) {
        return (r, cq, cb) -> {
            cq.distinct(true);
            Predicate where = cb.conjunction();
            for (DayOfWeek d : byDays) {
                where = cb.and(where, cb.isMember(cb.literal(d), r.get(Employee_.daysAvailable)));
            }
            for (EmployeeSkill s : skills) {
                where = cb.and(where, cb.isMember(cb.literal(s), r.get(Employee_.skills)));
            }
            return where;
        };
    }
}
