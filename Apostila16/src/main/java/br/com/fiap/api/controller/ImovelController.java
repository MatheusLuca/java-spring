package br.com.fiap.api.controller;

import br.com.fiap.api.dao.ImovelDao;
import br.com.fiap.api.model.Imovel;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.sql.SQLException;

@RestController
@RequestMapping("imoveis")

public class ImovelController {

    //nao usa notação autowire
    // Injecao de dependencia serve para podermos usar  objeto na classe
    // permite que uma classe receba de fora os objetos que ela precisa usar
    private ImovelDao dao;

    public ImovelController(ImovelDao dao){
        this.dao = dao;
    }

    @GetMapping
    public String dizerOla(){
        return "Churros de chocolate";
    }

    @PostMapping
    public ResponseEntity<Imovel>adicionar(@RequestBody Imovel imovel, UriComponentsBuilder uriBuilder) throws SQLException {

        dao.cadastrar(imovel);

        URI uri = uriBuilder.path("/imoveis/{id}").buildAndExpand(imovel.getCodigo()).toUri();

        return ResponseEntity.created(uri).body(imovel);



    }



}
