package com.capgemini.meowmed.repository;

import com.capgemini.meowmed.model.Breed;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BreedRepository extends JpaRepository<Breed, String> {

    public Breed findBreedByName(String breedName);
}
