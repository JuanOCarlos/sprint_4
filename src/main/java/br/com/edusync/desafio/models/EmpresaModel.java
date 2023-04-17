package br.com.edusync.desafio.models;

import lombok.Data;

import java.util.List;

//modelo de empresa
@Data
public class EmpresaModel {

    private String nome;
    private String cnpj;
    private List<FuncionarioModel> funcionarios;
    private String cargos;
    private String local;

}
