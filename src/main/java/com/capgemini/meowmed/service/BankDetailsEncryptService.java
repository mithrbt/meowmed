package com.capgemini.meowmed.service;

import com.capgemini.meowmed.model.BankDetails;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.keygen.KeyGenerators;

import java.io.*;

@Configuration
@EnableWebSecurity
public class BankDetailsEncryptService {

    private static final String PASSWORD = "MeowMed+";
    private static final String SALT = KeyGenerators.string().generateKey();

    public byte[] encryptBankDetails(BankDetails bankDetails) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(bankDetails);
        objectOutputStream.close();

        BytesEncryptor encryptor = Encryptors.standard(PASSWORD, SALT);
        return encryptor.encrypt(byteArrayOutputStream.toByteArray());
    }

    public <T> T decryptBankDetails(byte[] encryptedBankDetails, Class<T> objectType) throws IOException, ClassNotFoundException {
        BytesEncryptor decryptor = Encryptors.standard(PASSWORD, SALT);
        byte[] decryptedBytes = decryptor.decrypt(encryptedBankDetails);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decryptedBytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Object decryptedObject = objectInputStream.readObject();
        objectInputStream.close();

        return objectType.cast(decryptedObject);
    }

    public byte[] convertBankDetailsToByteArray(BankDetails bankDetails) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(bankDetails);
        objectOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    public BankDetails convertByteArrayToBankDetails(byte[] byteArray) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Object object = objectInputStream.readObject();
        objectInputStream.close();
        return (BankDetails) object;
    }
}
