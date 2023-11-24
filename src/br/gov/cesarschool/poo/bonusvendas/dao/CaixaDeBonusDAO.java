package br.gov.cesarschool.poo.bonusvendas.dao;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;
import br.gov.cesarschool.poo.bonusvendas.negocio.geral.StringUtil;

import java.io.Serializable;

public class CaixaDeBonusDAO {

    private DAOGenerico dao;

    public CaixaDeBonusDAO() {
        this.dao = new DAOGenerico(CaixaDeBonus.class);
    }

    public boolean incluir(CaixaDeBonus caixa) {
        return incluir(caixa);
    }

    public boolean alterar(CaixaDeBonus caixa) {
        return alterar(caixa);
    }

    public CaixaDeBonus buscar(long numero) {
        return (CaixaDeBonus) dao.buscar(String.valueOf(numero));
    }

    public CaixaDeBonus[] buscarTodos() {
        Registro[] registros = dao.buscarTodos();
        CaixaDeBonus[] caixas = new CaixaDeBonus[registros.length];
        for (int i = 0; i < registros.length; i++) {
            caixas[i] = (CaixaDeBonus) registros[i];
        }
        return caixas;
    }

}
