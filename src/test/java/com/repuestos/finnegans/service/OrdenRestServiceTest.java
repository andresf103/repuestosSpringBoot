package com.repuestos.finnegans.service;

import com.repuestos.RepuestosApplication;
import com.repuestos.finnegans.dto.OrdenDTO;
import com.repuestos.finnegans.utilidades.DownloadPDFOrder;
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
import java.util.stream.Collectors;

@Slf4j
@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RepuestosApplication.class})
class OrdenRestServiceTest {

    @Autowired
    private OrdenRestService ordenRestService;
    @Autowired
    private OrdenEntityService ordenEntityService;

    @Test
    void findAllFromToday() {
        List<OrdenDTO> ordenes= null;
        try {
            ordenes = ordenRestService.findAllFromToday();
            DownloadPDFOrder pdfs=new DownloadPDFOrder();
            List<String>ids=ordenRestService.idsOrders();
            log.info(ids.toString());
            pdfs.downloadAllOrders(ordenEntityService.findAll().stream().map(orden -> orden.getTransaccionId().toString()).collect(Collectors.toList()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        log.info(ordenes.toString());
    }
}