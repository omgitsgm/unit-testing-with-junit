package com.algaworks.junit.utilidade;

import java.math.BigDecimal;

public class ContaBancaria {

    private BigDecimal saldo;

    public ContaBancaria(BigDecimal saldo) {
        //TODO 1 - validar saldo: não pode ser nulo, caso seja, deve lançar uma IllegalArgumentException
        if (saldo == null)
            throw new IllegalArgumentException("O saldo não pode ser nulo.");

        //TODO 2 - pode ser zero ou negativo
        this.saldo = saldo;

    }

    public void saque(BigDecimal valor) {
        //TODO 1 - validar valor: não pode ser nulo, zero ou menor que zero, caso seja, deve lançar uma IllegalArgumentException
        isValidValorOrElseThrowException(valor);
        
        //TODO 2 - Deve subtrair o valor do saldo
        BigDecimal saldoAfterSaque = saldo.subtract(valor);

        //TODO 3 - Se o saldo for insuficiente deve lançar uma RuntimeException
        if(saldoAfterSaque.compareTo(BigDecimal.ZERO) < 0)
            throw new RuntimeException("Saldo insuficiente.");

        this.saldo = saldoAfterSaque;

    }

    public void deposito(BigDecimal valor) {
        //TODO 1 - validar valor: não pode ser nulo, zero ou menor que zero, caso seja, deve lançar uma IllegalArgumentException
        isValidValorOrElseThrowException(valor);

        //TODO 2 - Deve adicionar o valor ao saldo
        this.saldo = saldo.add(valor);

    }

    public BigDecimal saldo() {
        //TODO 1 - retornar saldo
        return this.saldo;
    }

    private static void isValidValorOrElseThrowException(BigDecimal valor) {
        //TODO 1 - validar valor: não pode ser nulo, zero ou menor que zero, caso seja, deve lançar uma IllegalArgumentException
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("O valor não pode ser nulo, zero ou menor que zero.");

    }

    public BigDecimal getSaldo() {
        return this.saldo;
    }

}
