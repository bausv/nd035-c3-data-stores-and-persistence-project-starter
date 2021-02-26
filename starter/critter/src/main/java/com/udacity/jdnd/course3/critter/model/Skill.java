package com.udacity.jdnd.course3.critter.model;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.Entity;

@Entity
public class Skill extends AbstractBaseEntity {

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
