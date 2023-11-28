package com.capgemini.meowmed.service;

import com.capgemini.meowmed.model.Cat;
import com.capgemini.meowmed.model.Contract;
import com.capgemini.meowmed.model.Customer;
import com.capgemini.meowmed.repository.CatRepository;
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
import java.util.Optional;

@Service
public class EmailService {

    @Autowired
    private final JavaMailSender javaMailSender;

    @Autowired
    private CatRepository catRepository;


    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public void sendEmailWithAttachment(String to, Customer customer, Cat cat) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);


        String emailText = "<div style='text-align: center;'>" +
                "<p>Sehr geehrte/r " + customer.getFirstname() + " " + customer.getLastname() + ",</p>" +
                "<p>wir freuen uns, Ihnen mitteilen zu dürfen, dass Ihr Vertrag für ihre Katze " + cat.getName() + " bei <strong>MeowMed+</strong> erfolgreich abgeschlossen wurde. </p>" +
                "<p>Herzlich willkommen in unserer Gemeinschaft von Katzenliebhabern, die den Schutz und das Wohlbefinden Ihrer Tiere an erster Stelle sehen.</p>" +
                "<p>Wir freuen uns darauf, Sie und Ihre Katzen auf Ihrem Versicherungsweg zu begleiten.</p>" +
                "<p>Vielen Dank für Ihr Vertrauen in <strong>MeowMed+</strong>.</p>" +
                "<p>Mit freundlichen Grüßen,<br>Ihr MeowMed+ Team!</p>" +
                "<div style='text-align: center;'>" +
                "<img src='cid:attachment' alt='Für Ihr Vertrauen' />" + // "cid:attachment" refers to the content ID of the embedded image
                "</div>" +
                "</div>";


        mimeMessageHelper.setFrom("meowmed999@gmail.com");
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setText(emailText, true);
        mimeMessageHelper.setSubject("MeowMed+ - Vertragsabschluss für " + cat.getName());

        FileSystemResource image = new FileSystemResource(new File("/Users/mithrarabet/Documents/Ostfalia/Informatik/4. Semester/Teamprojekt/für ihr Vertrauen.png"));

        mimeMessageHelper.addInline("attachment", image);

        javaMailSender.send(mimeMessage);

        System.out.println("Mail sent to " + to);

    }
}
