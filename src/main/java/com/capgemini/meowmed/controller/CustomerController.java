package com.capgemini.meowmed.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capgemini.meowmed.exception.ResourceNotFoundException;
import com.capgemini.meowmed.model.Customer;
import com.capgemini.meowmed.repository.CustomerRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class CustomerController {
    
    @Autowired
    private CustomerRepository customerRepository;


    //Read all customers
    @GetMapping("/kunden")
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }


    //Read customer by id
    @GetMapping("/kunden/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") Long customerId) throws ResourceNotFoundException{
        
            Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new ResourceNotFoundException("Es gibt keinen Kunden mit dieser id: " + customerId));

            return ResponseEntity.ok().body(customer);
    }

    //Create customer
    @PostMapping("/kunden")
    public Customer createCustomer(@Valid @RequestBody Customer customer){
        return customerRepository.save(customer);
    }

    //Update customer
    @PutMapping("/kunden/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") Long id,@Valid @RequestBody Customer customerDetails) throws ResourceNotFoundException{

        Customer customer = customerRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Es gibt keinen Kunden mit dieser id: " + id));

        customer.setFirstname(customerDetails.getFirstname());
        customer.setLastname(customerDetails.getLastname());
        customer.setAddress(customerDetails.getAddress());

        Customer updateCustomer = customerRepository.save(customer);
        return ResponseEntity.ok(updateCustomer);
    }

    //Delete customer
    @DeleteMapping("/kunden/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCustomer(@PathVariable(value = "id") Long customerId) throws ResourceNotFoundException{
        Customer customer = customerRepository.findById(customerId)
        .orElseThrow(() -> new ResourceNotFoundException("Es gibt keinen Kunden mit dieser id: " + customerId));

        customerRepository.delete(customer);
        //Weil die delete Methode nichts zurückgibt, wird noch eine Map benötigt, die zurückgegeben wird
        Map<String, Boolean> response = new HashMap<>();
        response.put("Der Kunde wurde gelöscht", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}
