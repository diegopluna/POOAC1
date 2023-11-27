package br.gov.cesarschool.poo.bonusvendas.dao;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;

import java.io.Serializable;

public class DAOGenerico {
    private CadastroObjetos cadastro;

    public DAOGenerico(Class<?> tipo) {
        this.cadastro = new CadastroObjetos(tipo);
    }

    public boolean incluir(Registro registro) {
        Registro registroBusca = buscar(registro.getIdUnico());
        if (registroBusca != null) {
            return false;
        } else {
            cadastro.incluir(registro,registro.getIdUnico());
            return true;
        }
    }

    public boolean alterar(Registro registro) {
        Registro registroBusca = buscar(registro.getIdUnico());
        if (registroBusca == null) {
            return false;
        } else {
          cadastro.alterar(registro, registro.getIdUnico());
          return true;
        }
    }

    public Registro buscar(String id) {
        return (Registro)cadastro.buscar(id);
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
