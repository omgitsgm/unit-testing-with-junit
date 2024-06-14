package com.algaworks.junit.blog.negocio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.algaworks.junit.blog.modelo.Editor;
import com.algaworks.junit.blog.modelo.Ganhos;
import com.algaworks.junit.blog.modelo.Post;
import com.algaworks.junit.blog.utilidade.ProcessadorTextoSimples;

class CalculadoraGanhosTest {

    static CalculadoraGanhos calculadoraGanhos;
    Editor editor;
    Post post;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Antes de todos os testes.");

        calculadoraGanhos = new CalculadoraGanhos(new ProcessadorTextoSimples(), BigDecimal.TEN);
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Antes de cada teste.");

        editor = new Editor(1L, "Gabriel", "gabriel@gmail.com", new BigDecimal(5), true);
        
        post = new Post(1L, "Ecossistema Java", "O ecossistema do Java é muito maduro", editor,
         "ecossistema-java-abc123", null, false, false);
    }

    // @AfterAll
    // static void afterAll() {
    //     System.out.println("Depois de todos os testes.");
    // }

    // @AfterEach
    // void afterEach() {
    //     System.out.println("Depois de cada teste.");
    // }
    
    @Test
    void givenAPost_whenCalcular_thenReturnGanhos() {

        BigDecimal expectedGanhos = new BigDecimal(45);
        int expectedQuantidadePalavras = 7;
        
        Ganhos actualGanhos = calculadoraGanhos.calcular(post);

        assertEquals(expectedGanhos, actualGanhos.getTotalGanho());
        assertEquals(expectedQuantidadePalavras, actualGanhos.getQuantidadePalavras());
        assertEquals(editor.getValorPagoPorPalavra(), actualGanhos.getValorPagoPorPalavra());

    }

    @Test
    void givenAPost_whenCalcular_thenReturnGanhosWithoutPremium() {
        
        editor.setPremium(false);
        BigDecimal expectedGanhos = new BigDecimal(35);
        int expectedQuantidadePalavras = 7;
        

        Ganhos actualGanhos = calculadoraGanhos.calcular(post);

        assertEquals(expectedGanhos, actualGanhos.getTotalGanho());
        assertEquals(expectedQuantidadePalavras, actualGanhos.getQuantidadePalavras());
        assertEquals(editor.getValorPagoPorPalavra(), actualGanhos.getValorPagoPorPalavra());

    }

}
