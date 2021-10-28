package main;

import java.util.ArrayList;
import java.util.List;

import compra.Carrinho;
import estoque.Estoque;
import estoque.Produto;

public class PantsAcademy {

	public static void main(String[] args) {
		Estoque estoque1 = new Estoque();
		
		List<Produto> listaDeProdutos = new ArrayList<Produto>();
		
		listaDeProdutos.add(new Produto(1,"Camisa United",300.00));
		listaDeProdutos.add(new Produto(2,"Camisa City",250.00));
		listaDeProdutos.add(new Produto(3,"Camisa Barça",150.00));
		listaDeProdutos.add(new Produto(4,"Camiseta", 49.00));
		listaDeProdutos.add(new Produto(5,"Camiseta Regata", 39.00));
		listaDeProdutos.add(new Produto(6,"Camiseta Manga Longa",69.00));
		listaDeProdutos.add(new Produto(7,"Camiseta Polo",59.00));
		listaDeProdutos.add(new Produto(8,"Bermuda",49.00));
		listaDeProdutos.add(new Produto(9,"Bermuda Jeans",50.00));
		listaDeProdutos.add(new Produto(10,"Calca Jeans",80.00));
		listaDeProdutos.add(new Produto(11,"Moleton",75.00));
		listaDeProdutos.add(new Produto(12,"Chinelo",29.00));
		listaDeProdutos.add(new Produto(13,"Tenis",200.00));
		
		for (Produto produto : listaDeProdutos) {
			estoque1.addProduto(produto, (int)(10*Math.random()));
		}
		
		Carrinho carrinho = new Carrinho(estoque1);
		
		carrinho.iniciarCompra();
	}

}
