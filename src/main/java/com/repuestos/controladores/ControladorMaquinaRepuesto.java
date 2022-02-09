package com.repuestos.controladores;

import com.repuestos.entidades.Maquina;
import com.repuestos.entidades.MaquinaRepuesto;
import com.repuestos.servicio.MaquinaRepuestoService;
import com.repuestos.servicio.MaquinaService;
import com.repuestos.servicio.RepuestoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class ControladorMaquinaRepuesto {

    @Autowired
    private MaquinaRepuestoService maquinaRepuestoService;
    @Autowired
    private MaquinaService maquinaService;
    @Autowired
    private RepuestoService repuestoService;

    @GetMapping("/maquinaRepuesto/{idMaquina}")
    String homeMaquinaRepuesto(Maquina maquina, Model model) {
        var maquinaRepuestos = maquinaRepuestoService.listarPorMaquina(maquina);
        var vehiculo = maquinaService.encontrarMaquina(maquina);
        model.addAttribute("vehiculo", vehiculo);
        model.addAttribute("maquinaRepuestos", maquinaRepuestos);
        return "homeMaquinaRepuesto";
    }

    @GetMapping("/maquinaRepuesto/{idMaquina}/{search}")
    String buscarMaquina(Maquina maquina, @PathVariable("search") String search, Model model) {
        var maquinaRepuestos = maquinaRepuestoService.listarPorMaquina(maquina);
        var vehiculo = maquinaService.encontrarMaquina(maquina);
        maquinaRepuestos = maquinaRepuestos.stream().filter(maquinaRepuesto -> {
            String comparacion = maquinaRepuesto.getDescripcion().toUpperCase()
                    + maquinaRepuesto.getRepuesto().getDescripcion().toUpperCase()
                    + maquinaRepuesto.getMarca().toUpperCase();
            String searchU = search.toUpperCase();
            return comparacion.contains(searchU);
        }).collect(Collectors.toList());
        model.addAttribute("vehiculo", vehiculo);
        model.addAttribute("maquinaRepuestos", maquinaRepuestos);
        return "homeMaquinaRepuesto";
    }

    @GetMapping("/maquinaRepuesto/")
    String homeMaquinaRepuestos(Maquina maquina) {
        return "homeMaquinaRepuesto";
    }

    @GetMapping("/maquinaRepuesto/agregar")
    public String agregarMaquinaRepuestos(MaquinaRepuesto maquinaRepuesto, Model model) {
        var maquinas = maquinaService.listarMaquinas();
        var repuestos = repuestoService.listarRepuestos();
        model.addAttribute("repuestos", repuestos);
        model.addAttribute("maquinas", maquinas);
        return "modificarMaquinasRepuesto";
    }
    
        @GetMapping("/maquinaRepuesto/agregar/maquina/{maquina.idMaquina}")
    public String agregarMaquinaRepuestosByPatente(MaquinaRepuesto maquinaRepuesto, Model model) {
        var maquinas = maquinaService.listarMaquinas();
        log.info("idMaquina: "+maquinaRepuesto.getMaquina().getIdMaquina());
        var repuestos = repuestoService.listarRepuestos();
        model.addAttribute("repuestos", repuestos);
        model.addAttribute("maquinas", maquinas);
        if(maquinaRepuesto.getMaquina().getIdMaquina()!=null){
            var vehiculo=maquinaService.encontrarMaquina(maquinaRepuesto.getMaquina());
            model.addAttribute("vehiculo",vehiculo);
        }
        return "modificarMaquinasRepuesto";
    }

    @PostMapping("/maquinaRepuesto/agregar")
    public String agregarMaquinaRepuesto(@Valid MaquinaRepuesto maquinaRepuesto, Errors errores) {
        if (errores.hasErrors()) {
            return "modificarMaquinasRepuesto";
        }
        maquinaRepuestoService.guardar(maquinaRepuesto);
        return "redirect:/maquinaRepuesto/"+maquinaRepuesto.getMaquina().getIdMaquina();

    }

    @GetMapping("/maquinaRepuesto/editar/{idMaquinaRepuesto}")
    public String editarMaquinaRepuesto(MaquinaRepuesto maquinaRepuesto, Model model) {
        maquinaRepuesto = maquinaRepuestoService.encontrarMaquinaRepuesto(maquinaRepuesto);
        var maquinas = maquinaService.listarMaquinas();
        var repuestos = repuestoService.listarRepuestos();
        model.addAttribute("repuestos", repuestos);
        model.addAttribute("maquinas", maquinas);
        model.addAttribute("maquinaRepuesto", maquinaRepuesto);
        return "modificarMaquinasRepuesto";
    }

    @GetMapping("/maquinaRepuesto/eliminar")
    public String eliminarMaquinaRepuesto(MaquinaRepuesto maquinaRepuesto) {
        maquinaRepuesto=maquinaRepuestoService.encontrarMaquinaRepuesto(maquinaRepuesto);
        Long idMaquina=maquinaRepuesto.getMaquina().getIdMaquina();
        maquinaRepuestoService.eliminar(maquinaRepuesto);
        return "redirect:/maquinaRepuesto/"+ idMaquina;
    }
}
