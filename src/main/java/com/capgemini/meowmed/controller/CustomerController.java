package com.capgemini.meowmed.controller;

import javax.validation.Valid;

import com.capgemini.meowmed.model.BankDetails;
import com.capgemini.meowmed.service.BankDetailsEncryptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capgemini.meowmed.exception.ResourceNotFoundException;
import com.capgemini.meowmed.model.Customer;
import com.capgemini.meowmed.repository.CustomerRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class CustomerController {
    
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BankDetailsEncryptService bankDetailsEncryptService;


    //Read all customers
    @GetMapping("/kunden")
    public List<Customer> getAllCustomers(){
        try {
            List<Customer> customers = customerRepository.findAll();

            for (Customer customer : customers) {
                byte[] encryptedBankDetails = customer.getEncryptedBankDetails();

                if (encryptedBankDetails != null) {
                    BankDetails bankDetails = bankDetailsEncryptService.decryptBankDetails(encryptedBankDetails, BankDetails.class);
                    customer.setBankDetails(bankDetails);
                }
            }

            return customers;
        } catch (Exception e) {
            e.printStackTrace();
            // Hier kannst du eine Fehlerbehandlung durchführen, z.B. eine Fehlermeldung zurückgeben
            return Collections.emptyList();
        }
    }


    //Read customer by id
    @GetMapping("/kunden/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") int customerId) throws ResourceNotFoundException{

        try {
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new ResourceNotFoundException("Es gibt keinen Kunden mit der ID: " + customerId));

            byte[] encryptedBankDetails = customer.getEncryptedBankDetails();

            if (encryptedBankDetails != null) {
                BankDetails bankDetails = bankDetailsEncryptService.decryptBankDetails(encryptedBankDetails, BankDetails.class);
                customer.setBankDetails(bankDetails);
            }

            return ResponseEntity.ok().body(customer);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //Create customer
    @PostMapping("/kunden")
    public Customer createCustomer(@Valid @RequestBody Customer customer){
        try{
            byte[] encryptedBankDetails = bankDetailsEncryptService.encryptBankDetails(customer.getBankDetails());
            customer.setEncryptedBankDetails(encryptedBankDetails);

            return customerRepository.save(customer);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //Update customer
    @PutMapping("/kunden/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") int customerId, @Valid @RequestBody Customer customerDetails) throws ResourceNotFoundException{

        Customer customer = customerRepository.findById(customerId)
        .orElseThrow(() -> new ResourceNotFoundException("Es gibt keinen Kunden mit der ID: " + customerId));

        customer.setFirstname(customerDetails.getFirstname());
        customer.setLastname(customerDetails.getLastname());
        customer.setAddress(customerDetails.getAddress());
        customer.setIncome(customerDetails.getIncome());
        customer.setProfession(customerDetails.getProfession());
        customer.setBirthdate(customerDetails.getBirthdate());
        customer.setTaxID(customerDetails.getTaxID());
        customer.setSvn(customerDetails.getSvn());
        customer.setTelNr(customerDetails.getTelNr());
        customer.setFamilyStatus(customerDetails.getFamilyStatus());


        final Customer updateCustomer = customerRepository.save(customer);
        return ResponseEntity.ok(updateCustomer);
    }

    //Delete customer
    @DeleteMapping("/kunden/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCustomer(@PathVariable(value = "id") int customerId) throws ResourceNotFoundException{
        customerRepository.deleteById(customerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
