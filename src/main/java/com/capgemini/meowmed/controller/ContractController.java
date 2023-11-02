package com.capgemini.meowmed.controller;

import com.capgemini.meowmed.exception.ResourceNotFoundException;
import com.capgemini.meowmed.model.Contract;
import com.capgemini.meowmed.model.Customer;
import com.capgemini.meowmed.repository.ContractRepository;
import com.capgemini.meowmed.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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
    public List<Contract> getAllContracts(@PathVariable int customerID){
        return contractRepository.findByCustomerId(customerID);
    }

    //get contract by ID
    @GetMapping("/vertrag/{contractID}")
    public ResponseEntity<Contract> getContractById(@PathVariable int customerID, @PathVariable int contractId) throws ResourceNotFoundException{

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Es gibt keinen Vertrag mit der ID: " + contractId));

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
    public ResponseEntity<Map<String, Boolean>> deleteContract(@PathVariable int contractID) throws ResourceNotFoundException{
        contractRepository.deleteById(contractID);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
