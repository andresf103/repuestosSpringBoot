package com.repuestos.controladores;

import com.repuestos.entidades.Maquina;
import com.repuestos.finnegans.HistorialVehicular;
import com.repuestos.finnegans.entity.Orden;
import com.repuestos.finnegans.entity.WrapperMaquina;
import com.repuestos.servicio.MaquinaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/historial")
public class ControladorHistorialVehicular {

    private final MaquinaService maquinaService;

    @Autowired
    ControladorHistorialVehicular(MaquinaService maquinaService){
        this.maquinaService = maquinaService;
    }

    @GetMapping(value="/{idMaquina}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Map<String, Object>> getHistorial(@PathVariable(value="idMaquina")Long idMaquina){
        Maquina maquina=new Maquina();
        maquina.setIdMaquina(idMaquina);
        maquina=maquinaService.encontrarMaquina(maquina);
        return new ResponseEntity<Map<String, Object>>(WrapperMaquina.toMinimalMap(maquina), HttpStatus.OK);
    }

    @GetMapping("/vehiculo/{idMaquina}")
    String homeMaquinaRepuesto(@PathVariable(value="idMaquina")Long idMaquina, Model model) {
        Maquina maquina=new Maquina();
        maquina.setIdMaquina(idMaquina);
        var vehiculo=maquinaService.encontrarMaquina(maquina);
        model.addAttribute("vehiculo",vehiculo);
        model.addAttribute("ordenes",vehiculo.getOrden().stream().sorted((o1, o2) -> o1.getTransactionId().intValue() - o2.getTransactionId().intValue()).collect(Collectors.toList()));
        return "historial";
    }
}
