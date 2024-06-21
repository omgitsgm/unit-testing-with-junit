package com.algaworks.junit.utilidade;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

@DisplayName("Teste do utilitário ContaBancaria utilizando BDD.")
class ContaBancariaBDDTest {
    
    /*
     * Given, When, Then
     */

    @DisplayName("Dada uma conta bancária com saldo de R$10,00...")
    @Nested
    class GivenABankAccountWithBalance {
        
        ContaBancaria bankAccount;


        @BeforeEach
        void BeforeEach() {

            bankAccount = new ContaBancaria(BigDecimal.TEN);

        }


        @DisplayName("quando sacar um dinheiro inferior ao saldo atual...")
        @Nested
        class WhenWithdrawMoneyLessThanCurrentBalance {

            @DisplayName("então não jogue nenhuma exceção.")
            @Test
            void thenDoesNotThrowException() {

                Executable executable = () -> bankAccount.saque(BigDecimal.ONE);

                assertDoesNotThrow(executable);

            }

            @DisplayName("então subtraia o valor do saldo.")
            @Test
            void thenSubtractFromBalance() {

                BigDecimal expectedBalance = new BigDecimal(9);

                bankAccount.saque(BigDecimal.ONE);

                assertEquals(expectedBalance, bankAccount.saldo());

            }

        }

        @DisplayName("quando sacar um dinheiro maior do que o saldo atual...")
        @Nested
        class whenWithdrawMoneyGreaterThanCurrentBalance {

            static String insuficientFundsExceptionMessage = "Saldo insuficiente.";

            @DisplayName("então lance uma RuntimeException.")
            @Test
            void thenThrowRuntimeException() {

                Executable executable = () -> bankAccount.saque(new BigDecimal(11));

                RuntimeException exception = assertThrows(RuntimeException.class, executable);

                assertEquals(insuficientFundsExceptionMessage, exception.getMessage());

            }

            @DisplayName("então não altere o saldo atual.")
            @Test
            void thenDoNotChangeTheCurrentBalance() {

                BigDecimal expectedBalance = BigDecimal.TEN;

                try {
                    bankAccount.saque(new BigDecimal(11));
                } catch (RuntimeException exception) {}

                assertEquals(expectedBalance, bankAccount.saldo());

            }

        }

    }

    @DisplayName("Dada uma conta bancária com saldo de R$0,00...")
    @Nested
    class GivenABankAccountWithoutBalance {

        static ContaBancaria bankAccount;

        @BeforeEach
        void beforeEach() {

            bankAccount = new ContaBancaria(BigDecimal.ZERO);

        }

        @DisplayName("quando sacar um dinheiro maior do que o saldo atual...")
        @Nested
        class WhenWithDrawMoneyGreaterThanCurrentBalance {

            @DisplayName("então lance uma IllegalArgumentException")
            @Test
            void thenThrowIllegalArgumentException() {

                Executable executable = () -> bankAccount.saque(BigDecimal.TEN);

                assertThrows(RuntimeException.class, executable);

            }

            @DisplayName("então não altere o saldo atual.")
            @Test
            void thenDoNotChangeTheCurrentBalance() {

                BigDecimal expectedBalance = BigDecimal.ZERO;

                try {
                    bankAccount.saque(BigDecimal.TEN);
                } catch(RuntimeException exception) {}

                assertEquals(expectedBalance, bankAccount.saldo());

            }

        }


        @DisplayName("quando depositar um dinheiro maior do que zero...")
        @Nested
        class whenDepositMoneyGreaterThanZero {

            @DisplayName("então adicione o valor do depósito ao saldo atual.")
            @Test
            void thenAddValueToCurrentBalance() {

                BigDecimal expectedBalance = BigDecimal.TEN;

                bankAccount.deposito(BigDecimal.TEN);

                assertEquals(expectedBalance, bankAccount.saldo());

            }

        }

    }

}
