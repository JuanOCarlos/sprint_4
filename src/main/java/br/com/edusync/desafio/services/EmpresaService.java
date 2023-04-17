package br.com.edusync.desafio.services;

import br.com.edusync.desafio.models.EmpresaModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;




@Service
public class EmpresaService {

    //lista de empresa
    List<EmpresaModel> empresa = new ArrayList<EmpresaModel>();

    // método que lista as empresas
    public List<EmpresaModel> listarTodos(){
        return empresa;
    }

    //metodo que adiciona uma empresa
    public void adicionar(EmpresaModel adicionarEmpresa){
        empresa.add(adicionarEmpresa);

    }

    // método que busca uma empresa
        public EmpresaModel buscarPorCnpj(String cnpj){
    return empresa.stream().filter(empresaModel -> cnpj.equals(empresaModel.getCnpj())).findFirst().orElseThrow();
}

    //método que atualiza uma empresa
    public void update(String cnpj, EmpresaModel empresa){
    remover(cnpj);
        adicionar(empresa);

}

    //método que apaga uma empresa
    public void remover(String cnpj){
     EmpresaModel pesquisaEmpresa = buscarPorCnpj(cnpj);
     if (pesquisaEmpresa != null) {
         empresa.remove(pesquisaEmpresa);
     }

}
}
