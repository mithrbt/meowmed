package com.capgemini.meowmed.repository;

import com.capgemini.meowmed.model.Breed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface BreedRepository extends JpaRepository<Breed, String> {
}
