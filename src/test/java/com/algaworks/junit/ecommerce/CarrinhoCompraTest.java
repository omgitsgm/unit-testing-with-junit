package com.algaworks.junit.ecommerce;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

@DisplayName("Testes em CarrinhoCompra")
class CarrinhoCompraTest {

    static Cliente cliente;
    static Produto produto;
    static ItemCarrinhoCompra item;
    static CarrinhoCompra carrinho;

    static String parametrosNaoPodemSerNullMessage = "Parâmetros não podem ser null.";
    static String quantidadeMenorDoQueUmMessage = "Quantidade não pode ser menor do que 1.";


    @BeforeAll
    static void beforeAll() {

        cliente = new Cliente(1L, "Gabriel");
        produto = new Produto(1L, "Playstation 5", "O novo console da família Playstation.", new BigDecimal(3700));
        item = new ItemCarrinhoCompra(produto, 1);

    }


    @BeforeEach
    void beforeEach() {

        carrinho = new CarrinhoCompra(cliente, List.of(item));

    }


    @DisplayName("Dada uma instância de carrinho de compra.")
    @Nested
    class GivenAnInstanceOfCarrinhoCompra {

        @DisplayName("quando pegar itens.")
        @Nested
        class WhenGetItens {
            
            @DisplayName("retorne os itens do carrinho.")
            @Test
            void thenReturnItensFromCarrinho() {
                
                List<ItemCarrinhoCompra> expectedListOfItens = List.of(item);

                List<ItemCarrinhoCompra> actualItens = carrinho.getItens();

                assertIterableEquals(expectedListOfItens, actualItens);

            }
            
        }

        @DisplayName("quando adicionar produto ao carrinho.")
        @Nested
        class whenAdicionarProduto {

            @DisplayName("com produto null.")
            @Nested
            class withProdutoNull {

                @DisplayName("então lance uma NullPointerException.")
                @Test
                void thenThrowNullPointerException() {

                    Executable executable = () -> carrinho.adicionarProduto(null, 1);

                    NullPointerException exception = assertThrows(NullPointerException.class, executable);

                    assertEquals(parametrosNaoPodemSerNullMessage, exception.getMessage());

                }

            }
            
            @DisplayName("com quantidade null.")
            @Nested
            class withQuantidadeNull {

                @DisplayName("então lance uma NullPointerException.")
                @Test
                void thenThrowNullPointerException() {

                    Executable executable = () -> carrinho.adicionarProduto(produto, null);

                    NullPointerException exception = assertThrows(NullPointerException.class, executable);

                    assertEquals(parametrosNaoPodemSerNullMessage, exception.getMessage());

                }

            }

            @DisplayName("com quantidade menor do que 1.")
            @Nested
            class withQuantidadeLessThanOne {

                @DisplayName("então lance uma IllegalArgumentException.")
                @Test
                void thenThrowIllegalArgumentException() {

                    Executable executable = () -> carrinho.adicionarProduto(produto, 0);

                    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, executable);

                    assertEquals(quantidadeMenorDoQueUmMessage, exception.getMessage());

                }

            }

            @DisplayName("com produto que já está no carrinho.")
            @Nested
            class withProdutoJaExistente {

                @DisplayName("então incremente a quantidade do item no carrinho.")
                @Test
                void thenIncrementaQuantidade() {

                    int expectedQuantidade = 2;

                    carrinho.adicionarProduto(produto, 1);

                    assertEquals(expectedQuantidade, carrinho.getItens().get(0).getQuantidade());

                }

            }

            @DisplayName("com produto novo.")
            @Nested
            class withNovoProduto {

                static Produto novoProduto;

                @BeforeAll
                static void beforeAll() {
                    novoProduto = new Produto(7L, "Novo Produto", "Descrição do novo produto", new BigDecimal(1));
                }

                @DisplayName("então adicione o novo produto ao carrinho.")
                @Test
                void thenAdicioneNovoProduto() {

                    carrinho.adicionarProduto(novoProduto, 1);

                    assertEquals(novoProduto, carrinho.getItens().get(1).getProduto());

                }

                @DisplayName("então incremente o tamanho da lista de itens do carrinho.")
                @Test
                void thenUpdateListSize() {

                    int expectedSize = 2;

                    carrinho.adicionarProduto(novoProduto, 1);

                    assertEquals(expectedSize, carrinho.getItens().size());

                }

            }

        }

        @DisplayName("quando remover produto do carrinho.")
        @Nested
        class whenRemoverProduto {
            
            @DisplayName("com produto existente.")
            @Nested
            class withExistingProduto {

                @DisplayName("então remova o item do carrinho.")
                @Test
                void thenRemoveItemFromItensList() {

                    List<ItemCarrinhoCompra> expectedItens = List.of();

                    carrinho.removerProduto(produto);

                    assertEquals(expectedItens, carrinho.getItens());

                }

                @DisplayName("então diminua o tamanho da lista de itens.")
                @Test
                void thenDecreaseListOfItensSize() {

                    int expectedSize = 0;

                    carrinho.removerProduto(produto);

                    assertEquals(expectedSize, carrinho.getItens().size());

                }

            }

            @DisplayName("com produto inexistente.")
            @Nested
            class withUnexistingProduto {

                static Produto produtoInexistente;

                static String produtoInexistenteMessage = "O produto não existe.";

                @BeforeEach
                void beforeEach() {

                    produtoInexistente = new Produto(2L, "Xbox Series X", "O console mais poderoso da Microsoft", new BigDecimal(4000));

                }

                @DisplayName("então lance uma RuntimeException.")
                @Test
                void thenThrowRuntimeException() {

                    Executable executable = () -> carrinho.removerProduto(produtoInexistente);

                    RuntimeException exception = assertThrows(RuntimeException.class, executable);

                    assertEquals(produtoInexistenteMessage, exception.getMessage());

                }

                @DisplayName("então não diminua o tamanho da lista de itens.")
                @Test
                void thenDoNotDecreaseListOfItensSize() {

                    int expectedSize = 1;

                    try {
                        carrinho.removerProduto(produtoInexistente);
                    } catch (RuntimeException exception) {}

                    assertEquals(expectedSize, carrinho.getItens().size());

                }

            }


            @DisplayName("com produto null.")
            @Nested
            class withProdutoNull {

                static String produtoNaoPodeSerNullMessage = "Produto não pode ser null.";

                @DisplayName("então lance uma NullPointerException.")
                @Test
                void thenThrowNullPointerException() {

                    Executable executable = () -> carrinho.removerProduto(null);

                    NullPointerException exception = assertThrows(NullPointerException.class, executable);

                    assertEquals(produtoNaoPodeSerNullMessage, exception.getMessage());

                }

                @DisplayName("então não diminua o tamanho da lista de itens.")
                @Test
                void thenDoNotDecreaseListOfItensSize() {

                    int expectedSize = 1;

                    try {
                        carrinho.removerProduto(null);
                    } catch (RuntimeException exception) {}

                    assertEquals(expectedSize, carrinho.getItens().size());

                }

            }
        }

    }

    
}
