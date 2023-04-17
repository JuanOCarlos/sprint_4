package br.com.edusync.desafio.services;

import br.com.edusync.desafio.models.SalarioModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalarioService {

    //cria uma lista de salario
    List<SalarioModel> salario = new ArrayList<SalarioModel>();

    //método que lista todos os salarios
    public List<SalarioModel> listarTudo(){
        return salario;
    }
    //método que adicionar um salario
    public void adicionar(SalarioModel adicionaSalario){
        salario.add(adicionaSalario);
    }

    //método que busca um salario
    public SalarioModel buscarPorCodigo(Integer codigo){
        return salario.stream().filter(salarioModel -> codigo.equals(salarioModel.getCodigo())).findFirst().orElseThrow();
    }
    //método que atualiza um salario
    public void update(Integer codigo, SalarioModel salario){
        remover(codigo);
        adicionar(salario);

    }

    //método que deleta um salario
    public void remover(Integer codigo){
        SalarioModel pesquisaSalario = buscarPorCodigo(codigo);
        if (pesquisaSalario != null){
            salario.remove(pesquisaSalario);
        }
    }


}
