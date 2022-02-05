package com.repuestos.finnegans.service;

import com.repuestos.finnegans.dto.SolicitudDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import com.repuestos.RepuestosApplication;

import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RepuestosApplication.class})
public class SolicitudServiceTest {

    @Autowired
    private SolicitudRestService solicitudService;
    @Autowired
    private OrdenRestService ordenRestService;

    public SolicitudServiceTest() {
    }

    @Test
    public void testFindAllFromToday() {

        try {
            for (int i = 1; i < 10 ; i++) {
            ordenRestService.findAllFromYear(i);
            }
            //solicitudService.findAllFromToday();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
