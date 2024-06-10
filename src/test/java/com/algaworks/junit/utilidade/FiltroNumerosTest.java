package com.algaworks.junit.utilidade;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FiltroNumerosTest {

    @Test
    void numerosPares_withArrayOfNumbers_returnsOnlyEvenNumbers() {
        List<Integer> numbers = Arrays.asList(1,2,3,4);

        List<Integer> expectedEvenNumbers = Arrays.asList(2, 4);

        List<Integer> actual = FiltroNumeros.numerosPares(numbers);

        Assertions.assertIterableEquals(expectedEvenNumbers, actual);
    }
    
}
