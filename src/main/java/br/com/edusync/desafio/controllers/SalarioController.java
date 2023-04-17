package br.com.edusync.desafio.controllers;

import br.com.edusync.desafio.models.SalarioModel;
import br.com.edusync.desafio.services.SalarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/salario")
public class SalarioController {

    @Autowired
    private SalarioService service;

    @PostMapping("/novo")
    @Operation(summary = "cria o salario", description = "Método que acessa o método adicionar do service e cria o salario")
    @ApiResponse(
            responseCode = "201",
            description = "Salario criado com sucesso!"
    )
    public ResponseEntity novoSalario(@RequestBody SalarioModel salario){
        service.adicionar(salario);
        return new ResponseEntity(salario, HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    @Operation(summary = "Lista todos os salarios", description = "Método que acessa o método listarTudo do service e exibe")
    @ApiResponse(
            responseCode = "200",
            description = "sucesso"
    )
    public ResponseEntity listarTudo(){
        return new ResponseEntity(service.listarTudo(), HttpStatus.OK);
    }

    @GetMapping("/listar/{codigo}")
    @Operation(summary = "Lista o salario pelo codigo", description = "Método que acessa o método buscarPorCodigo do service e exibe")
    @ApiResponse(
            responseCode = "200",
            description = "sucesso"
    )
    public ResponseEntity listarPorCodigo(@PathVariable Integer codigo){
        return new ResponseEntity(service.buscarPorCodigo(codigo), HttpStatus.OK);
    }

    @PutMapping("/editar/{codigo}")
    @Operation(summary = "Edita o salario pelo codigo", description = "Método que acessa o método update do service e atualiza")
    @ApiResponse(
            responseCode = "200",
            description = "sucesso"
    )
    public ResponseEntity editar(@PathVariable Integer codigo,@RequestBody SalarioModel salario){
        try {
            service.update(codigo, salario);
        }catch (NoSuchElementException e) {
            return new ResponseEntity("Codigo Inválido", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(salario, HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{codigo}")
    @Operation(summary = "Remove o salario pelo codigo", description = "Método que acessa o método remover do service e deleta")
    @ApiResponse(
            responseCode = "200",
            description = "sucesso"
    )
    public ResponseEntity remover(@PathVariable Integer codigo){
        try {
            service.remover(codigo);
        }catch (NoSuchElementException e) {

            return new ResponseEntity("Codigo Inválido!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Salario " + codigo + " removido com sucesso!", HttpStatus.OK);
    }
}
