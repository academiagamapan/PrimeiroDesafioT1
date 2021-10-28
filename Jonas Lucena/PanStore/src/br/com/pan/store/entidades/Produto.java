package br.com.pan.store.entidades;

import java.util.Objects;

public class Produto {

	private Integer id;
	private String nome;
	private Integer quantidade;
	private Double preco;
	private String marca;

	public Produto(Integer id, String nome, Integer quantidade, Double preco, String marca) {
		this.id = id;
		this.nome = nome;
		this.quantidade = quantidade;
		this.preco = preco;
		this.marca = marca;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public String getMarca() {
		return marca;
	}
	
	public boolean reduzirQuantidade(Integer quantidade) {
		if (this.getQuantidade() < quantidade) {
			return false;
		} else {
			this.quantidade -= quantidade;
			return true;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}
}
