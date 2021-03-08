package com.udacity.jdnd.course3.critter.model;

import com.udacity.jdnd.course3.critter.CritterApplication;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = CritterApplication.class)
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private PetRepository petRepository;

    @BeforeEach
    @AfterEach
    void setup() {
        petRepository.deleteAllInBatch();
        repository.deleteAllInBatch();
    }

    @Test
    @Transactional
    void testSaveCustomer() {
        Customer c = new Customer();
        c.setName("name");
        c.setPhoneNumber("12345");
        c.setNotes("notes");
        Set<Pet> pets = new HashSet<>();
        Pet pet = new Pet();
//        pet.setOwner(c);
        pet.setName("petName");
        pet.setNotes("petNotes");
        pets.add(pet);
        c.setPets(pets);
        Customer saved = repository.save(c);
        checkCustomer(c, pets, saved);
        checkCustomer(c, pets, repository.findById(saved.getId()).orElseThrow());
        Customer firstFromFindAll = repository.findAll().get(0);
        checkCustomer(c, pets, firstFromFindAll);
    }

    private void checkCustomer(Customer c, Set<Pet> pets, Customer saved) {
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getNotes()).isEqualTo(c.getNotes());
        assertThat(saved.getPets()).isNotNull().isNotEmpty().hasSize(pets.size()).usingElementComparatorOnFields("name").containsExactlyElementsOf(pets);
    }

}