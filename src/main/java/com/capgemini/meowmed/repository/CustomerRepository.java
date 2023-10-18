/*Hier wird die CustomerRepository-Schnittstelle definiert, die von JpaRepository erbt und für die Verwaltung von Customer-Objekten in einer Datenbank verwendet wird.*/
package com.capgemini.meowmed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.meowmed.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    
}