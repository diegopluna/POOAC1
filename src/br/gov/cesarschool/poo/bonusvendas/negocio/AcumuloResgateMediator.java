package br.gov.cesarschool.poo.bonusvendas.negocio;

import br.gov.cesarschool.poo.bonusvendas.dao.CaixaDeBonusDAO;
import br.gov.cesarschool.poo.bonusvendas.dao.LancamentoBonusDAO;
import br.gov.cesarschool.poo.bonusvendas.entidade.*;

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

    public static AcumuloResgateMediator getInstancia() {
        if (instance == null) {
            instance = new AcumuloResgateMediator();
        }
        return instance;
    }

    public long gerarCaixaDeBonus(Vendedor vendedor) {
        long numero = Long.parseLong(vendedor.getCpf().substring(0, vendedor.getCpf().length() - 2) + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        if (this.repositorioCaixaDeBonus.incluir(new CaixaDeBonus(numero))) {
            return numero;
        }
        return 0;
    }

    public String acumularBonus(long numeroCaixaDeBonus, double valor) {
        if (valor <= 0) {
            return "Valor menor ou igual a zero";
        }
        CaixaDeBonus caixa = repositorioCaixaDeBonus.buscar(numeroCaixaDeBonus);
        if (caixa == null) {
            return "Caixa de bonus inexistente";
        }
        caixa.creditar(valor);
        this.repositorioCaixaDeBonus.alterar(caixa);
        this.repositorioLancamento.incluir( new LancamentoBonusCredito(numeroCaixaDeBonus, valor, LocalDateTime.now()));
        return null;
    }

    public String resgatar(long numeroCaixaDeBonus, double valor, TipoResgate tipo) {
        if (valor <= 0) {
            return "Valor menor ou igual a zero";
        }
        CaixaDeBonus caixa = repositorioCaixaDeBonus.buscar(numeroCaixaDeBonus);
        if (caixa == null) {
            return "Caixa de bonus inexistente";
        }
        if (valor > caixa.getSaldo()) {
            return "Saldo insuficiente";
        }
        caixa.debitar(valor);
        this.repositorioCaixaDeBonus.alterar(caixa);
        this.repositorioLancamento.incluir( new LancamentoBonusResgate(numeroCaixaDeBonus, valor, LocalDateTime.now(), tipo));
        return null;
    }
}
