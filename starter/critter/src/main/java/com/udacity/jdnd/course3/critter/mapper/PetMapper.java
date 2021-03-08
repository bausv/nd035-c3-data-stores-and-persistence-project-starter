package com.udacity.jdnd.course3.critter.mapper;

import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.model.PetRepository;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PetMapper {

    public PetMapper() {
    }

    public Pet dtoToEntity(PetDTO dto) {
        Pet p = new Pet();
        p.setNotes(dto.getNotes());
        p.setBirthDate(dto.getBirthDate());
        p.setType(dto.getType());
        if (dto.getId() > 0) {
            p.setId(dto.getId());
        }
        return p;
    }

    public PetDTO entityToDTO(Pet save) {
        PetDTO dto = new PetDTO();
        dto.setBirthDate(save.getBirthDate());
        if (save.getId() != null) {
            dto.setId(save.getId());
        }
        dto.setNotes(save.getNotes());
        dto.setName(save.getName());
        dto.setType(save.getType());
        if (save.getOwner() != null && save.getOwner().getId() != null) {
            dto.setOwnerId(save.getOwner().getId());
        }
        return dto;
    }
}
