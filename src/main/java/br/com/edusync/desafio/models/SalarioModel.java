package br.com.edusync.desafio.models;

import lombok.Data;
// modeldo de salario
@Data
public class SalarioModel {

    private Integer codigo;
    private FuncionarioModel funcionario;
    private Integer salario;
}
