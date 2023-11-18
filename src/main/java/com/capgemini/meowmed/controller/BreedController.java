package com.capgemini.meowmed.controller;

import com.capgemini.meowmed.exception.ResourceNotFoundException;
import com.capgemini.meowmed.model.Breed;
import com.capgemini.meowmed.repository.BreedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class BreedController {

    @Autowired
    private BreedRepository breedRepository;

    @GetMapping("katze/rassen")
    public List<Breed> getAllBreeds(){
        return breedRepository.findAll();
    }

    @GetMapping("rassen/{rassenName}")
    public Breed getBreedById(@PathVariable String rassenName) throws ResourceNotFoundException{
        return breedRepository.findBreedByName(rassenName);
    }
}
