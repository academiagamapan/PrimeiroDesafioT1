package estoque;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Estoque {

	private Map<Produto, Integer> produtos;

	public Estoque() {
		this.produtos = new LinkedHashMap<Produto, Integer>();
	}

	public Map<Produto, Integer> getProdutos() {
		return produtos;
	}

	public void setProdutos(Map<Produto, Integer> produtos) {
		this.produtos = produtos;
	}

	public void addProduto(Produto p, int quantidade) {
			produtos.put(p, quantidade);
	}

	public void removerProduto(Produto produto, int quantidade) {

		produtos.replace(produto, produtos.get(produto) - quantidade);
	}

	public void listarProduto() {
		System.out.println("************************************************");
		System.out.println("**                PantsAcademy                **");
		System.out.println("**                                            **");
		System.out.println("************************************************");
		System.out.println("\nLista de Produtos em Estoque: ");
		System.out.println("ID\tProduto\t\t\tPreço\t\tQuant. Estoque");
		System.out.println();
		for (Produto p : produtos.keySet()) {
			System.out.printf("%-7d%-24s%-6.2f%20d%n", p.getId_produto(), p.getNome_produto(),p.getPreco_produto(), produtos.get(p));
		}
	}

	public void zerarLista() {
		produtos.clear();
	}

}
