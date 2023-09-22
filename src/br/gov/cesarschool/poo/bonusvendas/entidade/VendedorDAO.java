package br.gov.cesarschool.poo.bonusvendas.entidade;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;

import java.io.Serializable;

public class VendedorDAO {
    private static final String BRANCO = "";
    private CadastroObjetos cadastro = new CadastroObjetos(Vendedor.class);

    public boolean incluir(Vendedor vend) {
        Vendedor vendBusca = buscar(vend.getCpf());

        if (vendBusca != null) {
            return false;
        } else {
            cadastro.incluir(vend, BRANCO + vend.getCpf());
            return true;
        }
    }

    public boolean alterar(Vendedor vend) {
        Vendedor vendBusca = buscar(vend.getCpf());

        if (vendBusca == null) {
            return false;
        } else {
            cadastro.alterar(vend, BRANCO + vend.getCpf());
            return true;
        }
    }

    public boolean excluir(Vendedor vend) {
        Vendedor vendBusca = buscar(vend.getCpf());

        if (vendBusca == null) {
            return false;
        } else {
            cadastro.excluir(BRANCO + vend.getCpf());
            return true;
        }
    }

    public Vendedor buscar(String cpf) {
        return (Vendedor)cadastro.buscar(BRANCO + cpf);
    }

    public Vendedor[] buscarTodos() {
        Serializable[] rets = cadastro.buscarTodos(Vendedor.class);
        Vendedor[] vends = new Vendedor[rets.length];
        for (int i = 0; i < rets.length; i++) {
            vends[i] = (Vendedor)rets[i];
        }
        return vends;
    }


}
