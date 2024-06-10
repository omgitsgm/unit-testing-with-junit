package com.algaworks.junit.utilidade;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PessoaTest {
    
    @Test
    void pessoaConstructor_withNomeAndSobrenome_returnsPessoaWithNomeAndSobrenome() {
        Pessoa pessoa = new Pessoa("Gabriel", "Mendes");

        assertAll("Asserções de pessoa",
            () -> assertEquals("Gabriel", pessoa.getNome()),
            () -> assertEquals("Mendes", pessoa.getSobrenome())
        );
    }

}
