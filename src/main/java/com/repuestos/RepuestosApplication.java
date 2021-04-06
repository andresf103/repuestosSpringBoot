package com.repuestos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class RepuestosApplication {

@GetMapping("/")
    String home(Model model){
    return "home";
    }
	public static void main(String[] args) {
		SpringApplication.run(RepuestosApplication.class, args);
	}

}
