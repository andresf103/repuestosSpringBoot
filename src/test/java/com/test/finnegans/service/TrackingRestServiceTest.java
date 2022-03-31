package com.test.finnegans.service;

import com.repuestos.RepuestosApplication;
import com.repuestos.finnegans.dto.TrackingDTO;
import com.repuestos.finnegans.entity.Tracking;
import com.repuestos.finnegans.service.TrackingEntityService;
import com.repuestos.finnegans.service.TrackingRestService;
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
class TrackingRestServiceTest {

    @Autowired
    private TrackingRestService trackingRestService;
    @Autowired
    private TrackingEntityService trackingEntityService;

    @Test
    void findAllFromToday() {
        List<TrackingDTO> ordenes= null;
        try {
            ordenes = trackingRestService.findAllFromToday();
            DownloadPDFOrder pdfs=new DownloadPDFOrder();
            List<TrackingDTO>ids= trackingRestService.listTracking();
            log.info(ids.toString());
            pdfs.downloadAllOrders(trackingEntityService.findAll().stream().map(Tracking::toDto).collect(Collectors.toList()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        log.info(ordenes.toString());
    }
}