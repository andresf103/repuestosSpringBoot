package com.repuestos.finnegans.utilidades;

import com.repuestos.finnegans.entity.Solicitud;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Properties;

@Slf4j
public class SendEmail {

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("andresoicsa@gmail.com");
        mailSender.setPassword("condor931");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    // usuarioFinnegan proveedor y file
    public void send(Solicitud solicitud, String emailProveedor,String emailUsuarioFinnegan, String pathAdjunto) throws Exception {

        JavaMailSender emailSender = getJavaMailSender();

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("noreply@oicsa.com");
        helper.setTo(emailUsuarioFinnegan);
        helper.setCc(emailProveedor);
        helper.setSubject("Orden de compra de la solicitud " + solicitud.getNumeroInterno());
        helper.setText("Hola,\n Adjunto orden de compra, correspondiente a la solicitud Nº" + solicitud.getNumeroInterno() + ", pongo en copia al encargado "+ solicitud.getNombreUsuarioAlta() +" para coordinar la compra de la misma.\n\n Saludos cordiales.");

        FileSystemResource file = new FileSystemResource(new File(pathAdjunto));
        helper.addAttachment("orden "+file.getFilename(), file);
        emailSender.send(message);
        log.info("Done");

    }

}