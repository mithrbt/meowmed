package com.capgemini.meowmed.controller;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import com.capgemini.meowmed.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capgemini.meowmed.exception.ResourceNotFoundException;
import com.capgemini.meowmed.model.Customer;
import com.capgemini.meowmed.repository.CustomerRepository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;


    //Get all customers
    @GetMapping("/kunden")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }


    //Read customer by id
    @GetMapping("/kunden/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") int customerId) throws ResourceNotFoundException {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Es gibt keinen Kunden mit der ID: " + customerId));

        return ResponseEntity.ok().body(customer);
    }


    //Create customer
    @PostMapping("/kunden")
    public Customer createCustomer(@Valid @RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    //Update customer
    @PutMapping("/kunden/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") int customerId, @Valid @RequestBody Customer customerDetails) throws ResourceNotFoundException {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Es gibt keinen Kunden mit der ID: " + customerId));

        customer.setTitle(customerDetails.getTitle());
        customer.setFirstname(customerDetails.getFirstname());
        customer.setLastname(customerDetails.getLastname());
        customer.setEmail(customerDetails.getEmail());
        customer.setAddress(customerDetails.getAddress());
        customer.setBirthdate(customerDetails.getBirthdate());
        customer.setTelNr(customerDetails.getTelNr());
        customer.setFamilyStatus(customerDetails.getFamilyStatus());
        customer.setTaxID(customerDetails.getTaxID());
        customer.setSvn(customerDetails.getSvn());
        customer.setIncome(customerDetails.getIncome());
        customer.setProfession(customerDetails.getProfession());
        customer.setBankDetails(customerDetails.getBankDetails());

        final Customer updateCustomer = customerRepository.save(customer);
        return ResponseEntity.ok(updateCustomer);
    }

    //delete customer
    @DeleteMapping("/kunden/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCustomer(@PathVariable(value = "id") int customerId) throws ResourceNotFoundException {
        customerRepository.deleteById(customerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //upload profile picture
    @PostMapping("/{customerId}/profile-picture")
    public ResponseEntity<String> uploadProfilePicture(@PathVariable int customerId,
                                                       @RequestParam("file") MultipartFile file) {
        try {
            Customer customer = customerRepository.findById(customerId).orElseThrow(EntityNotFoundException::new);
            customer.setProfilePicture((Image) file);
            customerRepository.save(customer);
            return ResponseEntity.status(HttpStatus.OK).body("Profile picture uploaded successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload profile picture.");
        }
    }

    //get profile picture
    @GetMapping("/{customerId}/profile-picture")
    public ResponseEntity<Image> getProfilePicture(@PathVariable int customerId) {
        Image picture = customerRepository.findById(customerId)
                .map(Customer::getProfilePicture)
                .orElse(null);

        if (picture != null) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(picture);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
