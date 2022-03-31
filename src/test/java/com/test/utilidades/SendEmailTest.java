package com.test.utilidades;

import com.repuestos.finnegans.entity.Solicitud;
import com.repuestos.finnegans.utilidades.SendEmail;
import org.junit.jupiter.api.Test;

class SendEmailTest {
    @Test
    void sendEmail() {
        SendEmail sendEmail=new SendEmail();
        try {

            //sendEmail.send(null,"andresfernandez103@gmail.com","andresoicsa@gmail.com","");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}