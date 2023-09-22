package br.gov.cesarschool.poo.bonusvendas.entidade;

import java.time.LocalDateTime;

public class LancamentoBonusDebito extends LancamentoBonus {
    TipoResgate tipoResgate;

    public LancamentoBonusDebito(long numeroCaixaDeBonus, int valor, LocalDateTime dataHoraLancamento, TipoResgate tipoResgate) {
        super(numeroCaixaDeBonus, valor, dataHoraLancamento);
        this.tipoResgate = tipoResgate;
    }

    public TipoResgate getTipoResgate() {
        return tipoResgate;
    }
}
