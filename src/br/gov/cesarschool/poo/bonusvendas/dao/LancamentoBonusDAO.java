package br.gov.cesarschool.poo.bonusvendas.dao;

import br.edu.cesarschool.next.oo.persistenciaobjetos.CadastroObjetos;
import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LancamentoBonusDAO {
    private static final String BRANCO = "";
    private CadastroObjetos cadastro = new CadastroObjetos(LancamentoBonus.class);

    public boolean incluir(LancamentoBonus lancamento) {
        LancamentoBonus lancamentoBusca = buscar(lancamento.getNumeroCaixaDeBonus(), lancamento.getDataHoraLancamento());

        if (lancamentoBusca != null) {
            return false;
        } else {
            cadastro.incluir(lancamento, BRANCO + lancamento.getNumeroCaixaDeBonus() + lancamento.getDataHoraLancamento().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
            return true;
        }
    }

    public boolean alterar(LancamentoBonus lancamento) {
        LancamentoBonus lancamentoBusca = buscar(lancamento.getNumeroCaixaDeBonus(), lancamento.getDataHoraLancamento());

        if (lancamentoBusca == null) {
            return false;
        } else {
            cadastro.alterar(lancamento, BRANCO + lancamento.getNumeroCaixaDeBonus() + lancamento.getDataHoraLancamento().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
            return true;
        }
    }

    public boolean excluir(LancamentoBonus lancamento) {
        LancamentoBonus lancamentoBusca = buscar(lancamento.getNumeroCaixaDeBonus(), lancamento.getDataHoraLancamento());

        if (lancamentoBusca == null) {
            return false;
        } else {
            cadastro.excluir(BRANCO + lancamento.getNumeroCaixaDeBonus() + lancamento.getDataHoraLancamento().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
            return true;
        }
    }

    public LancamentoBonus buscar(long numero, LocalDateTime hora) {
        return (LancamentoBonus) cadastro.buscar(BRANCO + numero + hora.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
    }

    public LancamentoBonus[] buscarTodos() {
        Serializable[] rets = cadastro.buscarTodos(LancamentoBonus.class);
        LancamentoBonus[] lancs = new LancamentoBonus[rets.length];
        for (int i = 0; i < rets.length; i++) {
            lancs[i] = (LancamentoBonus)rets[i];
        }
        return lancs;
    }
}
