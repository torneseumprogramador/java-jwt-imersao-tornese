package br.tornese.imersao.JavaJWT.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.tornese.imersao.JavaJWT.domain.model_views.Home;

@RestController
public class HomeController {
    @GetMapping("/")
    public Home index(){
        return new Home();
    }
}
