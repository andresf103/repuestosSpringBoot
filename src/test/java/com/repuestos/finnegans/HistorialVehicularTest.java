package com.repuestos.finnegans;

import com.repuestos.RepuestosApplication;
import com.repuestos.finnegans.service.OrdenRestService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URISyntaxException;

@Slf4j
@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RepuestosApplication.class})
class HistorialVehicularTest {

    @Autowired
    HistorialVehicular historialVehicular;
    @Autowired
    OrdenRestService ordenRestService;

    @Test
    void rutina() {
        historialVehicular.rutina();
    }

    @Test
    void historialVehicularParticular(){
       /* try {
            for (int i = 1; i < 3 ; i++) {
                ordenRestService.findAllFromYear(i);
            }
            //solicitudService.findAllFromToday();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }*/
        historialVehicular.historialVehicularPorPatente("IVX787");
    }
}