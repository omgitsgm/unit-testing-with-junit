package com.algaworks.junit.blog.negocio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.algaworks.junit.blog.modelo.Editor;
import com.algaworks.junit.blog.modelo.Ganhos;
import com.algaworks.junit.blog.modelo.Post;
import com.algaworks.junit.blog.utilidade.ProcessadorTextoSimples;

@DisplayName("Testes na Calculadora de Ganhos")
class CalculadoraGanhosTest {

    static CalculadoraGanhos calculadoraGanhos;
    Editor autor;
    Post post;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Antes de todos os testes.");

        calculadoraGanhos = new CalculadoraGanhos(new ProcessadorTextoSimples(), BigDecimal.TEN);
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Antes de cada teste.");

        autor = new Editor(1L, "Gabriel", "gabriel@gmail.com", new BigDecimal(5), true);
        
        post = new Post(1L, "Ecossistema Java", "O ecossistema do Java é muito maduro", autor,
         "ecossistema-java-abc123", null, false, false);
    }

    @Test
    void givenAPostAndAnAutorPremium_whenCalcularGanhos_thenReturnValorComBonus() {

        BigDecimal expectedGanhos = new BigDecimal(45);
        
        Ganhos actualGanhos = calculadoraGanhos.calcular(post);

        assertEquals(expectedGanhos, actualGanhos.getTotalGanho());

    }

    @Test
    void givenAPostAndAutor_whenCalcularGanhos_thenReturnQuantidadePalavrasDoPost() {

        int expectedQuantidadePalavras = 7;
        
        Ganhos actualGanhos = calculadoraGanhos.calcular(post);

        assertEquals(expectedQuantidadePalavras, actualGanhos.getQuantidadePalavras());

    }

    @Test
    void givenAPostAndAutor_whenCalcularGanhos_thenReturnValorPagoPorPalavraDoAutor() {

        Ganhos actualGanhos = calculadoraGanhos.calcular(post);

        assertEquals(autor.getValorPagoPorPalavra(), actualGanhos.getValorPagoPorPalavra());

    }


    @Test
    void givenAPostAndAutorSemPremium_whenCalcularGanhos_thenReturnGanhosWithoutPremium() {
        
        autor.setPremium(false);
        BigDecimal expectedGanhos = new BigDecimal(35);

        Ganhos actualGanhos = calculadoraGanhos.calcular(post);

        assertEquals(expectedGanhos, actualGanhos.getTotalGanho());

    }


}
