package br.gov.cesarschool.poo.bonusvendas.entidade;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CaixaDeBonus implements Serializable {
    private long numero;
    private double saldo;
    private LocalDateTime dataHoraAtualizacao;

    public CaixaDeBonus(long numero) {
        this.numero = numero;
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

    void creditar(double valor) {
        this.saldo = saldo + valor;
        this.dataHoraAtualizacao = LocalDateTime.now();
    }

    void debitar(double valor) {
        this.saldo = saldo - valor;
        this.dataHoraAtualizacao = LocalDateTime.now();
    }
}
