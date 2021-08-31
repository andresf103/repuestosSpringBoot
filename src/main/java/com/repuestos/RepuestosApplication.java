package com.repuestos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
@Slf4j
public class RepuestosApplication {

    @GetMapping("/")
    String home(Model model, @AuthenticationPrincipal User user) {
        log.info("ejecutando el controlador Spring MVC");
        log.info("usuario que hizo login:" + user);
        return "redirect:/maquina/";

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
