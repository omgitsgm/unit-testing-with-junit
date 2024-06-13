package com.algaworks.junit.utilidade;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class SaudacaoUtilTest {
    
    @Test
    void saudar_withHoraBetween0and11_returnsBomDia() {
        // Arrange
        int horaBetween0and11 = 9;
        String expectedSaudacao = "Bom dia";
        
        // Act
        String actualSaudacao = SaudacaoUtil.saudar(horaBetween0and11);

        // Assert
        assertEquals(expectedSaudacao, actualSaudacao, "Saudação incorreta!");
    }

    @Test
    void saudar_withHora5_returnsBomDia() {

        int hora5 = 5;
        String expectedSaudacao = "Bom dia";

        String saudacao = SaudacaoUtil.saudar(hora5);

        assertEquals(expectedSaudacao, saudacao, "Saudação incorreta!");
    }

    @Test
    void saudar_withHoraBetween12and17_returnsBoaTarde() {

        int horaBetween12and17 = 15;
        String expectedSaudacao = "Boa tarde";

        String saudacao = SaudacaoUtil.saudar(horaBetween12and17);

        assertEquals(expectedSaudacao, saudacao, "Saudação incorreta!");
    }

    @Test
    void saudar_withHoraBetween18and23_returnsBoaNoite() {

        int horaBetween18and23 = 21;
        String expectedSaudacao = "Boa noite";

        String saudacao = SaudacaoUtil.saudar(horaBetween18and23);

        assertEquals(expectedSaudacao, saudacao, "Saudação incorreta!");
    }

    @Test
    void saudar_withHora4_returnsBoaNoite() {

        int hora4 = 4;
        String expectedSaudacao = "Boa noite";

        String saudacao = SaudacaoUtil.saudar(hora4);

        assertEquals(expectedSaudacao, saudacao, "Saudação incorreta!");
    }

    @Test
    void saudar_withInvalidHora_throwsIllegalArgumentException() {

        // Arrange
        int invalidHora = -10;
        String expectedExceptionMessage = "Hora inválida";

        // Act
        Executable executable = () -> SaudacaoUtil.saudar(invalidHora);

        // Assert
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, executable);
        assertEquals(expectedExceptionMessage, illegalArgumentException.getMessage());
    }

    @Test
    void saudar_withValidHora_doesNotThrowException() {

        int validHora = 0;

        Executable executable = () -> SaudacaoUtil.saudar(validHora);

        assertDoesNotThrow(executable);
    }

}
