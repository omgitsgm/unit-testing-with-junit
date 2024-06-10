package com.algaworks.junit.utilidade;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class SaudacaoUtilTest {
    
    @Test
    void saudar_withHoraBetween0and11_returnsBomDia() {
        String saudacao = SaudacaoUtil.saudar(9);
        assertEquals("Bom dia", saudacao, "Saudação incorreta!");
    }

    @Test
    void saudar_withInvalidHora_throwsIllegalArgumentException() {
        IllegalArgumentException illegalArgumentException = 
            assertThrows(IllegalArgumentException.class, () -> SaudacaoUtil.saudar(-10));

        assertEquals("Hora inválida", illegalArgumentException.getMessage());
    }

    @Test
    void saudar_withValidHora_doesNotThrowException() {
        assertDoesNotThrow(() -> SaudacaoUtil.saudar(0));
    }

}
