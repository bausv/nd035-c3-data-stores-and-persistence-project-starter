package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.mapper.PetMapper;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.user.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    private PetService petService;
    private PetMapper petMapper;
    private CustomerService customerService;

    public PetController(PetService petService, PetMapper petMapper, CustomerService customerService) {
        this.petService = petService;
        this.petMapper = petMapper;
        this.customerService = customerService;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet petIn = petMapper.dtoToEntity(petDTO);
        if (petDTO.getOwnerId() > 0l) {
            petIn.setOwner(customerService.findById(petDTO.getOwnerId()));
        }
        return petMapper.entityToDTO(petService.savePet(petIn));
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) throws PetNotFoundException {
        return petMapper.entityToDTO(petService.getPet(petId));
    }

    @GetMapping
    public List<PetDTO> getPets(){
        return petService.getPets().stream().map(p -> petMapper.entityToDTO(p)).collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        return petService.getPetsByOwner(ownerId).stream().map(p -> petMapper.entityToDTO(p)).collect(Collectors.toList());
    }
}
