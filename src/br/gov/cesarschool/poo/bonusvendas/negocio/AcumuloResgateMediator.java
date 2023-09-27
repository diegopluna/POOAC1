package br.gov.cesarschool.poo.bonusvendas.negocio;

import br.gov.cesarschool.poo.bonusvendas.dao.CaixaDeBonusDAO;
import br.gov.cesarschool.poo.bonusvendas.dao.LancamentoBonusDAO;
import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;
import br.gov.cesarschool.poo.bonusvendas.entidade.LancamentoBonusCredito;
import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AcumuloResgateMediator {
    private static AcumuloResgateMediator instance;
    private CaixaDeBonusDAO repositorioCaixaDeBonus;
    private LancamentoBonusDAO repositorioLancamento;



    private AcumuloResgateMediator() {
        this.repositorioLancamento = new LancamentoBonusDAO();
        this.repositorioCaixaDeBonus = new CaixaDeBonusDAO();
    }

    public static AcumuloResgateMediator getInstance() {
        if (instance == null) {
            instance = new AcumuloResgateMediator();
        }
        return instance;
    }

    public long gerarCaixaDeBonus(Vendedor vendedor) {
        long numero = Long.parseLong(vendedor.getCpf() + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        if (this.repositorioCaixaDeBonus.incluir(new CaixaDeBonus(numero))) {
            return numero;
        }
        return 0;
    }

    public String acumularBonus(long numeroCaixaDeBonus, double valor) {
        if (valor <= 0) {
            return "Bônus tem que ser um número positivo";
        }
        CaixaDeBonus caixa = repositorioCaixaDeBonus.buscar(numeroCaixaDeBonus);
        if (caixa == null) {
            return "Caixa de bônus não existe";
        }
        caixa.creditar(valor);
        this.repositorioCaixaDeBonus.alterar(caixa);
        this.repositorioLancamento.incluir( new LancamentoBonusCredito(numeroCaixaDeBonus, (int) valor, LocalDateTime.now()));
        return null;
    }
}
