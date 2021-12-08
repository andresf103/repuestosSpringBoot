package com.repuestos.utilidades;

import com.repuestos.finnegans.utilidades.SendEmail;
import org.junit.jupiter.api.Test;

class SendEmailTest {
    @Test
    void sendEmail() {
        SendEmail sendEmail=new SendEmail();
        sendEmail.send();
    }
}