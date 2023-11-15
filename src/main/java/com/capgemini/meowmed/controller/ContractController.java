package com.capgemini.meowmed.controller;

import com.capgemini.meowmed.enums.Color;
import com.capgemini.meowmed.enums.Environment;
import com.capgemini.meowmed.exception.ResourceNotFoundException;
import com.capgemini.meowmed.model.*;
import com.capgemini.meowmed.repository.ContractRepository;
import com.capgemini.meowmed.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class ContractController {

    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private CustomerRepository customerRepository;

    //Get all contracts
    @GetMapping("/kunden/{customerID}/vertrag")
    public List<Contract> getAllContractsByCustomerID(@PathVariable int customerID){
        return contractRepository.findByCustomerId(customerID);
    }

    //get contract by ID
    @GetMapping("/vertrag/{contractID}")
    public ResponseEntity<Contract> getContractById(@PathVariable int contractID) throws ResourceNotFoundException{

        Contract contract = contractRepository.findById(contractID)
                .orElseThrow(() -> new ResourceNotFoundException("Es gibt keinen Vertrag mit der ID: " + contractID));

        return ResponseEntity.ok().body(contract);
    }

    //create contract
    @PostMapping("/kunden/{customerID}/vertrag")
    public ResponseEntity<Contract> createContract(@PathVariable int customerID, @Valid @RequestBody Contract contractRequest){
        Contract contract = customerRepository.findById(customerID).map(customer -> {
            contractRequest.setCustomer(customer);
            return contractRepository.save(contractRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Es gibt keinen Kunden mit der ID: " + customerID));

        return new ResponseEntity<>(contract, HttpStatus.CREATED);
    }


    //update contract
    @PutMapping("/vertrag/{contractID}")
    public ResponseEntity<Contract> updateContract(@PathVariable int contractID, @Valid @RequestBody Contract contractRequest) throws ResourceNotFoundException{
        Contract contract = contractRepository.findById(contractID)
                .orElseThrow(() -> new ResourceNotFoundException("Es gibt keinen Vertrag mit der ID: " + contractID));

        contract.setCustomer(contractRequest.getCustomer());
        contract.setCoverage(contractRequest.getCoverage());
        contract.setEnd(contractRequest.getEnd());
        contract.setStart(contractRequest.getStart());

        final Contract updateContract = contractRepository.save(contract);
        return ResponseEntity.ok(updateContract);
    }

    //delete contract
    @DeleteMapping("vertrag/{contractID}")
    public void deleteContract(@PathVariable int contractID) throws ResourceNotFoundException{
        contractRepository.deleteById(contractID);
    }

    static class Catract{
        public Cat cat;
        public Contract contract;

        public Customer customer;
    }

    @PostMapping("vertrag/quote")
    public double quote(@Valid @RequestBody Catract catract){
        double min = 5;
        double quote = 0;
        LocalDate curDate = LocalDate.now();
        long age = ChronoUnit.YEARS.between(catract.cat.getBirthdate(), curDate);
        double age25;

        age25 = catract.cat.getBreed().getMaxAverageAge() - ((catract.cat.getBreed().getMaxAverageAge() - catract.cat.getBreed().getMinAverageAge()) * 0.25);

        double basicValue = catract.cat.getColor() == Color.SCHWARZ ? min + catract.contract.getCoverage() * 0.2
                : min + catract.contract.getCoverage() * 0.15;


            if(catract.cat.getEnvironment() == Environment.DRAUSSEN){
                quote += (basicValue * 1.1);
            }
            if(!catract.cat.isCastrated()){
                quote += 5;
            }
            if(catract.cat.getWeight() > catract.cat.getBreed().getMaxWeight()) {
                double overweight = catract.cat.getBreed().getMaxWeight() - catract.cat.getWeight();
                quote += (overweight * 5);
            }
            if(age <= 2){
                basicValue -= (basicValue * 0.1);
            }
            if(age >= age25){
                basicValue += (basicValue * 0.2);
            }
            if(catract.customer.getAddress().getZipCode() == 7 || catract.customer.getAddress().getZipCode() == 8){
                basicValue += (basicValue * 0.05);
            }

        quote += basicValue + catract.cat.getBreed().getProbabilityOfIllness();

        return quote;
    }


}
