package com.udacity.jdnd.course3.critter.pet;

public class PetNotFoundException extends Exception {

    public PetNotFoundException(Long id) {
        super("No pet with Id [" + id + "] available!");
    }
}
