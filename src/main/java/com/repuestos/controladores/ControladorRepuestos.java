package com.repuestos.controladores;

import com.repuestos.entidades.Repuesto;
import com.repuestos.servicio.RepuestoService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControladorRepuestos {

    @Autowired
    private RepuestoService repuestoService;

    @GetMapping("/repuesto/")
    String homeRepuesto(Model model) {
        var repuestos = repuestoService.listarRepuestos();
        model.addAttribute("repuestos", repuestos);
        return "home";
    }

    @GetMapping("/repuesto/agregar")
    public String agregarRepuestos(Repuesto repuesto) {
        return "modificar";
    }

    @PostMapping("/repuesto/agregar")
    public String agregarRepuesto(@Valid Repuesto repuesto, Errors errores) {
        if (errores.hasErrors()) {
            return "modificar";
        }
        repuestoService.guardar(repuesto);
        return "redirect:/repuesto/";

    }

    @GetMapping("/repuesto/editar/{idRepuesto}")
    public String editarRepuesto(Repuesto repuesto, Model model) {
        repuesto = repuestoService.encontrarRepuesto(repuesto);
        model.addAttribute("repuesto", repuesto);
        return "modificar";
    }

    @GetMapping("/repuesto/eliminar")
    public String eliminarRepuesto(Repuesto repuesto) {
        repuestoService.eliminar(repuesto);
        return "redirect:/repuesto/";
    }
}
