package com.algaworks.junit.utilidade;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testes no utilitário de filtro de números.")
// @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class FiltroNumerosTest {

    @Test
    @DisplayName("Deve retornar apenas números pares.")
    void numerosPares_withArrayOfNumbers_returnsOnlyEvenNumbers() {
        List<Integer> numbers = Arrays.asList(1,2,3,4);
        List<Integer> expectedEvenNumbers = Arrays.asList(2, 4);

        List<Integer> actualNumbers = FiltroNumeros.numerosPares(numbers);

        Assertions.assertIterableEquals(expectedEvenNumbers, actualNumbers);
    }
    
}
