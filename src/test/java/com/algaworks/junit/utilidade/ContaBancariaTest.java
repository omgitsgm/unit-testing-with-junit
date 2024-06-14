package com.algaworks.junit.utilidade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

@DisplayName("Testes no utilitário de conta bancária.")
class ContaBancariaTest {
    
    
    @Test
    @DisplayName("Deve lançar IllegalArgumentException ao criar conta com saldo null.")
    void contaBancaria_withSaldoEqualsToNull_throwsIllegalArgumentException() {

        BigDecimal invalidSaldo = null;
        String expectedExceptionMessage = "O saldo não pode ser nulo.";

        Executable executable = () -> new ContaBancaria(invalidSaldo);

        IllegalArgumentException illegalArgumentException = 
            assertThrows(IllegalArgumentException.class, executable, "Saldo inválido.");

        assertEquals(expectedExceptionMessage, illegalArgumentException.getMessage());

    }

    @Test
    @DisplayName("Deve criar uma nova conta bancária.")
    void contaBancaria_withValidSaldo_createsNewContaBancaria() {

        BigDecimal validSaldo = new BigDecimal(100);
        BigDecimal expectedSaldo = validSaldo;

        ContaBancaria contaBancaria = new ContaBancaria(validSaldo);

        assertEquals(expectedSaldo, contaBancaria.getSaldo());

    }

    @Nested
    @DisplayName("Testes relacionados ao método de saque.")
    class Saque {

        @Test
        @DisplayName("Deve lançar IllegalArgumentException quando sacar um valor null.")
        void saque_withValorNull_throwsIllegalArgumentException() {
    
            BigDecimal initialSaldo = new BigDecimal(100);
            ContaBancaria contaBancaria = new ContaBancaria(initialSaldo);
            BigDecimal valorSaqueNull = null;
            String expectedExceptionMessage = "O valor não pode ser nulo, zero ou menor que zero.";
    
            Executable executable = () -> contaBancaria.saque(valorSaqueNull);
    
            IllegalArgumentException illegalArgumentException = 
                assertThrows(IllegalArgumentException.class, executable, "Valor inválido.");
    
            assertEquals(expectedExceptionMessage, illegalArgumentException.getMessage());
        }
    
        @Test
        @DisplayName("Deve lançar IllegalArgumentException quando sacar um valor menor do que zero.")
        void saque_withValorLessThanZero_throwsIllegalArgumentException() {
    
            BigDecimal initialSaldo = new BigDecimal(100);
            ContaBancaria contaBancaria = new ContaBancaria(initialSaldo);
            BigDecimal valorSaqueLessThanZero = new BigDecimal(-1);
            String expectedExceptionMessage = "O valor não pode ser nulo, zero ou menor que zero.";
    
            Executable executable = () -> contaBancaria.saque(valorSaqueLessThanZero);
    
            IllegalArgumentException illegalArgumentException = 
                assertThrows(IllegalArgumentException.class, executable);
    
            assertEquals(expectedExceptionMessage, illegalArgumentException.getMessage());
    
        }
    
        @Test
        @DisplayName("Deve lançar IllegalArgumentException quando sacar um valor igual a zero.")
        void saque_withValorEqualsToZero_throwsIllegalArgumentException() {
    
            BigDecimal initialSaldo = new BigDecimal(100);
            ContaBancaria contaBancaria = new ContaBancaria(initialSaldo);
            BigDecimal valorEqualsToZero = new BigDecimal(0);
            String expectedExceptionMessage = "O valor não pode ser nulo, zero ou menor que zero.";
    
            Executable executable = () -> contaBancaria.saque(valorEqualsToZero);
    
            IllegalArgumentException illegalArgumentException = 
                assertThrows(IllegalArgumentException.class, executable);
    
            assertEquals(expectedExceptionMessage, illegalArgumentException.getMessage());
    
        }
    
        @Test
        @DisplayName("Deve lançar RuntimeException quando o saldo não for suficiente para efetuar um saque.")
        void saque_withSaldoInsuficiente_throwsRuntimeException() {
    
            BigDecimal initialSaldo = new BigDecimal(0);
            ContaBancaria contaBancaria = new ContaBancaria(initialSaldo);
            BigDecimal valorSaqueGreaterThanSaldo = new BigDecimal(50);
            String expectedExceptionMessage = "Saldo insuficiente.";
    
            Executable executable = () -> contaBancaria.saque(valorSaqueGreaterThanSaldo);
    
            RuntimeException runtimeException = assertThrows(RuntimeException.class, executable);
            assertEquals(expectedExceptionMessage, runtimeException.getMessage());
    
        }
    
