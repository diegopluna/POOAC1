package br.gov.cesarschool.poo.bonusvendas.dao;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonus;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;

import java.io.Serializable;
import java.time.LocalDateTime;
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

    public LancamentoBonus buscar(LancamentoBonus lancamentoBonus) {
        return (LancamentoBonus) dao.buscar(lancamentoBonus.getIdUnico());
    }

    public LancamentoBonus[] buscarTodos() {
        Registro[] registros = dao.buscarTodos();
        LancamentoBonus[] lancs = new LancamentoBonus[registros.length];
        for (int i = 0; i < registros.length; i++) {
            lancs[i] = (LancamentoBonus)registros[i];
        }
        return lancs;
    }
}
