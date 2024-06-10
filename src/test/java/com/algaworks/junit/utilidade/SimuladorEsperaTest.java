package com.algaworks.junit.utilidade;

import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import org.junit.jupiter.api.Test;

class SimuladorEsperaTest {

    @Test
    void esperar_withoutTimeout() {

        /*
         * Teste para verificar o tempo de execução do método.
         * 
         * Espera-se que o método seja executado em até 1s.
         * 
         * No trecho de código abaixo, o tempo de execução do método 'esperar', será maior
         * do que o tempo esperado de 1s.
         */

        // assertTimeout(Duration.ofSeconds(1),
        //  () -> SimuladorEspera.esperar(Duration.ofSeconds(10)));

        /*
         * O código acima tem uma "falha": o tempo de duração do teste está diretamente
         * atrelado a duração de execução do método. Ou seja, o teste só falha quando a
         * execução é concluída.
         * 
         * No entanto, se pararmos pra pensar, a partir do momento em que o código supera
         * o 1s esperado, o teste já deveria falhar e não ficar esperando, correto?
         * 
         * Para isso, utilizamos a Assertions abaixo. No momento em que o tempo de execução
         * superar o 1s esperado, o teste falhará imediatamente.
         */

        // assertTimeoutPreemptively(Duration.ofSeconds(1),
        //     () -> SimuladorEspera.esperar(Duration.ofSeconds(10)));

        /*
         * Agora testando o caso de sucesso...
         */

        assertTimeoutPreemptively(Duration.ofSeconds(1),
            () -> SimuladorEspera.esperar(Duration.ofMillis(10)));
    }

}
