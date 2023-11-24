package com.capgemini.meowmed.controller;

import com.capgemini.meowmed.exception.ResourceNotFoundException;
import com.capgemini.meowmed.model.Customer;
import com.capgemini.meowmed.model.Picture;
import com.capgemini.meowmed.repository.CustomerRepository;
import com.capgemini.meowmed.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
public class PictureController {

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/kunden/{customerID}/upload")
    public ResponseEntity<String> handleFileUpload(@PathVariable int customerID, @RequestParam("file") MultipartFile file) {
        try {
            Customer customer = customerRepository.findById(customerID)
                    .orElseThrow(() -> new ResourceNotFoundException("Es gibt keinen Kunden mit der ID: " + customerID));

            // Speichern Sie die Datei in der Datenbank
            Picture picture = new Picture();
            picture.setImageName(file.getOriginalFilename());
            picture.setImageData(file.getBytes());

            // Verknüpfen Sie das Bild mit dem Kunden
            customer.setPicture(picture);

            // Speichern Sie die Customer-Entity in der Datenbank
            customerRepository.save(customer);

            // Speichern Sie die Image-Entity in der Datenbank
            pictureRepository.save(picture);

            return ResponseEntity.ok("Bild erfolgreich hochgeladen: " + file.getOriginalFilename());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Fehler beim Hochladen des Bildes.");
        }
    }

    // Weitere Endpunkte für Bildoperationen können hinzugefügt werden
}
