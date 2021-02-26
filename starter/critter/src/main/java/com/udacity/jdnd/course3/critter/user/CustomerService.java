package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.mapper.CustomerMapper;
import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private CustomerRepository repository;

    private final CustomerMapper mapper;

    public CustomerService(CustomerRepository repository, CustomerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        return mapper.entityToDTO(repository.save(mapper.dtoToEntity(customerDTO)));
    }

    public List<CustomerDTO> getAllCustomers() {
        return repository.findAll().stream().map(c -> mapper.entityToDTO(c)).collect(Collectors.toList());
    }

    public Customer findById(long ownerId) {
        if (ownerId > 0) {
            return repository.findById(ownerId).orElse(null);
        } else {
            return null;
        }
    }
}
