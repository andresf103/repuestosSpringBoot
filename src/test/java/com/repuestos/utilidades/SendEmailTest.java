package com.repuestos.utilidades;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SendEmailTest {
    @Test
    void sendEmail() {
        SendEmail sendEmail=new SendEmail();
        sendEmail.send();
    }
}