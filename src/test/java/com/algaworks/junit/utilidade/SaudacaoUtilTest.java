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
    void saudar_withHora5_returnsBomDia() {
        String saudacao = SaudacaoUtil.saudar(5);
        assertEquals("Bom dia", saudacao, "Saudação incorreta!");
    }

    @Test
    void saudar_withHoraBetween12and17_returnsBoaTarde() {
        String saudacao = SaudacaoUtil.saudar(15);
        assertEquals("Boa tarde", saudacao, "Saudação incorreta!");
    }

    @Test
    void saudar_withHoraBetween18and23_returnsBoaNoite() {
        String saudacao = SaudacaoUtil.saudar(21);
        assertEquals("Boa noite", saudacao);
    }

    @Test
    void saudar_withHora4_returnsBoaNoite() {
        String saudacao = SaudacaoUtil.saudar(4);
        assertEquals("Boa noite", saudacao);
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
