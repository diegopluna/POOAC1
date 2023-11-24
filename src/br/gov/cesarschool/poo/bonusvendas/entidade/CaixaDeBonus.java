package br.gov.cesarschool.poo.bonusvendas.entidade;

import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CaixaDeBonus extends Registro {
    private long numero;
    private double saldo;
    private LocalDateTime dataHoraAtualizacao;

    public CaixaDeBonus(long numero) {
        super();
        this.numero = numero;
        this.dataHoraAtualizacao = LocalDateTime.now();
    }

    public String getIdUnico() {
        return String.valueOf(numero); //TODO CHECAR SE É ISSO AQUI MESMO
    }

    public long getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public LocalDateTime getDataHoraAtualizacao() {
        return dataHoraAtualizacao;
    }

    public void creditar(double valor) {
        this.saldo = saldo + valor;
        this.dataHoraAtualizacao = LocalDateTime.now();
    }

    public void debitar(double valor) {
        this.saldo = saldo - valor;
        this.dataHoraAtualizacao = LocalDateTime.now();
    }
}
