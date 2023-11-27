package com.capgemini.meowmed.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Objects;

@Service
public class EmailService {

    @Autowired
    private final JavaMailSender javaMailSender;


    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public void sendEmailWithAttachment(String to) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        String htmlContent = "<p>Hallo,</p>"
                + "<p>Sie haben eine Versicherung bei MeowMed+ abgeschlossen.<br></p>";


        mimeMessageHelper.setFrom("meowmed999@gmail.com");
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setText(htmlContent, true);
        mimeMessageHelper.setSubject("MeowMed+ - Vertragsabschluss");

        FileSystemResource fileSystemResource = new FileSystemResource(new File("/Users/mithrarabet/Documents/Ostfalia/Informatik/4. Semester/Teamprojekt/Bildschirmfoto 2023-11-27 um 15.00.08.png"));

        mimeMessageHelper.addAttachment(Objects.requireNonNull(fileSystemResource.getFilename()), fileSystemResource);

        javaMailSender.send(mimeMessage);

        System.out.println("Mail sent to " + to);

    }
}
