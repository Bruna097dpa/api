package br.com.bfs.api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TestController {

    @GetMapping(value = "/teste")
    public String getWelcomeMessage() {
        return "Bem Vindo ao Treinamento de Spring BOOT - NTT Data";
    }

}
