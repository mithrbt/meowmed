package com.capgemini.meowmed.repository;

import com.capgemini.meowmed.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CatRepository extends JpaRepository<Cat, Integer> {

    public List<Cat> findByCustomerId(int customerID);

    public Cat findCatByContractId(int contractID);

    public void deleteCatByContractId(int contractID);

    public void deleteByCustomerId(int customerID);
}
