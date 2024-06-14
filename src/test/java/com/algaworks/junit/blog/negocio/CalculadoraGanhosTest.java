package com.algaworks.junit.blog.negocio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.algaworks.junit.blog.modelo.Editor;
import com.algaworks.junit.blog.modelo.Ganhos;
import com.algaworks.junit.blog.modelo.Post;
import com.algaworks.junit.blog.utilidade.ProcessadorTextoSimples;

class CalculadoraGanhosTest {
    
    @Test
    void givenAPost_whenCalcular_thenReturnGanhos() {

        BigDecimal expectedGanhos = new BigDecimal(45);
        int expectedQuantidadePalavras = 7;
        
        CalculadoraGanhos calculadoraGanhos = new CalculadoraGanhos(new ProcessadorTextoSimples(), BigDecimal.TEN);
        
        Editor editor = new Editor(1L, "Gabriel", "gabriel@gmail.com", new BigDecimal(5), true);
        
        Post post = new Post(1L, "Ecossistema Java", "O ecossistema do Java é muito maduro", editor,
         "ecossistema-java-abc123", null, false, false);

        Ganhos actualGanhos = calculadoraGanhos.calcular(post);

        assertEquals(expectedGanhos, actualGanhos.getTotalGanho());
        assertEquals(expectedQuantidadePalavras, actualGanhos.getQuantidadePalavras());
        assertEquals(editor.getValorPagoPorPalavra(), actualGanhos.getValorPagoPorPalavra());

    }

    @Test
    void givenAPost_whenCalcular_thenReturnGanhosWithoutPremium() {

        BigDecimal expectedGanhos = new BigDecimal(35);
        int expectedQuantidadePalavras = 7;
        
        CalculadoraGanhos calculadoraGanhos = new CalculadoraGanhos(new ProcessadorTextoSimples(), BigDecimal.TEN);
        
        Editor editor = new Editor(1L, "Gabriel", "gabriel@gmail.com", new BigDecimal(5), false);
        
        Post post = new Post(1L, "Ecossistema Java", "O ecossistema do Java é muito maduro", editor,
         "ecossistema-java-abc123", null, false, false);

        Ganhos actualGanhos = calculadoraGanhos.calcular(post);

        assertEquals(expectedGanhos, actualGanhos.getTotalGanho());
        assertEquals(expectedQuantidadePalavras, actualGanhos.getQuantidadePalavras());
        assertEquals(editor.getValorPagoPorPalavra(), actualGanhos.getValorPagoPorPalavra());

    }

}
