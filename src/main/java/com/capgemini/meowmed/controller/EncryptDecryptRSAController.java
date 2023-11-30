package com.capgemini.meowmed.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EncryptDecryptRSAController {

    @Autowired
    EncryptDecryptService encryptDecryptService;

    @GetMapping("/createKeys")
    public void createPrivatePublicKey(){
        encryptDecryptService.createKeys();
    }

    @PostMapping("/encrypt")
    public String encryptString(@RequestBody String plainString){
        return encryptDecryptService.encryptString(plainString);
    }

    @PostMapping("/decrypt")
    public String decryptString(@RequestBody String encryptString){
        return encryptDecryptService.decryptString(encryptString);
    }

}
