package br.com.edusync.desafio.models;

import lombok.Data;

//modelo de funcionario
@Data
public class FuncionarioModel {
    private String nome;
    private String cpf;
    private EmpresaModel empresa;
    private SalarioModel salario;
}
