package com.capgemini.meowmed.repository;

import com.capgemini.meowmed.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatRepository extends JpaRepository<Cat, Integer> {

    public List<Cat> findByContractId(int contractID);
}
