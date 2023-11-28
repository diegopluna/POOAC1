package br.gov.cesarschool.poo.bonusvendas.negocio;

import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;
import br.gov.cesarschool.poo.bonusvendas.util.Comparador;

public class ComparadorCaixaDeBonusSaldoDec implements Comparador {
    private static ComparadorCaixaDeBonusSaldoDec instance;
    public static ComparadorCaixaDeBonusSaldoDec getInstance() {
        if (instance == null) {
            instance = new ComparadorCaixaDeBonusSaldoDec();
        }
        return instance;
    }
    private ComparadorCaixaDeBonusSaldoDec(){}

    public int comparar(Object o1, Object o2) {
        CaixaDeBonus c1 = (CaixaDeBonus) o1;
        CaixaDeBonus c2 = (CaixaDeBonus) o2;

        if (c2.getSaldo() > c1.getSaldo()) {
            return 1;
        } else if (c2.getSaldo() == c1.getSaldo()) {
            return 0;
        } else {
            return -1;
        }
    }
}
