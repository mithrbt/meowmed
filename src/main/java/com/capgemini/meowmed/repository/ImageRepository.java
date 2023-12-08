package com.capgemini.meowmed.repository;

import com.capgemini.meowmed.model.Contract;
import com.capgemini.meowmed.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    public Image findByCustomerId(int customerId);

    Optional<Image> findByName(String name);
}
