package com.repuestos.utilidades;

import com.repuestos.finnegans.utilidades.SendEmail;
import org.junit.jupiter.api.Test;

class SendEmailTest {
    @Test
    void sendEmail() {
        SendEmail sendEmail=new SendEmail();
        try {
            sendEmail.send("andresfernandez103@gmail.com","andresoicsa@gmail.com","");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}