package com.example.sendMail.service;

import com.example.sendMail.model.Email;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    public String sendEmail(Email email) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("Javier Gonzalez<".concat(sender).concat(">"));
            mailMessage.setTo(email.getRecipient());
            mailMessage.setSubject(email.getSubject());
            mailMessage.setText(email.getMessage());

            javaMailSender.send(mailMessage);
            return "Email sent successfully";
        } catch (Exception e) {
            return "Email sending error!";
        }
    }

    public String sendMailWithAttatchment(Email email) throws MessagingException {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom("Javier Gonzalez<".concat(sender).concat(">"));
            mimeMessageHelper.setTo(email.getRecipient());
            mimeMessageHelper.setSubject(email.getSubject());
            mimeMessageHelper.setText(email.getMessage());

            mimeMessageHelper.addAttachment(email.getAttatchment().getOriginalFilename(),
                    email.getAttatchment());

            javaMailSender.send(mimeMessage);
            return "Mail sent successfully";
        } catch (Exception e) {
            return "Email sending error!";
        }
    }

}
