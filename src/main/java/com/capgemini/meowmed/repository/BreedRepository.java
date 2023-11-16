package com.capgemini.meowmed.repository;

import com.capgemini.meowmed.model.Breed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BreedRepository extends JpaRepository<Breed, String> {
        Breed findBreedByName(String name);

        List<Breed> findAll();
}
