package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.CustomerRepository;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.model.PetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetService {

    private PetRepository repository;
    private CustomerRepository customerRepository;

    public PetService(PetRepository repository, CustomerRepository customerRepository) {
        this.repository = repository;
        this.customerRepository = customerRepository;
    }


    public Pet savePet(Pet petIn) {
        Optional<Customer> owner = petIn.getOwner() != null && petIn.getOwner().getId() > 0l ? customerRepository.findById(petIn.getOwner().getId()) : Optional.empty();
        if (owner.isPresent()) {
            petIn.setOwner(owner.get());
        }
        Pet saved = repository.save(petIn);
        return saved;
    }

    public Pet getPet(long petId) throws PetNotFoundException {
        return repository.findById(petId).orElseThrow(() -> new PetNotFoundException(petId));
    }

    public List<Pet> getPetsByOwner(long ownerId) {
        return repository.findByOwnerId(ownerId);
    }

    public List<Pet> getPets() {
        return repository.findAll();
    }
}
