package br.gov.cesarschool.poo.bonusvendas.dao;

import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;


public class CaixaDeBonusDAO {
    private DAOGenerico dao;

    public CaixaDeBonusDAO() {
        this.dao = new DAOGenerico(CaixaDeBonus.class);
    }

    public boolean incluir(CaixaDeBonus caixaBonus) {
        return dao.incluir(caixaBonus);
    }
    public boolean alterar(CaixaDeBonus caixaBonus) {
        return dao.alterar(caixaBonus);
    }
    public CaixaDeBonus buscar(long codigo) {
        return (CaixaDeBonus) dao.buscar(String.valueOf(codigo));
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
