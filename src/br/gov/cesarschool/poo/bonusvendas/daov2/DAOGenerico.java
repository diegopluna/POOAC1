package br.gov.cesarschool.poo.bonusvendas.daov2;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoJaExistente;
import br.gov.cesarschool.poo.bonusvendas.excecoes.ExcecaoObjetoNaoExistente;

import java.io.Serializable;

public class DAOGenerico {
    private CadastroObjetos cadastro;
    private String nomeEntidade;

    public DAOGenerico(Class<?> tipo, String nomeEntidade) {
        this.cadastro = new CadastroObjetos(tipo);
        this.nomeEntidade = nomeEntidade;
    }

    public void incluir(Registro registro) throws ExcecaoObjetoJaExistente {
        try {
            buscar(registro.getIdUnico());
            throw new ExcecaoObjetoJaExistente(nomeEntidade + " ja existente");
        } catch (ExcecaoObjetoNaoExistente e) {
            cadastro.incluir(registro, registro.getIdUnico());
        }
    }

    public void alterar(Registro registro) throws ExcecaoObjetoNaoExistente {
        buscar(registro.getIdUnico());
        cadastro.alterar(registro, registro.getIdUnico());
    }

    public Registro buscar(String id) throws ExcecaoObjetoNaoExistente {
        Registro registroBusca = (Registro) cadastro.buscar(id);
        if (registroBusca == null) {
            throw new ExcecaoObjetoNaoExistente(nomeEntidade + " nao existente");
        }
        return registroBusca;
    }

    public Registro[] buscarTodos() {
        Serializable[] rets = cadastro.buscarTodos(Registro.class);
        Registro[] registros = new Registro[rets.length];
        for (int i = 0; i < rets.length; i++) {
            registros[i] = (Registro) rets[i];
        }
        return registros;
    }
}
