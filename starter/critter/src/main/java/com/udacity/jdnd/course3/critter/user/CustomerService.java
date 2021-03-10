package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.CustomerRepository;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.model.PetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerService {

    private CustomerRepository repository;
    private PetRepository petRepository;

    public CustomerService(CustomerRepository repository, PetRepository petRepository) {
        this.repository = repository;
        this.petRepository = petRepository;
    }


    public Customer saveCustomer(Customer customerIn) {
        return repository.save(customerIn);
    }

    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    public Customer findById(long ownerId) {
        if (ownerId > 0) {
            return repository.findById(ownerId).orElse(null);
        } else {
            return null;
        }
    }

    public Customer getOwnerByPet(long petId) {
        Pet pet = petRepository.findById(petId).orElseThrow();
        List<Pet> byOwnerId = petRepository.findByOwnerId(pet.getOwner().getId());
        Customer customer = repository.findById(pet.getOwner().getId()).orElseThrow();
        return customer;
    }
}
