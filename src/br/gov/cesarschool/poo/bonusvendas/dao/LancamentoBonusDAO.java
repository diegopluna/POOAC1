package br.gov.cesarschool.poo.bonusvendas.dao;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonus;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

public class LancamentoBonusDAO {
    private DAOGenerico dao;

    public LancamentoBonusDAO() {
        this.dao = new DAOGenerico(LancamentoBonus.class);
    }
    public boolean incluir(LancamentoBonus lancamento) {
        return dao.incluir(lancamento);
    }
    public boolean alterar(LancamentoBonus lancamento) {
        return dao.alterar(lancamento);
    }

    public LancamentoBonus buscar(String codigo) {
        return (LancamentoBonus)dao.buscar(codigo);
    }
    public LancamentoBonus[] buscarTodos() {
        Registro[] regs = dao.buscarTodos();
        LancamentoBonus[] lancamentos = new LancamentoBonus[regs.length];
        for (int i = 0; i < regs.length; i++) {
            lancamentos[i] = (LancamentoBonus)regs[i];
        }
        return lancamentos;
    }
}
