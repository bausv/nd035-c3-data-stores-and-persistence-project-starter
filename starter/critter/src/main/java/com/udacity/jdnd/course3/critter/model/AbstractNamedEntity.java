package com.udacity.jdnd.course3.critter.model;

import org.hibernate.annotations.Nationalized;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractNamedEntity extends AbstractBaseEntity {

    @Nationalized
    private String name;

    public AbstractNamedEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
