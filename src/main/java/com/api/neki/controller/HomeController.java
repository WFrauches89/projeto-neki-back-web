package com.api.neki.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    @PreAuthorize("hasAuthority('NORMAL')")
    public String getHome(){
        return "Olá, Bem vindo a Home da Neki.";
    }

}
