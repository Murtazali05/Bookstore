package ru.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.shop.service.dto.user.UserDTO;

@Service
public class EmailService {
    private JavaMailSender mailSender;

    @Autowired
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendEmail(UserDTO userDTO, String URL){

        SimpleMailMessage registrationEmail = new SimpleMailMessage();
        registrationEmail.setTo(userDTO.getEmail());
        registrationEmail.setSubject("Registration Confirmation");
        registrationEmail.setText("To confirm your e-mail address, please click the link below:\n"
                + URL + "/confirm?token=" + userDTO.getConfirmCode());
        registrationEmail.setFrom("murtuzali1996@gmail.com");

        mailSender.send(registrationEmail);
    }

}
