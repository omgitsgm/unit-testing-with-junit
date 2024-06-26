package com.algaworks.junit.ecommerce;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class CarrinhoCompra {

	private final Cliente cliente;
	private final List<ItemCarrinhoCompra> itens;

	public CarrinhoCompra(Cliente cliente) {
		this(cliente, new ArrayList<>());
	}

	public CarrinhoCompra(Cliente cliente, List<ItemCarrinhoCompra> itens) {
		Objects.requireNonNull(cliente);
		Objects.requireNonNull(itens);
		this.cliente = cliente;
		this.itens = new ArrayList<>(itens); //Cria lista caso passem uma imutável
	}

	public List<ItemCarrinhoCompra> getItens() {
		// Deve retornar uma nova lista para que a antiga não seja alterada
		return List.copyOf(itens);
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void adicionarProduto(Produto produto, Integer quantidade) {

		// Parâmetros não podem ser nulos, deve retornar uma exception
		if(produto == null || quantidade == null)
			throw new NullPointerException("Parâmetros não podem ser null."); 
		
		// Quantidade não pode ser menor que 1
		if(quantidade < 1)
			throw new IllegalArgumentException("Quantidade não pode ser menor do que 1.");

		for(int i = 0; i < itens.size(); i++) {
			ItemCarrinhoCompra item = itens.get(i);
			
			// Deve incrementar a quantidade caso o produto já exista
			if(item.getProduto().equals(produto))
				item.adicionarQuantidade(1);
			else if(i == itens.size() - 1)
				itens.add(new ItemCarrinhoCompra(produto, quantidade));

		}

	}

	public void removerProduto(Produto produto) {
		// Parâmetro não pode ser nulo, deve retornar uma exception
		if(produto == null)
			throw new NullPointerException("Produto não pode ser null.");

		// Caso o produto não exista, deve retornar uma exception
		ItemCarrinhoCompra item = findItemByProduto(produto)
									.orElseThrow(() -> new RuntimeException("O produto não existe."));
		
		// Deve remover o produto independente da quantidade
		itens.remove(item);
	}

	public void aumentarQuantidadeProduto(Produto produto) {
		//TODO parâmetro não pode ser nulo, deve retornar uma exception
		//TODO caso o produto não exista, deve retornar uma exception
		//TODO deve aumentar em um quantidade do produto
	}

    public void diminuirQuantidadeProduto(Produto produto) {
		//TODO parâmetro não pode ser nulo, deve retornar uma exception
		//TODO caso o produto não exista, deve retornar uma exception
		//TODO deve diminuir em um quantidade do produto, caso tenha apenas um produto, deve remover da lista
	}

    public BigDecimal getValorTotal() {
		//TODO implementar soma do valor total de todos itens
		return null;
    }

	public int getQuantidadeTotalDeProdutos() {
		//TODO retorna quantidade total de itens no carrinho
		//TODO Exemplo em um carrinho com 2 itens, com a quantidade 2 e 3 para cada item respectivamente, deve retornar 5
		return 0;
	}

	public void esvaziar() {
		//TODO deve remover todos os itens
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CarrinhoCompra that = (CarrinhoCompra) o;
		return Objects.equals(itens, that.itens) && Objects.equals(cliente, that.cliente);
	}

	@Override
	public int hashCode() {
		return Objects.hash(itens, cliente);
	}

	private Optional<ItemCarrinhoCompra> findItemByProduto(Produto produto) {

		return itens.stream().filter(item -> item.getProduto().equals(produto)).findFirst();

	}

}