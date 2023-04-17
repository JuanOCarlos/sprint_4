package br.com.edusync.desafio.services;

import br.com.edusync.desafio.models.FuncionarioModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FuncionarioService {

    //cria uma lista de funcionario
    List<FuncionarioModel> funcionario = new ArrayList<FuncionarioModel>();

    //métpdp que lista todos os funcionarios
    public List<FuncionarioModel> listarTodos(){
        return funcionario;
    }

    //método que adiciona um funcionario
    public void adicionar(FuncionarioModel adicionaFuncionario){
        funcionario.add(adicionaFuncionario);
    }

    //método que busca um funcionario
    public FuncionarioModel buscarPorCpf(String cpf){
        return funcionario.stream().filter(funcionarioModel -> cpf.equals(funcionarioModel.getCpf())).findFirst().orElseThrow();
    }

    //método que atualiza um funcionario
    public void update(String cpf, FuncionarioModel funcionario){
        remover(cpf);
        adicionar(funcionario);
    }

    //método que remove um funcionario
    public void remover(String cpf){
        FuncionarioModel pesquisaFuncionario = buscarPorCpf(cpf);
        if (pesquisaFuncionario != null){
            funcionario.remove(pesquisaFuncionario);
        }

    }
}
