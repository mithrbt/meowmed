package com.capgemini.meowmed.controller;


import com.capgemini.meowmed.exception.ResourceNotFoundException;
import com.capgemini.meowmed.model.Cat;
import com.capgemini.meowmed.model.Contract;
import com.capgemini.meowmed.repository.CatRepository;
import com.capgemini.meowmed.repository.ContractRepository;
import jakarta.transaction.Transactional;
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
public class CatController {

    @Autowired
    private CatRepository catRepository;

    @Autowired
    private ContractRepository contractRepository;

    //get all cats
    @GetMapping("/kunden/{customerID}/katze")
    public List<Cat> getAllCatsByCustomerID(@PathVariable int customerID){
        return catRepository.findByCustomerId(customerID);
    }

    //get cat by ID
    @GetMapping("/katze/{catID}")
    public ResponseEntity<Cat> getCatByID(@PathVariable int catID) throws ResourceNotFoundException{
        Cat cat = catRepository.findById(catID)
                .orElseThrow(() -> new ResourceNotFoundException("Es gibt keine Katze mit der ID: " + catID));

        return ResponseEntity.ok(cat);
    }

    //get cat by contract ID
    @GetMapping("/vertrag/{contractID}/katze")
    public Cat getCatByContractID(@PathVariable int contractID) throws ResourceNotFoundException{
        return catRepository.findCatByContractId(contractID);
    }

    //create cat
    @PostMapping("/vertrag/{contractID}/katze")
    public ResponseEntity<Cat> createCat(@PathVariable int contractID, @Valid @RequestBody Cat catRequest){
        Contract contract = contractRepository.findById(contractID)
                .orElseThrow(() -> new ResourceNotFoundException("Es gibt keinen Vertrag mit der ID: " + contractID));
        catRequest.setContract(contract);

        Cat cat = catRepository.save(catRequest);

        return new ResponseEntity<>(cat, HttpStatus.CREATED);
    }

    //update cat
    @PutMapping("/katze/{catID}")
    public ResponseEntity<Cat> updateCat(@PathVariable int catID, @Valid @RequestBody Cat catRequest) throws ResourceNotFoundException{
        Cat cat = catRepository.findById(catID)
                .orElseThrow(() -> new ResourceNotFoundException("Es gibt keine Katze mit der ID: " + catID));

        cat.setName(catRequest.getName());
        cat.setEnvironment(catRequest.getEnvironment());
        //cat.setPersonality(catRequest.getPersonality());
        cat.setContract(catRequest.getContract());

        final Cat updateCat = catRepository.save(cat);
        return ResponseEntity.ok(updateCat);
    }

    //delete cat
    /*@DeleteMapping("cat/{catID}")
    public void deleteCat(@PathVariable int catID) throws ResourceNotFoundException{
        catRepository.deleteById(catID);
    }*/

    @Transactional
    @DeleteMapping("katze/{contractID}")
    public void deleteCatByContractID(@PathVariable int contractID){
        catRepository.deleteCatByContractId(contractID);
    }

}
