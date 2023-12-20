package com.capgemini.meowmed.repository;

import com.capgemini.meowmed.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    Optional<Image> findByCustomerId(int customerId);

    Optional<Image> findByName(String name);

    void deleteByCustomerId(int customerId);

    void deleteByCatId(int catId);

    Optional<Image> findByCatId(int catID);
}
