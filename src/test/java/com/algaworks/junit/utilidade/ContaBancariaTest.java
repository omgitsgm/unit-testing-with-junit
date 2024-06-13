package com.algaworks.junit.utilidade;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class ContaBancariaTest {
    
    
    @Test
    void contaBancaria_withSaldoEqualsToNull_throwsIllegalArgumentException() {

        assertThrows(IllegalArgumentException.class, () -> new ContaBancaria(null), "Saldo inválido.");

    }

    @Test
    void contaBancaria_withValidSaldo_createsNewContaBancaria() {

        ContaBancaria contaBancaria = new ContaBancaria(BigDecimal.valueOf(100));

        assertEquals(BigDecimal.valueOf(100), contaBancaria.getSaldo());

    }

    @Test
    void saque_withValorNull_throwsIllegalArgumentException() {

        ContaBancaria contaBancaria = new ContaBancaria(BigDecimal.valueOf(100));

        BigDecimal valor = null;

        assertThrows(IllegalArgumentException.class, () -> contaBancaria.saque(valor), "Valor inválido.");

    }

    @Test
    void saque_withValorLessThanZero_throwsIllegalArgumentException() {

        ContaBancaria contaBancaria = new ContaBancaria(BigDecimal.valueOf(100));

        BigDecimal valor = new BigDecimal(-1);

        assertThrows(IllegalArgumentException.class, () -> contaBancaria.saque(valor));

    }

    @Test
    void saque_withValorEqualsToZero_throwsIllegalArgumentException() {

        ContaBancaria contaBancaria = new ContaBancaria(BigDecimal.valueOf(100));

        BigDecimal valor = new BigDecimal(0);

        assertThrows(IllegalArgumentException.class, () -> contaBancaria.saque(valor));

    }

    @Test
    void saque_withSaldoInsuficiente_throwsRuntimeException() {

        ContaBancaria contaBancaria = new ContaBancaria(BigDecimal.valueOf(0));

        BigDecimal valor = new BigDecimal(50);

        assertThrows(RuntimeException.class, () -> contaBancaria.saque(valor));

    }

    @Test
    void saque_withValidValor_changesContaBancariaSaldo() {

        ContaBancaria contaBancaria = new ContaBancaria(BigDecimal.valueOf(100));

        BigDecimal valor = new BigDecimal(50);

        contaBancaria.saque(valor);

        assertEquals(new BigDecimal(50), contaBancaria.getSaldo());

    }

    @Test
    void deposito_withValorNull_throwsIllegalArgumentException() {

        ContaBancaria contaBancaria = new ContaBancaria(BigDecimal.valueOf(100));

        BigDecimal valor = null;

        assertThrows(IllegalArgumentException.class, () -> contaBancaria.deposito(valor));

    }

    @Test
    void deposito_withValorLessThanZero_throwsIllegalArgumentException() {

        ContaBancaria contaBancaria = new ContaBancaria(BigDecimal.valueOf(100));

        BigDecimal valor = new BigDecimal(-1);

        assertThrows(IllegalArgumentException.class, () -> contaBancaria.deposito(valor));

    }

    @Test
    void deposito_withValorEqualsToZero_throwsIllegalArgumentException() {

        ContaBancaria contaBancaria = new ContaBancaria(BigDecimal.valueOf(100));

        BigDecimal valor = new BigDecimal(0);

        assertThrows(IllegalArgumentException.class, () -> contaBancaria.deposito(valor));

    }

    @Test
    void deposito_withValidValor_changesContaBancariaSaldo() {

        ContaBancaria contaBancaria = new ContaBancaria(BigDecimal.valueOf(100));

        BigDecimal valor = new BigDecimal(50);

        BigDecimal expectedSaldo = new BigDecimal(150);

        contaBancaria.deposito(valor);

        assertEquals(expectedSaldo, contaBancaria.getSaldo());

    }

    @Test
    void saldo_returnsContaBancariaSaldo() {

        BigDecimal expectedSaldo = new BigDecimal(100);
        ContaBancaria contaBancaria = new ContaBancaria(expectedSaldo);

        BigDecimal saldo = contaBancaria.saldo();

        assertEquals(expectedSaldo, saldo);

    }

}
