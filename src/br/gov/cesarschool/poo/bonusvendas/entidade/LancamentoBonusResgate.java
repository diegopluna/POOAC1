package br.gov.cesarschool.poo.bonusvendas.entidade;

import java.time.LocalDateTime;

public class LancamentoBonusResgate extends LancamentoBonus {
    TipoResgate tipoResgate;

    public LancamentoBonusResgate(long numeroCaixaDeBonus, double valor, LocalDateTime dataHoraLancamento, TipoResgate tipoResgate) {
        super(numeroCaixaDeBonus, valor, dataHoraLancamento);
        this.tipoResgate = tipoResgate;
    }

    public TipoResgate getTipoResgate() {
        return tipoResgate;
    }
}
