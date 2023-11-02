/*Hier wird die CustomerRepository-Schnittstelle definiert, die von JpaRepository erbt und für die Verwaltung von Customer-Objekten in einer Datenbank verwendet wird.*/
package com.capgemini.meowmed.repository;

import com.capgemini.meowmed.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.meowmed.model.Customer;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
}