package br.com.edusync.desafio.controllers;


import br.com.edusync.desafio.models.FuncionarioModel;
import br.com.edusync.desafio.services.EmpresaService;
import br.com.edusync.desafio.services.FuncionarioService;
import br.com.edusync.desafio.services.SalarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;
    @Autowired
    private EmpresaService empresaService;
    @Autowired
    private SalarioService salarioService;

    // objetivo: ligar funcionario a empresa
    // primeiro passo: buscar a empresa pelo cnpj
    // segundo passo: colocar a empresa dentro de funcionario
    // usar o seter funcionario para adicionar uma empresa a ele
    // cadastrar funcionario
    //
    @PostMapping(value = "/novo")
    @Operation(summary = "Cria um funcionario", description = "Método que acessa o método adicionar do service e cria um funcionario")
    @ApiResponse(
            responseCode = "201",
            description = "Funcionario criado com sucesso!"
    )
    public ResponseEntity novoFuncionario(@RequestParam String cnpj,
                                          @RequestParam Integer codigo,
                                          @RequestBody FuncionarioModel funcionario){
        funcionario.setEmpresa(empresaService.buscarPorCnpj(cnpj));
        funcionario.setSalario(salarioService.buscarPorCodigo(codigo));

        service.adicionar(funcionario);
        return new ResponseEntity(cnpj + " " + codigo, HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    @Operation(summary = "Lista todos os funcionarios", description = "Método que acessa o método listarTodos do service e exibe")
    @ApiResponse(
            responseCode = "200",
            description = "sucesso"
    )
    public ResponseEntity listarTudo(){
        return new ResponseEntity(service.listarTodos(), HttpStatus.OK);
    }

    @GetMapping("/listar/{cpf}")
    @Operation(summary = "Lista o funcionario pelo cpf", description = "Método que acessa o método buscarPorCpf do service e exibe o funcionario")
    @ApiResponse(
            responseCode = "200",
            description = "sucesso"
    )
    public ResponseEntity listarPorCpf(@PathVariable String cpf){
        return new ResponseEntity(service.buscarPorCpf(cpf), HttpStatus.OK);
    }

    @PutMapping("/editar/{cpf}")
    @Operation(summary = "edita o funcionario pelo cpf", description = "Método que acessa o método update do service e atualiza o funcionario")
    @ApiResponse(
            responseCode = "200",
            description = "sucesso"
    )
    public ResponseEntity editar(@PathVariable String cpf, @RequestBody FuncionarioModel funcionario){
        try {
            service.update(cpf, funcionario);
        }catch (NoSuchElementException e){
            return new ResponseEntity("CPF Inválido!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(funcionario, HttpStatus.OK);
    }

    @DeleteMapping("/remover/{cpf}")
    @Operation(summary = "remove o funcionario pelo cpf", description = "Método que acessa o método remover do service e deleta o funcionario")
    @ApiResponse(
            responseCode = "200",
            description = "sucesso"
    )
    public ResponseEntity remover(@PathVariable String cpf){
        try {
            service.remover(cpf);
        }catch (NoSuchElementException e) {

            return new ResponseEntity("CPF Inválido", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(cpf + " excluído com Sucesso!", HttpStatus.OK);
    }
}
