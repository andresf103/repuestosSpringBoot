package com.test.finnegans;


import com.repuestos.RepuestosApplication;
import com.repuestos.finnegans.SendingEmailWithAttachment;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;



@Slf4j
@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RepuestosApplication.class})
class SendingEmailWithAttachmentTest {

    @Autowired
    private SendingEmailWithAttachment sendingEmailWithAttachment;

    @Test
    void sendEmailTest(){
        sendingEmailWithAttachment.sendEmails();
    }
}