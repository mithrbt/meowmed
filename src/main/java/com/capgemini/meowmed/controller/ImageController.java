package com.capgemini.meowmed.controller;

import com.capgemini.meowmed.exception.ResourceNotFoundException;
import com.capgemini.meowmed.model.Customer;
import com.capgemini.meowmed.model.Image;
import com.capgemini.meowmed.repository.CustomerRepository;
import com.capgemini.meowmed.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.validation.Valid;
import javax.ws.rs.PathParam;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("/{customerID}/upload/image")
    public ResponseEntity<ImageUploadResponse> uplaodImage(@PathVariable int customerID, @RequestParam("image") MultipartFile file)
            throws IOException {
        Customer customer = customerRepository.findById(customerID).
                orElseThrow(() -> new ResourceNotFoundException("Es gibt keinen Kunden mit der ID: " + customerID));


        /*imageRepository.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .image(ImageUtility.compressImage(file.getBytes())).build()
                .setCustomer(customer));*/
        Image image = new Image();
        image.setName(file.getOriginalFilename());
        image.setType(file.getContentType());
        image.setImage(ImageUtility.compressImage(file.getBytes()));
        image.setCustomer(customer);

        imageRepository.save(image);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ImageUploadResponse("Image uploaded successfully: " +
                        file.getOriginalFilename()));
    }

    @GetMapping(path = {"/get/image/info/{customerID}"})
    public Image getImageDetails(@PathVariable int customerID) throws IOException {

        final Optional<Image> dbImage = imageRepository.findByCustomerId(customerID);

        return Image.builder()
                .customer(dbImage.get().getCustomer())
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .image(ImageUtility.decompressImage(dbImage.get().getImage())).build();
    }



    @GetMapping(path = {"/get/image/{customerID}"})
    public ResponseEntity<byte[]> getImage(@PathVariable int customerID) throws IOException {

        final Optional<Image> dbImage = imageRepository.findByCustomerId(customerID);

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(dbImage.get().getType()))
                .body(ImageUtility.decompressImage(dbImage.get().getImage()));
    }

    @Transactional
    @DeleteMapping("/image/{customerId}")
    public void deleteImage(@PathVariable int customerId) throws ResourceNotFoundException{
        imageRepository.deleteByCustomerId(customerId);
    }


}
