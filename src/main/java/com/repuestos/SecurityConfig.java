package com.repuestos;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /*para agregar mas usuarios y hacer login en nuestra aplicacion*/
    @Override
    protected void configure(AuthenticationManagerBuilder autorizacion) throws Exception {
        autorizacion.inMemoryAuthentication()
                .withUser("andres")
                .password("{noop}gladiador")
                .roles("ADMIN", "USER")
                .and()
                .withUser("juliana")
                .password("{noop}environment")
                .roles("ADMIN", "USER")
                .and()
                .withUser("usuario")
                .password("{noop}usuario")
                .roles("USER");
    }

    /*para restringir las urls de nuestra aplicacion segun el rol del usuario*/
    @Override
    protected void configure(HttpSecurity httpsecurity) throws Exception {
        httpsecurity.authorizeRequests()
                .antMatchers("/repuesto/agregar/**",
                        "/repuesto/eliminar",
                        "/repuesto/editar/**",
                        "/maquina/eliminar",
                        "/maquina/editar/**",
                        "/maquina/agregar/**")
                .hasRole("ADMIN")
                .antMatchers("/", "/maquina/", "/maquinaRepuesto/**", "/repuesto/", "/repuesto", "/historial/**")
                .hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .exceptionHandling().accessDeniedPage("/errores/403");

    }
}
