package org.iesvdm.clubjava.service;

import org.iesvdm.clubjava.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;


@Service
public class MailSenderService {

    @Value("${spring.mail.username}")
    private String emailSender;

    private JavaMailSender mailSender;

    public MailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(String from, String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    @Async
    public void notificarPorEmail(User user, String mensaje) {

        //Correo en modo texto
        this.send(emailSender,
                user.getEmail(),
                user.getUsername(),
                mensaje
        );
    }
}
