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

## Importância dos Testes Unitários

* Testes manuais demoram e são chatos;
* Agiliza o teste de uma aplicação;
* Evitar que bugs ocorram ao alterar o código;
* Documenta as funcionalidades do sistema, assim como as regras;
* Os testes nos dão mais segurança na hora de refatorar;

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