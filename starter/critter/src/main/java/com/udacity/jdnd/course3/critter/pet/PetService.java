package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.mapper.PetMapper;
import com.udacity.jdnd.course3.critter.model.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService {

    private PetMapper mapper;
    private PetRepository repository;

    public PetService(PetMapper mapper, PetRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }


    public PetDTO savePet(PetDTO petDTO) {
        return mapper.entityToDTO(repository.save(mapper.dtoToEntity(petDTO)));
    }

    public PetDTO getPet(long petId) {
        return mapper.entityToDTO(repository.findById(petId).orElseThrow());
    }

    public List<PetDTO> getPetsByOwner(long ownerId) {
        return repository.findByOwnerId(ownerId).stream().map(p -> mapper.entityToDTO(p)).collect(Collectors.toList());
    }
}
