package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.mapper.CustomerMapper;
import com.udacity.jdnd.course3.critter.mapper.EmployeeMapper;
import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.pet.PetService;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final EmployeeService employeeService;
    private EmployeeMapper employeeMapper;

    private final CustomerService customerService;
    private CustomerMapper customerMapper;

    private PetService petService;

    public UserController(EmployeeService employeeService, EmployeeMapper employeeMapper, CustomerService customerService, CustomerMapper customerMapper, PetService petService) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
        this.customerService = customerService;
        this.customerMapper = customerMapper;
        this.petService = petService;
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        return customerMapper.entityToDTO(customerService.saveCustomer(customerMapper.dtoToEntity(customerDTO)));
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        return customerService.getAllCustomers().stream().map(c -> doMapCustomerEntityWithPets(c)).collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        Customer ownerByPet = customerService.getOwnerByPet(petId);
        CustomerDTO customerDTO = customerMapper.entityToDTO(ownerByPet);
        setPetIdsForCustomerDTO(ownerByPet, customerDTO);
        return customerDTO;
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeMapper.entityToDTO(employeeService.saveEmployee(employeeMapper.dtoToEntity(employeeDTO)));
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) throws EmployeeNotFoundException {
        return employeeMapper.entityToDTO(employeeService.getEmployee(employeeId));
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) throws EmployeeNotFoundException {
        Employee employee = employeeService.getEmployee(employeeId);
        employee.setDaysAvailable(daysAvailable);
        employeeService.saveEmployee(employee);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        return employeeService.findEmployeesForService(employeeDTO).stream().map(e -> employeeMapper.entityToDTO(e)).collect(Collectors.toList());
    }

    private CustomerDTO doMapCustomerEntityWithPets(Customer c) {
        CustomerDTO dto = customerMapper.entityToDTO(c);
        setPetIdsForCustomerDTO(c, dto);
        return dto;
    }

    private void setPetIdsForCustomerDTO(Customer c, CustomerDTO dto) {
        List<Pet> byOwnerId = petService.getPetsByOwner(c.getId());
        dto.setPetIds(byOwnerId.stream().map(p -> p.getId()).collect(Collectors.toList()));
    }

}
