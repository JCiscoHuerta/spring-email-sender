package com.example.sendMail.controller;

import com.example.sendMail.model.Email;
import com.example.sendMail.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-mail")
    public String sendMail(@RequestBody Email email) {
        return emailService.sendEmail(email);
    }

    @PostMapping("/send-mail-attatchment")
    public String sendMailWithAttatchment(@ModelAttribute Email email) throws MessagingException {
        return emailService.sendMailWithAttatchment(email);
    }

}
