package com.capgemini.meowmed.repository;

import com.capgemini.meowmed.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Integer> {

    //Alle Verträge eines Kundens
    public List<Contract> findByCustomerId(int customerID);
}
