package com.example.sendMail.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class Email {
    public String recipient;
    private String subject;
    private String message;
    private MultipartFile attatchment;
}
