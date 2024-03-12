package org.iesvdm.clubjava.controller;


import org.iesvdm.clubjava.domain.User;
import org.iesvdm.clubjava.service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/v1/api/mail")
public class MailSenderController {

    @Autowired
    private MailSenderService mailSenderService;

    @PostMapping("/send-mail")
    public String sendMail(@RequestBody User user) {

        this.mailSenderService.notificarPorEmail(user, "Hola " + user.getUsername() + ", bienvenido al Club de Java!");
        return "Mensaje Enviado!";

    }

}
