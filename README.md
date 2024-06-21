# ✅ Unit Testing with JUnit
A repository for the course "Testes Unitários com JUnit" from Algaworks.

> <a href="https://github.com/algaworks/curso-testes-unitarios-junit-projeto">Clique aqui</a> para acessar o repositório base da _Algaworks_ que foi utilizado como referência para esse repositório.

## Pirâmide de Testes

| Tipo de Teste | Complexidade | Custo  | Tempo de execução |
|-------------- | ------------ | ------ | ----------------- |
| E2E           | High         | High   | High              |
| Integration   | Medium       | Medium | Medium            |
| Unit          | Low          | Low    | Low               |

| Tipo de Teste | Volume de Testes Esperados na Aplicação |
| ------------- | --------------------------------------- |
| Unit          | High                                    |
| Integration   | Medium                                  |
| E2E           | Low                                     |

**💻 E2E**: O teste E2E (end-to-end ou ponta-a-ponta), é o mais complexo, custoso e o que leva mais tempo para ser executado, pois é um tipo de teste que cobre todas as camadas de um sistema.  
Para criar um teste E2E, é necessário que todas as camadas da aplicação já estejam funcionando, ou seja, é necessária uma infraestrutura relativamente maior do que a de testes unitários ou de integração. Esse fator implica diretamente em fatores como custo e tempo de execução.  
Além disso, devido a sua complexidade, espera-se que os testes E2E representem a menor parcela de testes em uma aplicação.

**🤝 Integration**: testa a interação entre os componentes de um sistema. Os componentes de um sistema podem ser classes, banco de dados ou API's externas, por exemplo. Um exemplo de teste de integração é testar se uma classe repository está se comunicando corretamente com o banco de dados.

**🐺 Unit**: tem como objetivo testar um componente único de um sistema, como uma classe, por exemplo. Não deve receber influência de outros componentes do sistema. Deve ser feito durante o desenvolvimento. Os testes unitários devem ser concisos e com objetivos bem definidos.

---

## Importância dos Testes Unitários

* Testes manuais demoram e são chatos;
* Agiliza o teste de uma aplicação;
* Evitar que bugs ocorram ao alterar o código;
* Documenta as funcionalidades do sistema, assim como as regras;
* Os testes nos dão mais segurança na hora de refatorar;

---

## O princípio F.I.R.S.T.

_O que é F.I.R.S.T.?_

<pre>
    F (Fast)           : Testes unitários **devem ser rápidos**.  
    I (Independent)    : Testes unitários **devem ser independentes**.  
    R (Repeatable)     : Testes unitários **devem permitir repetição**.  
    S (Self-validating): Testes **devem ter autoavaliação**.  
    T (Timely)         : Testes **devem ser escritos junto ao desenvolvimento**.  
    T (Thorough)       : Testes **devem ser minuciosos**.  
</pre>

> Um conjunto de características esperadas em **testes unitários de qualidade**.

---

## Padrão Triple A

> O método de teste deve ser dividido em 3 partes: **_arrange_**, **_act_** and **_assert_**.

Essa é uma forma de organizar os seus testes unitários.

Exemplo:

<pre>
<code>
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
</code>
</pre>

Exemplo onde realizamos o Assert de uma Exception:
> A estratégia aqui, é extrair a execução do método para um Executable, ao invés de passar a execução do método diretamente dentro do assertThrows().
<pre>
<code>
    @Test
    void saudar_withInvalidHora_throwsIllegalArgumentException() {

        // Arrange
        int invalidHora = -10;

        // Act
        Executable executable = () -> SaudacaoUtil.saudar(invalidHora);

        // Assert
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, executable);
        assertEquals("Hora inválida", illegalArgumentException.getMessage());
    }
</code>
</pre>

---

## Nomenclatura BDD para testes unitários

> Nomenclatura para métodos de testes unitários baseados no BDD (_Behavior Driven Development_)

Estrutura base:

<pre>
<code>
    @Test
    given_when_then(){
        // your code here...
    }
</code>
</pre>

