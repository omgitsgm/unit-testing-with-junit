package com.algaworks.junit.utilidade;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

@DisplayName("Testes no utilitário de saudação.")
class SaudacaoUtilTest {
    
    // BDD    : givenAnHourBetween0and11_whenSaudar_thenReturnBomDia
    // Default: saudar_withHoraBetween0and11_returnsBomDia
    @Test
    @DisplayName("Deve saudar com bom dia em um horário entre 0h e 11h.")
    void givenAnHourBetween0and11_whenSaudar_thenReturnBomDia() {
        // Arrange
        int horaBetween0and11 = 9;
        String expectedSaudacao = "Bom dia";
        
        // Act
        String actualSaudacao = SaudacaoUtil.saudar(horaBetween0and11);

        // Assert
        assertEquals(expectedSaudacao, actualSaudacao, "Saudação incorreta!");
    }

    // BDD    : givenAnHourEqualTo5_whenSaudar_thenReturnBomDia
    // Default: saudar_withHora5_returnsBomDia
    @Test
    @DisplayName("Deve saudar com bom dia às 5h.")
    void givenAnHourEqualTo5_whenSaudar_thenReturnBomDia() {

        int hora5 = 5;
        String expectedSaudacao = "Bom dia";

        String saudacao = SaudacaoUtil.saudar(hora5);

        assertEquals(expectedSaudacao, saudacao, "Saudação incorreta!");
    }

    // BDD    : givenAnHourBetween12and17_whenSaudar_thenReturnBoaTarde
    // Default: saudar_withHoraBetween12and17_returnsBoaTarde
    @Test
    @DisplayName("Deve saudar com boa tarde em um horário entre 12h e 17h.")
    void givenAnHourBetween12and17_whenSaudar_thenReturnBoaTarde() {

        int horaBetween12and17 = 15;
        String expectedSaudacao = "Boa tarde";

        String saudacao = SaudacaoUtil.saudar(horaBetween12and17);

        assertEquals(expectedSaudacao, saudacao, "Saudação incorreta!");
    }

    // BDD    : givenAnHourBetween18and23_whenSaudar_thenReturnBoaNoite
    // Default: saudar_withHoraBetween18and23_returnsBoaNoite
    @Test
    @DisplayName("Deve saudar com boa noite entre 18h e 23h.")
    void givenAnHourBetween18and23_whenSaudar_thenReturnBoaNoite() {

        int horaBetween18and23 = 21;
        String expectedSaudacao = "Boa noite";

        String saudacao = SaudacaoUtil.saudar(horaBetween18and23);

        assertEquals(expectedSaudacao, saudacao, "Saudação incorreta!");
    }

    // BDD    : givenAnHourEqualTo4_whenSaudar_thenReturnBoaNoite
    // Default: saudar_withHora4_returnsBoaNoite
    @Test
    @DisplayName("Deve saudar com boa noite às 4h.")
    void givenAnHourEqualTo4_whenSaudar_thenReturnBoaNoite() {

        int hora4 = 4;
        String expectedSaudacao = "Boa noite";

        String saudacao = SaudacaoUtil.saudar(hora4);

        assertEquals(expectedSaudacao, saudacao, "Saudação incorreta!");
    }

    // BDD    : givenAnInvalidHour_whenSaudar_thenThrowAnIllegalArgumentException
    // Default: saudar_withInvalidHora_throwsIllegalArgumentException
    @Test
    @DisplayName("Deve lançar IllegalArgumentException quando saudar com hora inválida.")
    void givenAnInvalidHour_whenSaudar_thenThrowAnIllegalArgumentException() {

        // Arrange
        int invalidHora = -10;
        String expectedExceptionMessage = "Hora inválida";

        // Act
        Executable executable = () -> SaudacaoUtil.saudar(invalidHora);

        // Assert
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, executable);
        assertEquals(expectedExceptionMessage, illegalArgumentException.getMessage());
    }

    // BDD    : givenAValidHour_whenSaudar_thenDoNotThrowAnyException
    // Default: saudar_withValidHora_doesNotThrowException
    @Test
    @DisplayName("Não deve lançar exceção quando saudar com hora válida.")
    void givenAValidHour_whenSaudar_thenDoNotThrowAnyException() {

        int validHora = 0;

        Executable executable = () -> SaudacaoUtil.saudar(validHora);

        assertDoesNotThrow(executable);
    }

}
