package com.capgemini.meowmed.controller;

import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.SimpleTimeZone;

@Service
public class EncryptDecryptService {

    public static Map<String, Object> map = new HashMap<>();

    public void createKeys(){
        try{
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(1024);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();
            map.put("publicKey", publicKey);
            map.put("privateKey", privateKey);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String encryptString(String plainString){

        try{
            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-512ANDMGF1PADDING");
            PublicKey publicKey = (PublicKey) map.get("publicKey");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encrypt = cipher.doFinal(plainString.getBytes());

            return new String(Base64.getEncoder().encodeToString(encrypt));

        } catch (Exception e){

        }
        return "";
    }

    public String decryptString(String encryptMessage){

        try{
            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-512ANDMGF1PADDING");
            PrivateKey privateKey = (PrivateKey) map.get("privateKey");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decrypt = cipher.doFinal(Base64.getDecoder().decode(encryptMessage));

            return new String(decrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

}
