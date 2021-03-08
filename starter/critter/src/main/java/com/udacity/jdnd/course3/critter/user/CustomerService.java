package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.mapper.CustomerMapper;
import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.CustomerRepository;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.model.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private CustomerRepository repository;
    private PetRepository petRepository;

    private final CustomerMapper mapper;

    public CustomerService(CustomerRepository repository, PetRepository petRepository, CustomerMapper mapper) {
        this.repository = repository;
        this.petRepository = petRepository;
        this.mapper = mapper;
    }


    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        return mapper.entityToDTO(repository.save(mapper.dtoToEntity(customerDTO)));
    }

    public List<CustomerDTO> getAllCustomers() {
        return repository.findAll().stream().map(c -> {
            return doMapCustomerEntityWithPets(c);
        }).collect(Collectors.toList());
    }

    private CustomerDTO doMapCustomerEntityWithPets(Customer c) {
        CustomerDTO dto = mapper.entityToDTO(c);
        List<Pet> byOwnerId = petRepository.findByOwnerId(c.getId());
        dto.setPetIds(byOwnerId.stream().map(p -> p.getId()).collect(Collectors.toList()));
        return dto;
    }

    public Customer findById(long ownerId) {
        if (ownerId > 0) {
            return repository.findById(ownerId).orElse(null);
        } else {
            return null;
        }
    }

    public CustomerDTO getOwnerByPet(long petId) {
        Pet pet = petRepository.findById(petId).orElseThrow();
        List<Pet> byOwnerId = petRepository.findByOwnerId(pet.getOwner().getId());
        Customer customer = repository.findById(pet.getOwner().getId()).orElseThrow();
        CustomerDTO customerDTO = mapper.entityToDTO(customer);
        customerDTO.setPetIds(byOwnerId.stream().map(p -> p.getId()).collect(Collectors.toList()));
        return customerDTO;
    }
}
