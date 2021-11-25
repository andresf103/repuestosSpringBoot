package com.repuestos.finnegans.service;

import com.repuestos.RepuestosApplication;
import com.repuestos.finnegans.dto.OrdenDTO;
import com.repuestos.finnegans.dto.SolicitudDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RepuestosApplication.class})
class OrdenRestServiceTest {

    @Autowired
    private OrdenRestService ordenRestService;

    @Test
    void findAllFromToday() {
        List<OrdenDTO> socio= null;
        try {
            socio = ordenRestService.findAllFromToday();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        log.info(socio.toString());
    }
}