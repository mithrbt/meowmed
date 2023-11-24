package com.capgemini.meowmed.repository;

import com.capgemini.meowmed.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.*;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {
}