        @Test
        @DisplayName("Deve subtrair um valor do saldo da conta ao realizar um saque.")
        void saque_withValidValor_changesContaBancariaSaldo() {
    
            BigDecimal initialSaldo = new BigDecimal(100);
            ContaBancaria contaBancaria = new ContaBancaria(initialSaldo);
            BigDecimal validValor = new BigDecimal(50);
            BigDecimal expectedSaldo = new BigDecimal(50);
            
            contaBancaria.saque(validValor);
    
            assertEquals(expectedSaldo, contaBancaria.getSaldo());
    
        }

    }


    @Nested
    @DisplayName("Testes relacionados ao método de depósito.")
    class Deposito {

        @Test
        @DisplayName("Deve lançar IllegalArgumentException quando depositar um valor null.")
        void deposito_withValorNull_throwsIllegalArgumentException() {
    
            BigDecimal initialSaldo = new BigDecimal(100);
            ContaBancaria contaBancaria = new ContaBancaria(initialSaldo);
            BigDecimal valorNull = null;
            String expectedExceptionMessage = "O valor não pode ser nulo, zero ou menor que zero.";
    
            Executable executable = () -> contaBancaria.deposito(valorNull);
    
            IllegalArgumentException illegalArgumentException = 
                assertThrows(IllegalArgumentException.class, executable);
    
            assertEquals(expectedExceptionMessage, illegalArgumentException.getMessage());
    
        }
    
        @Test
        @DisplayName("Deve lançar IllegalArgumentException quando depositar um valor menor do que zero.")
        void deposito_withValorLessThanZero_throwsIllegalArgumentException() {
    
            BigDecimal initialSaldo = new BigDecimal(100);
            ContaBancaria contaBancaria = new ContaBancaria(initialSaldo);
            BigDecimal valorLessThanZero = new BigDecimal(-1);
            String expectedExceptionMessage = "O valor não pode ser nulo, zero ou menor que zero.";
    
            Executable executable = () -> contaBancaria.deposito(valorLessThanZero);
    
            IllegalArgumentException illegalArgumentException = 
                assertThrows(IllegalArgumentException.class, executable);
    
            assertEquals(expectedExceptionMessage, illegalArgumentException.getMessage());
    
        }
    
        @Test
        @DisplayName("Deve lançar IllegalArgumentException quando depositar um valor igual a zero.")
        void deposito_withValorEqualsToZero_throwsIllegalArgumentException() {
    
            BigDecimal initialSaldo = new BigDecimal(100);
            ContaBancaria contaBancaria = new ContaBancaria(initialSaldo);
            BigDecimal valorEqualsToZero = new BigDecimal(0);
            String expectedExceptionMessage = "O valor não pode ser nulo, zero ou menor que zero.";
    
            Executable executable = () -> contaBancaria.deposito(valorEqualsToZero);
    
            IllegalArgumentException illegalArgumentException =
                assertThrows(IllegalArgumentException.class, executable);
    
            assertEquals(expectedExceptionMessage, illegalArgumentException.getMessage());
    
        }
    
        @Test
        @DisplayName("Deve adicionar um valor ao saldo da conta ao realizar um depósito.")
        void deposito_withValidValor_changesContaBancariaSaldo() {
    
            BigDecimal initialSaldo = new BigDecimal(100);
            ContaBancaria contaBancaria = new ContaBancaria(initialSaldo);
            BigDecimal validValor = new BigDecimal(50);
            BigDecimal expectedSaldo = new BigDecimal(150);
    
            contaBancaria.deposito(validValor);
    
            assertEquals(expectedSaldo, contaBancaria.getSaldo());
    
        }

    }


    @Nested
    @DisplayName("Testes relacionados ao saldo.")
    class Saldo {
        
        @Test
        @DisplayName("Deve retornar o saldo da conta bancária.")
        void saldo_returnsContaBancariaSaldo() {
    
            BigDecimal initialSaldo = new BigDecimal(100);
            ContaBancaria contaBancaria = new ContaBancaria(initialSaldo);
            BigDecimal expectedSaldo = new BigDecimal(100);
    
            BigDecimal saldo = contaBancaria.saldo();
    
            assertEquals(expectedSaldo, saldo);
    
        }

    }


}
