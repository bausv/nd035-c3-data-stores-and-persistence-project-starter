package com.udacity.jdnd.course3.critter.model;

import com.udacity.jdnd.course3.critter.model.AbstractNamedEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Person extends AbstractNamedEntity {

    public Person() {
    }
}
