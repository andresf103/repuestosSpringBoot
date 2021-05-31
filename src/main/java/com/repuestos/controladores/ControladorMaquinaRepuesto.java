
package com.repuestos.controladores;

import com.repuestos.entidades.MaquinaRepuesto;
import com.repuestos.servicio.MaquinaRepuestoService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ControladorMaquinaRepuesto {
        @Autowired
    private MaquinaRepuestoService maquinaRepuestoService;

    @GetMapping("/maquinaRepuesto/")
    String homeMaquinaRepuesto(Model model) {
        var repuestos =maquinaRepuestoService.listarMaquinaRepuestos();
        model.addAttribute("repuestos", repuestos);
        return "home";
    }

    @GetMapping("/maquinaRepuesto/agregar")
    public String agregarMaquinaRepuestos(MaquinaRepuesto maquinaRepuesto) {
        return "modificar";
    }

    @PostMapping("/maquinaRepuesto/agregar")
    public String agregarMaquinaRepuesto(@Valid MaquinaRepuesto maquinaRepuesto, Errors errores) {
        if (errores.hasErrors()) {
            return "modificar";
        }
       maquinaRepuestoService.guardar(maquinaRepuesto);
        return "redirect:/maquinaRepuesto/";

    }

    @GetMapping("/maquinaRepuesto/editar/{idRepuesto}")
    public String editarMaquinaRepuesto(MaquinaRepuesto maquinaRepuesto, Model model) {
        maquinaRepuesto =maquinaRepuestoService.encontrarMaquinaRepuesto(maquinaRepuesto);
        model.addAttribute("maquinaRepuesto", maquinaRepuesto);
        return "modificar";
    }

    @GetMapping("/maquinaRepuesto/eliminar")
    public String eliminarMaquinaRepuesto(MaquinaRepuesto maquinaRepuesto) {
       maquinaRepuestoService.eliminar(maquinaRepuesto);
        return "redirect:/maquinaRepuesto/";
    }
}