Exemplo - **sem nomenclatura BDD**:
<pre>
<code>
    @Test
    @DisplayName("Deve retornar apenas números pares quando filtrar por números pares.")
    void numerosPares_withArrayOfNumbers_returnsOnlyEvenNumbers() {
        List<Integer> numbers = Arrays.asList(1,2,3,4);
        List<Integer> expectedEvenNumbers = Arrays.asList(2, 4);

        List<Integer> actualNumbers = FiltroNumeros.numerosPares(numbers);

        Assertions.assertIterableEquals(expectedEvenNumbers, actualNumbers);
    }
</code>
</pre>

Exemplo - **com nomenclatura BDD**:
<pre>
<code>
    @Test
    @DisplayName("Deve retornar apenas números pares quando filtrar por números pares.")
    void givenAListOfNumbers_whenFilterByEvenNumbers_thenReturnOnlyEvenNumbers() {
        List<Integer> numbers = Arrays.asList(1,2,3,4);
        List<Integer> expectedEvenNumbers = Arrays.asList(2, 4);

        List<Integer> actualNumbers = FiltroNumeros.numerosPares(numbers);

        Assertions.assertIterableEquals(expectedEvenNumbers, actualNumbers);
    }
</code>
</pre>

---

## Devemos ter apenas 1 asserção por teste?

> Essa é uma discussão interessante.

Por vezes, a resposta para essa pergunta irá depender muito do que estamos testando. No entanto, de uma maneira geral, por questões de organização,
legibilidade e clareza, sempre que possível **opte por realizar apenas 1 asserção por teste**.

_Observe o exemplo abaixo:_

<pre>
<code>
    @Test
    void givenAPost_whenCalcular_thenReturnGanhos() {

        BigDecimal expectedGanhos = new BigDecimal(45);
        int expectedQuantidadePalavras = 7;

        Ganhos actualGanhos = calculadoraGanhos.calcular(post);

        assertEquals(expectedGanhos, actualGanhos.getTotalGanho());
        assertEquals(expectedQuantidadePalavras, actualGanhos.getQuantidadePalavras());
        assertEquals(editor.getValorPagoPorPalavra(), actualGanhos.getValorPagoPorPalavra());

    }
</code>
</pre>

No código acima estamos testando o método calcular de CalculadoraGanhos. Quando esse método é executado, nós esperamos que, em um caso de sucesso, ele nos retorne:
* O total de ganhos esperados por um post;
* A quantidade de palavras utilizadas em um post;
* O valor pago por palavra ao editor do post;

Neste caso não é _tão_ difícil entender o que está sendo testado aqui apenas lendo o código, mas uma coisa é certa: podemos tornar tudo mais simples e objetivo. 
**_Então por quê não fazer isso?_**

_Observe o exemplo abaixo:_

<pre>
<code>

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

</code>
</pre>

A partir de uma única função de teste, nós fomos capazes de gerar três outros testes que são **mais claros, objetivos, legíveis e que _documentam muito melhor_ o comportamento da função calcular.**


### Quando eu não uso uma única asserção por teste?

Embora eu considere que seja muito melhor usar 1 asserção por teste, eu não acho que esso seja uma regra absoluta na escrita de testes unitários.

_Observe o exemplo abaixo:_

<pre>
<code>
    @Test
    @DisplayName("Deve lançar IllegalArgumentException ao criar conta com saldo null.")
    void contaBancaria_withSaldoEqualsToNull_throwsIllegalArgumentException() {

        BigDecimal invalidSaldo = null;
        String expectedExceptionMessage = saldoCannotBeNullMessage;

        Executable executable = () -> new ContaBancaria(invalidSaldo);

        IllegalArgumentException illegalArgumentException = 
            assertThrows(IllegalArgumentException.class, executable, "Saldo inválido.");

        assertEquals(expectedExceptionMessage, illegalArgumentException.getMessage());

    }
</code>
</pre>

Aqui, podemos ver que eu uso dois tipos de asserções: ``assertThrows`` e ``assertEquals``.  
Nesse tipo de situação, é **válido** usar duas asserções, porque além de checar se o método construtor de ContaBancaria está jogando uma exceção quando recebe um saldo inválido como argumento, eu estou checando se a mensagem dessa exceção está de acordo com a mensagem esperada. Eu considero que, aqui, temos duas asserções **complementares entre si** e ambas trabalham em conjunto para testar uma única coisa, que é o lançamento de uma exceção com a mensagem correta. Sem a exceção não existe a mensagem de uma exceção e é por isso que faz sentido as asserções estarem juntas.