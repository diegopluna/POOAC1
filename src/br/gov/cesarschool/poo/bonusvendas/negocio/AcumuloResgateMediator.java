package br.gov.cesarschool.poo.bonusvendas.negocio;

import br.gov.cesarschool.poo.bonusvendas.dao.CaixaDeBonusDAO;
import br.gov.cesarschool.poo.bonusvendas.dao.LancamentoBonusDAO;
import br.gov.cesarschool.poo.bonusvendas.entidade.*;
import br.gov.cesarschool.poo.bonusvendas.util.Ordenadora;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AcumuloResgateMediator {
    private static final String CAIXA_DE_BONUS_INEXISTENTE = "Caixa de bonus inexistente";
    private static final String VALOR_MENOR_OU_IGUAL_A_ZERO = "Valor menor ou igual a zero";
    private static AcumuloResgateMediator instancia;
    public static AcumuloResgateMediator getInstancia() {
        if (instancia == null) {
            instancia = new AcumuloResgateMediator();
        }
        return instancia;
    }

    private CaixaDeBonusDAO repositorioCaixaDeBonus;
    private LancamentoBonusDAO repositorioLancamento;

    private AcumuloResgateMediator() {
        this.repositorioLancamento = new LancamentoBonusDAO();
        this.repositorioCaixaDeBonus = new CaixaDeBonusDAO();
    }
    public long gerarCaixaDeBonus(Vendedor vendedor) {
        LocalDate dataAtual = LocalDate.now();
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        long numero = Long.parseLong(vendedor.getCpf().substring(0, 9) + dataAtual.format(customFormatter));
        CaixaDeBonus caixa = new CaixaDeBonus(numero);
        boolean ret = repositorioCaixaDeBonus.incluir(caixa);
        if (ret) {
            return numero;
        } else {
            return 0;
        }
    }

    public String acumularBonus(long numeroCaixaDeBonus, double valor) {
        if (valor <= 0) {
            return VALOR_MENOR_OU_IGUAL_A_ZERO;
        }
        CaixaDeBonus caixa = repositorioCaixaDeBonus.buscar(numeroCaixaDeBonus);
        if (caixa == null) {
            return CAIXA_DE_BONUS_INEXISTENTE;
        }
        caixa.creditar(valor);
        repositorioCaixaDeBonus.alterar(caixa);
        LancamentoBonusCredito lancamento = new LancamentoBonusCredito(numeroCaixaDeBonus, valor, LocalDateTime.now());
        repositorioLancamento.incluir( lancamento );
        return null;
    }

    public String resgatar(long numeroCaixaDeBonus, double valor, TipoResgate tipo) {
        if (valor <= 0) {
            return VALOR_MENOR_OU_IGUAL_A_ZERO;
        }
        CaixaDeBonus caixa = repositorioCaixaDeBonus.buscar(numeroCaixaDeBonus);
        if (caixa == null) {
            return CAIXA_DE_BONUS_INEXISTENTE;
        }
        if (caixa.getSaldo() < valor) {
            return "Saldo insuficiente";
        }
        caixa.debitar(valor);
        repositorioCaixaDeBonus.alterar(caixa);
        LancamentoBonusDebito lancamento = new LancamentoBonusDebito(numeroCaixaDeBonus, valor, LocalDateTime.now(), tipo);
        repositorioLancamento.incluir(lancamento);
        return null;
    }

    public CaixaDeBonusDAO getRepositorioCaixaDeBonus() {
        return repositorioCaixaDeBonus;
    }

    public CaixaDeBonus[] listaCaixaDeBonusPorSaldoMaior(double saldoInicial) {
        CaixaDeBonus[] caixas = repositorioCaixaDeBonus.buscarTodos();
        Ordenadora.ordenar(caixas, ComparadorCaixaDeBonusSaldoDec.getInstance());
        int count = 0;
        for (int i = 0; i < caixas.length; i++) {
            if (caixas[i].getSaldo() >= saldoInicial) {
                count++;
            }
        }
        CaixaDeBonus[] retorno = new CaixaDeBonus[count];
        for (int i = 0; i < count; i++) {
            retorno[i] = caixas[i];
        }
        return retorno;
    }

    public LancamentoBonus[] listaLancamentosPorFaixaData(LocalDate d1, LocalDate d2) {
        LancamentoBonus[] lancamentos = repositorioLancamento.buscarTodos();
        List<LancamentoBonus> lista = new ArrayList<>(Collections.emptyList());
        for (int i = 0; i < lancamentos.length; i++) {
            if (lancamentos[i].getDataHoraLancamento().toLocalDate().isAfter(d1) || lancamentos[i].getDataHoraLancamento().toLocalDate().isEqual(d1)) {
                if(lancamentos[i].getDataHoraLancamento().toLocalDate().isBefore(d2) || lancamentos[i].getDataHoraLancamento().toLocalDate().isEqual(d2)) {
                    lista.add(lancamentos[i]);
                }
            }
        }
        Collections.sort(lista, ComparadorLancamentoBonusDHDec.getInstance());
        LancamentoBonus[] lancamentosOrdenados = new LancamentoBonus[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            lancamentosOrdenados[i] = lista.get(i);
        }
        return lancamentosOrdenados;
    }
}
