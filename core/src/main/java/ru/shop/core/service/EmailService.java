package ru.shop.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private JavaMailSender mailSender;

    @Autowired
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendEmail(String toEmail, String confirmCode, String URL){

        SimpleMailMessage registrationEmail = new SimpleMailMessage();
        registrationEmail.setTo(toEmail);
        registrationEmail.setSubject("Registration Confirmation");
        registrationEmail.setText("To confirm your e-mail address, please click the link below:\n"
                + URL + "/confirm?token=" + confirmCode);
        registrationEmail.setFrom("noreply@shop.com");

        mailSender.send(registrationEmail);
    }

}
