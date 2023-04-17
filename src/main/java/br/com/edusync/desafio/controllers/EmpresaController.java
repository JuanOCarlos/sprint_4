package br.com.edusync.desafio.controllers;


import br.com.edusync.desafio.models.EmpresaModel;

import br.com.edusync.desafio.services.EmpresaService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.models.annotations.OpenAPI30;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
// CRUD - (CREATE, READ(TODOS OU UM UNICO REGISTRO), UPDATE, DELETE)
    /*
     * CREATE = POST = cadastrar, enviar dados
     * READ = GET = quer alguma coisa
     * UPDATE = PUT = atualizar
     * DELETE = DELETE = apagar algo
     * */

    @Autowired
    private EmpresaService service;

    @PostMapping(value = "/novo")
    @Operation(summary = "Cria uma nova empresa", description = "Método que acessa o método adiconar do service e cria uma nova empresa")
    @ApiResponse(
            responseCode = "201",
            description = "Empresa criada com sucesso!"
    )
    public ResponseEntity novaEmpresa(@RequestBody EmpresaModel empresa) {
        service.adicionar(empresa);
        return new ResponseEntity(empresa, HttpStatus.CREATED);
    }

    @GetMapping(value = "/listar")
    @Operation(summary = "Lista todas as empresas", description = "Método que acessa o método listarTodos do service e exibe a lista de empresas.")
    @ApiResponse(
            responseCode = "200",
            description = "sucesso"
    )
    public ResponseEntity listarTodos() {
        return new ResponseEntity(service.listarTodos(), HttpStatus.OK);
    }

    @GetMapping(value = "/listar/{cnpj}")
    @Operation(summary = "Lista uma empresa pelo cnpj", description = "Método que acessa o método buscarPorCnpj do service e exibe a empresa pesquisada pelo cnpj")
    @ApiResponse(
            responseCode = "200",
            description = "sucesso"
    )
    public ResponseEntity listarPorCnpj(@PathVariable String cnpj) {
        return new ResponseEntity(service.buscarPorCnpj(cnpj), HttpStatus.OK);
    }

    @PutMapping(value = "/editar/{cnpj}")
    @Operation(summary = "Edita a empresa através do cnpj ", description = "Método que acessa o método update do service e atualiza a empresa")
    @ApiResponse(
            responseCode = "200",
            description = "sucesso"
    )
    public ResponseEntity editar(@PathVariable String cnpj, @RequestBody EmpresaModel empresa) {
        try {
            service.update(cnpj, empresa);
        }catch (NoSuchElementException e){

            return new ResponseEntity("CNPJ Inválido!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(empresa, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletar/{cnpj}")
    @Operation(summary = "Remove a empresa pelo cnpj", description = "Método que acessa o método remover do service e remove a empresa")
    @ApiResponse(
            responseCode = "200",
            description = "sucesso"
    )
    public ResponseEntity remover(@PathVariable String cnpj) {
        try {
            service.remover(cnpj);
        }catch (NoSuchElementException e){

            return new ResponseEntity("CNPJ Inválido!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Empresa " + cnpj + " removida com sucesso!", HttpStatus.OK);
    }
}
