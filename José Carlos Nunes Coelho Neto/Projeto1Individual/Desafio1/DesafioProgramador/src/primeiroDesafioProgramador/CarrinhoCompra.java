package primeiroDesafioProgramador;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class CarrinhoCompra extends Produtos{

	
	private int quantidadeCarrinho;
	private Double valorTotal;
	
	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public int getQuantidadeCarrinho() {
		return quantidadeCarrinho;
	}

	public void setQuantidadeCarrinho(int quantidadeCarrinho) {
		this.quantidadeCarrinho = quantidadeCarrinho;
	}
	
	public static String design(String nome) {
		return nome = nome + " ".repeat(30 - nome.length());
	}

	public ArrayList<CarrinhoCompra> adicionaProduto(Produtos produto, ArrayList<CarrinhoCompra> produtosCarrinho) {		
		ArrayList<CarrinhoCompra> produtoCarrinho = new ArrayList<CarrinhoCompra>();
		produtoCarrinho = produtosCarrinho;
		int i = 0;
		int j = 0;
		int quantidade;
		boolean temProduto = false;
		if (produtoCarrinho.isEmpty()) {			
		} else {
			while (i < produtoCarrinho.size()) {
				if (produtoCarrinho.get(i).getNome().equals(produto.getNome())) {
					quantidade = produtoCarrinho.get(i).getQuantidadeCarrinho() + 1;
					produtoCarrinho.get(i).setQuantidadeCarrinho(quantidade);
					j = i;
					temProduto = true;
				}
				i = i + 1;
			}
		}				
		if (temProduto == false) {
			CarrinhoCompra produtos = new CarrinhoCompra();
			produtos.setNome(produto.getNome());
			produtos.setPreco(produto.getPreco());
			produtos.setQuantidade(produto.getQuantidade());
			produtos.setQuantidadeCarrinho(1);
			produtoCarrinho.add(produtos);
			int indice = produtoCarrinho.size()-1;
			produtoCarrinho.get(indice).setQuantidade(1);
		}		
		return produtoCarrinho;
	}
	
	public String exibeCarrinho(ArrayList<CarrinhoCompra> produtoCarrinho) {
		String listaProdutos;
		if (produtoCarrinho.isEmpty()) {
			listaProdutos = "===O carrinho de compras está vazio!===\n"
							+ "      Por favor, continue nas compras!";
		} else {
			listaProdutos = "==============Lista de Produtos no Carrinho de Compras==============\n"
					 + "N°     Nome                     Quantidade               Valor Total\n";
			String design = "                            ";		
			int escolhaProduto;
			int i = 1;
			for (CarrinhoCompra produtos : produtoCarrinho) {
				Double valorTotal = produtos.getPreco() * produtos.getQuantidadeCarrinho();
				int tamanhoDesignNome = produtos.getNome().length();
				int tamanhoDesignPreco = Integer.toString(produtos.getQuantidade()).length();
				listaProdutos = listaProdutos +i+"° - "+design(produtos.getNome())
												+design(Integer.toString(produtos.getQuantidadeCarrinho()))
												+"R$ "+Double.toString(valorTotal)+"\n";
				i = i + 1;
				}			
		}			
		return listaProdutos;
	}
	
	public ArrayList<CarrinhoCompra> removeProduto (ArrayList<CarrinhoCompra> produtosCarrinho, int indice, int opcao) {
		
		ArrayList<CarrinhoCompra> produtoCarrinho = new ArrayList<CarrinhoCompra>();
		produtoCarrinho = produtosCarrinho;
		int quantidade;
		if (opcao == 1) {
			quantidade = produtoCarrinho.get(indice).getQuantidadeCarrinho() - 1;
			produtoCarrinho.get(indice).setQuantidadeCarrinho(quantidade);			
		} else if (opcao == 2) {
			produtoCarrinho.remove(indice);			
		} else {
			
		}
		return produtoCarrinho;
	}
	
	public Double valorTotalCarrinho(ArrayList<CarrinhoCompra> produtosCarrinho) {
		Double valorTotal = 0.0;
		Double valor = 0.0;
		for (CarrinhoCompra carrinhoCompra : produtosCarrinho) {
			valor = carrinhoCompra.getQuantidadeCarrinho() * carrinhoCompra.getPreco();
			valorTotal = valorTotal + valor;
		}
		
		return valorTotal;
	}
	
}
