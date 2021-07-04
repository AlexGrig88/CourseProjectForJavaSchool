package com.alexgrig.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    @Autowired
    JavaMailSender javaMailSender;

    public void sendSimpleEmail(String toEmail, String body, String topic) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("grigxia86@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(topic);

        javaMailSender.send(message);

        System.out.println("Mail send...");
    }

}
