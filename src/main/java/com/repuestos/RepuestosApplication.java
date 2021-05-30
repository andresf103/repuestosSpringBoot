package com.repuestos;

import com.repuestos.entidades.Maquina;
import com.repuestos.servicio.MaquinaService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@SpringBootApplication
@Controller
public class RepuestosApplication {
    @Autowired
    private MaquinaService maquinaService;

    @GetMapping("/")
    String home(Model model){
    var maquinas = maquinaService.listarMaquinas();
        model.addAttribute("maquinas", maquinas);
        return "home";
    }

    @GetMapping("/maquina/agregar")
    public String agregarMaquinas(Maquina maquina){
    return "modificar";
    }
    
    @PostMapping("/maquina/agregar")
    public String agregarMaquina(@Valid Maquina maquina,Errors errores){
        if(errores.hasErrors()){
            return "modificar";
        }
        maquinaService.guardar(maquina);
        return "redirect:/";
        
    }
    
    
        @GetMapping("/maquina/editar/{idMaquina}")
    public String editarMaquina(Maquina maquina, Model model){
        maquina=maquinaService.encontrarMaquina(maquina);
        model.addAttribute("maquina", maquina);
        return "modificar";
    }
    
        @GetMapping("/maquina/eliminar")
    public String eliminarMaquina(Maquina maquina){
        maquinaService.eliminar(maquina);
        return "redirect:/";
    }
    
    @GetMapping("/repuesto")
    String repuesto(Model model) {
        return "repuesto";
    }
    
    @GetMapping("/error")
            /*el nombre del metodo tiene que tener el mismo nombre que la vista eso parece*/
    String error(Model model) {
        return "error";
    }

    public static void main(String[] args) {
        SpringApplication.run(RepuestosApplication.class, args);
    }

}
