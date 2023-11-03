package com.capgemini.meowmed.controller;


import com.capgemini.meowmed.exception.ResourceNotFoundException;
import com.capgemini.meowmed.model.Cat;
import com.capgemini.meowmed.repository.CatRepository;
import com.capgemini.meowmed.repository.ContractRepository;
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
    @GetMapping("vertrag/{contractID}/katze")
    public List<Cat> getAllCatsByContractID(@PathVariable int contractID){
        return catRepository.findByContractId(contractID);
    }

    //get cat by ID
    @GetMapping("/katze/{catID}")
    public ResponseEntity<Cat> getCatByID(@PathVariable int catID) throws ResourceNotFoundException{
        Cat cat = catRepository.findById(catID)
                .orElseThrow(() -> new ResourceNotFoundException("Es gibt keine Katze mit der ID: " + catID));

        return ResponseEntity.ok(cat);
    }

    //create cat
    @PostMapping("/vertrag/{contractID}/katze")
    public ResponseEntity<Cat> createCat(@PathVariable int contractID, @Valid @RequestBody Cat catRequest){
        Cat cat = contractRepository.findById(contractID).map(contract -> {
            catRequest.setContract(contract);
            return catRepository.save(catRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Es gibt keinen Vertrag mit der ID: " + contractID));

        return new ResponseEntity<>(cat, HttpStatus.CREATED);
    }

    //update cat
    @PutMapping("/katze/{catID}")
    public ResponseEntity<Cat> updateCat(@PathVariable int catID, @Valid @RequestBody Cat catRequest) throws ResourceNotFoundException{
        Cat cat = catRepository.findById(catID)
                .orElseThrow(() -> new ResourceNotFoundException("Es gibt keine Katze mit der ID: " + catID));

        cat.setName(catRequest.getName());
        cat.setEnvironment(catRequest.getEnvironment());
        cat.setPersonality(catRequest.getPersonality());
        cat.setContract(catRequest.getContract());

        final Cat updateCat = catRepository.save(cat);
        return ResponseEntity.ok(updateCat);
    }

    //delete cat
    @DeleteMapping("cat/{catID}")
    public void deleteCat(@PathVariable int catID) throws ResourceNotFoundException{
        catRepository.deleteById(catID);
    }

}
