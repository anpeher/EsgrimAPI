package com.example.esgrimAPI.Servicio.ServicioNotificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ServicioEnvioEmail {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String emailReceptor, String titulo, String cuerpo) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("anpeher12@gmail.com");
        message.setTo(emailReceptor);
        message.setSubject(titulo);
        message.setText(cuerpo);
        mailSender.send(message);
        System.out.println("Mail Send to "+ emailReceptor);
    }

    public void sendMultipleEmails(List<String> emailReceptores, String titulo, String cuerpo){

        for(String emailReceptor: emailReceptores){
            sendSimpleEmail(emailReceptor, titulo, cuerpo);
        }
    }


}

