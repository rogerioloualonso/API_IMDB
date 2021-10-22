package br.com.letscode.java.aplicationimdb.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class teste {

    @GetMapping("/teste")
    public String hello() {
        return "Teste!";
    }

}
