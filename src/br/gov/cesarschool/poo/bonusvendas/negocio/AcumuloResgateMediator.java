package br.gov.cesarschool.poo.bonusvendas.negocio;

import br.gov.cesarschool.poo.bonusvendas.dao.CaixaDeBonusDAO;

public class AcumuloResgateMediator {
    private static AcumuloResgateMediator instance;
    private CaixaDeBonusDAO repositorioCaixaDeBonus;

    private AcumuloResgateMediator() {}

    public static AcumuloResgateMediator getInstance() {
        if (instance == null) {
            instance = new AcumuloResgateMediator();
        }
        return instance;
    }
}
