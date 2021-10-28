package LojaVirtualFilipe;

import java.util.ArrayList;
import java.util.Scanner;

public class Util {
	
	// MOSTRAR TABELA DE PRODUTOS DO SUPERMERCADO
	public static void mostrarProdutos(ArrayList<Produto> produtos) {
		System.out.println("\n==================================================================");
		System.out.println("                      Supermercado PANdeLó                      ");
		System.out.println("==================================================================");
		System.out.println("COD\t" + "\tPRODUTO" + "\t\t\tESTOQUE" + "\t\t PREÇO/UNI");
		for (Produto p : produtos) {
			System.out.printf("%s \t\n", p);
		}
		System.out.println("==================================================================");
	}
	
	// MOSTRAR TABELA DE PRODUTOS DO CARRINHO E CÁLCULA O TOTAL DA COMPRA
	public static double calculaCarrinho(ArrayList<Produto> carrinho) {
		double total = 0.0;
		System.out.println("\n===================================================================================");
		System.out.println("                                     CARRINHO                                     ");
		System.out.println("===================================================================================");
		System.out.println("COD\t" + "\tPRODUTO" + "\t\t\tQUANTIDADE" + "\tPREÇO/UNI" + "\tVALOR TOTAL");
		for (Produto p : carrinho) {
			total += p.getPrecounit() * p.getQuantidade();
			System.out.printf("%s \t R$ %.2f\n", p, p.getPrecounit() * p.getQuantidade());
		}
		System.out.println("===================================================================================");
		System.out.printf("TOTAL: R$ %.2f\n", total);
		return total;
	}
	
	// REMOVER PRODUTO DO CARRINHO
	@SuppressWarnings("resource")
	public static boolean removerProduto(ArrayList<Produto> carrinho) {
		System.out.println("Digite o código do produto para ser removido: ");
		int i = (new Scanner(System.in)).nextInt();
		for (Produto produto : carrinho) {
			if (produto.getCodigo() == i) {
				Produto pRemovido = produto;
				carrinho.remove(produto);
				System.out.printf("Produto removido do carrinho: %s\n", pRemovido.getNomeProduto());
				Util.calculaCarrinho(carrinho);
				return true;
			}
		}
		System.out.println("Produto não existe no carrinho!");
		return false;
	}
	
	// ALTERAR A QUANTIDADE DE ALGUM PRODUTO SELECIONADO
	@SuppressWarnings("resource")
	public static boolean alterarQuantidade(ArrayList<Produto> carrinho, ArrayList<Produto> produtos) {
		System.out.println("Digite o código do produto para ser alterado: ");
		int i = (new Scanner(System.in)).nextInt();
		boolean ver = true;
		for (Produto produto : carrinho) {
			if (produto.getCodigo() == i) {
				ver = false;
				System.out.print("Estoque disponível: " + produtos.get(i).getQuantidade());
				int quantidade = 0;
				do {
					System.out.printf("\nDigite a nova quantidade: ");
					quantidade = (new Scanner(System.in)).nextInt();
					if (quantidade > produtos.get(i).getQuantidade() + produto.getQuantidade()) {
						System.out.println("Quantidade indisponivel");
					}
				} while (quantidade > produtos.get(i).getQuantidade() + produto.getQuantidade());
				produtos.get(i).setQuantidade(produtos.get(i).getQuantidade() + produto.getQuantidade() - quantidade);
				produto.setQuantidade(quantidade);
				return true;
			}
		}
		if (ver) {
			System.out.println("Código não localizado...");
		}
		return false;
	}
	
}
