package br.gov.cesarschool.poo.bonusvendas.negocio;

import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonus;

import java.util.Comparator;

public class ComparadorLancamentoBonusDHDec implements Comparator {
    private static ComparadorLancamentoBonusDHDec instance;
    public static ComparadorLancamentoBonusDHDec getInstance() {
        if (instance == null) {
            instance = new ComparadorLancamentoBonusDHDec();
        }
        return instance;
    }

    private ComparadorLancamentoBonusDHDec(){}

    public int compare(Object o1, Object o2) {
        LancamentoBonus l1 = (LancamentoBonus) o1;
        LancamentoBonus l2 = (LancamentoBonus) o2;

        if (l2.getDataHoraLancamento().isAfter(l1.getDataHoraLancamento())) {
            return 1;
        } else if (l2.getDataHoraLancamento().isEqual(l1.getDataHoraLancamento())) {
            return 0;
        } else {
            return -1;
        }
    }
}
