package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.mapper.CustomerMapper;
import com.udacity.jdnd.course3.critter.mapper.PetMapper;
import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.CustomerRepository;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.model.PetRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PetService {

    private PetMapper mapper;
    private CustomerMapper customerMapper;
    private PetRepository repository;
    private CustomerRepository customerRepository;

    public PetService(PetMapper mapper, CustomerMapper customerMapper, PetRepository repository, CustomerRepository customerRepository) {
        this.mapper = mapper;
        this.customerMapper = customerMapper;
        this.repository = repository;
        this.customerRepository = customerRepository;
    }


    public PetDTO savePet(PetDTO petDTO) {
        Pet entity = mapper.dtoToEntity(petDTO);
        Customer owner = customerRepository.findById(petDTO.getOwnerId()).orElseGet(() -> {
            Customer c = new Customer();
            c.setId(petDTO.getOwnerId());
            return c;
        });
        entity.setOwner(owner);
        Pet saved = repository.save(entity);
        PetDTO dto = mapper.entityToDTO(saved);
        dto.setOwnerId(saved.getOwner().getId());
        return dto;
    }

    public PetDTO getPet(long petId) {
        return mapper.entityToDTO(repository.findById(petId).orElseThrow());
    }

    public List<PetDTO> getPetsByOwner(long ownerId) {
        return repository.findByOwnerId(ownerId).stream().map(p -> mapper.entityToDTO(p)).collect(Collectors.toList());
    }

    public Set<Pet> idListToPetSet(List<Long> ids) {
        if (ids != null && !ids.isEmpty()) {
            return repository.findAllById(ids).stream().collect(Collectors.toSet());
        } else {
            return Collections.emptySet();
        }
    }

    public List<Long> petSetToIdList(Set<Pet> pets) {
        return pets.stream().map(p -> p.getId()).collect(Collectors.toList());
    }
}
