package br.com.fiap.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("imoveis")
public class ImovelController {

    @GetMapping
    public String dizerOla(){
        return "Churros de chocolate";
    }

}
