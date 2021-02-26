package com.udacity.jdnd.course3.critter.mapper;

import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    private PetMapper petMapper;

    public CustomerMapper(PetMapper petMapper) {
        this.petMapper = petMapper;
    }

    public Customer dtoToEntity(CustomerDTO dto) {
        Customer c = new Customer();
        c.setName(dto.getName());
        c.setPhoneNumber(dto.getPhoneNumber());
        c.setNotes(dto.getNotes());
        if (dto.getId() > 0) {
            c.setId(dto.getId());
        }
        c.setPets(petMapper.idListToPetSet(dto.getPetIds()));
        return c;
    }

    public CustomerDTO entityToDTO(Customer c) {
        CustomerDTO dto = new CustomerDTO();
        if (c.getId() != null) {
            dto.setId(c.getId());
        }
        dto.setName(c.getName());
        dto.setNotes(c.getNotes());
        dto.setPhoneNumber(c.getPhoneNumber());
        dto.setPetIds(petMapper.petSetToIdList(c.getPets()));
        return dto;
    }
}
