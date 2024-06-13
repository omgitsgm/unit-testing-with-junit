package com.algaworks.junit.utilidade;

import java.math.BigDecimal;

public class ContaBancaria {

    private BigDecimal saldo;

    public ContaBancaria(BigDecimal saldo) {
        if (saldo == null)
            throw new IllegalArgumentException("O saldo não pode ser nulo.");

        this.saldo = saldo;

    }

    public void saque(BigDecimal valor) {
        isValidValorOrElseThrowException(valor);
        
        BigDecimal saldoAfterSaque = saldo.subtract(valor);

        if(saldoAfterSaque.compareTo(BigDecimal.ZERO) < 0)
            throw new RuntimeException("Saldo insuficiente.");

        this.saldo = saldoAfterSaque;

    }

    public void deposito(BigDecimal valor) {
        isValidValorOrElseThrowException(valor);

        this.saldo = saldo.add(valor);

    }

    public BigDecimal saldo() {
        return this.saldo;
    }

    private static void isValidValorOrElseThrowException(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("O valor não pode ser nulo, zero ou menor que zero.");

    }

    public BigDecimal getSaldo() {
        return this.saldo;
    }

}
