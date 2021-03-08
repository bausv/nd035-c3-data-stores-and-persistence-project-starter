package com.udacity.jdnd.course3.critter.model;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Skill extends AbstractBaseEntity {

    @Enumerated(EnumType.STRING)
    private EmployeeSkill skill;

    public Skill() {
    }

    public Skill(EmployeeSkill skill) {
        this.skill = skill;
    }

    public EmployeeSkill getSkill() {
        return skill;
    }

    public void setSkill(EmployeeSkill skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return "Skill{" + super.toString() +
                ", skill=" + skill +
                '}';
    }
}
