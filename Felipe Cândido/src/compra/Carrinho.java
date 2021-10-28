package compra;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import estoque.Estoque;
import estoque.Produto;
import financeiro.NotaFiscal;

public class Carrinho {

	private Map<Produto, Integer> listaCarrinho = new HashMap<Produto, Integer>();
	private Estoque estoque;
	
	public Carrinho(Estoque estoque) {
		this.estoque = estoque;
	}
	
	public void adicionaItem(Produto produto, int quantidade) {
		listaCarrinho.put(produto, quantidade);
		System.out.println("-----------------------------");
		System.out.println("você adicionou " + quantidade + " " + produto.getNome_produto() + "(s)");
		System.out.println("### digite qualquer coisa para continuar ###");
		Scanner resposta = new Scanner(System.in);
		String pausa = resposta.next();
	}
	
	public Map<Produto, Integer> getListaCarrinho() {
		return listaCarrinho;
	}

	public void setListaCarrinho(Map<Produto, Integer> listaCarrinho) {
		this.listaCarrinho = listaCarrinho;
	}

	public void removeItem(Produto produto, int quantidade) {
		for(int i = 0; i<quantidade; i++) {
			listaCarrinho.remove(produto);			
		}
		System.out.println("você removeu " + quantidade + " " + produto.getNome_produto() +"(s)");
	}

	public void fecharCompra() {
		System.out.println("escolha a forma de pagamento:");
		System.out.println("1 - pagamento à vista (20% de desconto)");
		System.out.println("2 - crédito à vista (10% de desconto)");
		System.out.println("3 - crédito parcelado (sem desconto, com juros acima de 3 parcelas)");
		System.out.println("4 - crédito cartão pan (25% de desconto à vista e parcelado em até 12x sem juros)");
		
		Scanner pagamento = new Scanner(System.in);
		String formaPagamento;
		int nparcelas;
		
		switch (pagamento.nextInt()) {
			case 1:
				formaPagamento = "pvista";
				System.out.println("pix ou dinheiro ?");
				System.out.println("1 - pix");
				System.out.println("2 - dinheiro ?");
				Scanner escolhaDePagamento = new Scanner(System.in);
				int escolha = escolhaDePagamento.nextInt();
				if (escolha == 1) {
					System.out.println("sua compra foi fechada e sua nota será gerada");
					NotaFiscal.gerarNota(listaCarrinho, formaPagamento, 0, "pix");					
				}
				if (escolha == 2) {
					System.out.println("sua compra foi fechada e sua nota será gerada");
					NotaFiscal.gerarNota(listaCarrinho, formaPagamento, 0, "dinheiro");					
				}
				break;
			case 2:
				formaPagamento = "cvista";
				System.out.println("sua compra foi fechada e sua nota será gerada");
				NotaFiscal.gerarNota(listaCarrinho, formaPagamento, 0, "");				
				break;
			case 3:
				formaPagamento = "cparcelado";
				while(true) {
					System.out.println("quantas parcelas ?");
					Scanner parcelas = new Scanner(System.in);
					nparcelas = parcelas.nextInt();
				
					if(nparcelas >= 1 && nparcelas <= 10) {
						System.out.println("sua compra foi fechada e sua nota será gerada");
						NotaFiscal.gerarNota(listaCarrinho, formaPagamento, nparcelas, "");
						break;
					}else {
						System.out.println("Numero de parcelas inválido!");
						
					}
				}
				break;
			case 4:
				formaPagamento = "cpan";
				while(true) {
					System.out.println("quantas parcelas ?");
					Scanner panParcelas = new Scanner(System.in);
					nparcelas = panParcelas.nextInt();
					if(nparcelas >= 1 && nparcelas <= 12) {
						System.out.println("sua compra foi fechada e sua nota será gerada");
						NotaFiscal.gerarNota(listaCarrinho, formaPagamento, nparcelas, "");
						break;
					}else {
						System.out.println("Numero de parcelas inválido!");
						}
				}
				break;
		}
		
		pagamento.close();
		
	}
	
	public void cancelaCompra() {
		listaCarrinho.clear();
		System.out.println("sua compra foi cancelada, que pena :(");
	}
	
	public void limparTela() {

		try {
			Process process = Runtime.getRuntime().exec("cls");
		} catch (IOException e) {
			System.out.println("comando não suportado pelo sistema");
		}

//		try {
//			Process process = Runtime.getRuntime().exec("clear");
//		} catch (IOException e) {
//			System.out.println("comando não suportado pelo sistema");
//		}
		for (int i = 0; i < 50; ++i) System.out.println();
//	    System.out.print("\033[H\033[2J");  
//	    System.out.flush(); 
		
//        ProcessBuilder processBuilder = new ProcessBuilder();
//        // Windows
//        processBuilder.command("clear");
//
//        try {
//            Process process = processBuilder.start();
//            int exitCode = process.waitFor();
////            System.out.println("\nExited with error code : " + exitCode);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

	}
	
	public void iniciarCompra()  {
		
		int id = -1;
		int qntEscolhida = 0;

//		estoque.listarProduto();
		
		do {
			limparTela();
			estoque.listarProduto();
			

			System.out.println("-----------------------------");
			System.out.printf("> digite o id do produto e a quantidade desejada (digite 0 para concluir a compra)\n> ");
			Scanner resposta = new Scanner(System.in);
			
			id = resposta.nextInt();
			if (id == 0) break;
//			else {
				qntEscolhida = resposta.nextInt();
				for (Produto produtoEscolhido : estoque.getProdutos().keySet()) {
					if (produtoEscolhido.getId_produto() == id) {
							if(estoque.getProdutos().get(produtoEscolhido) >= qntEscolhida) {
								adicionaItem(produtoEscolhido, qntEscolhida);
								estoque.removerProduto(produtoEscolhido, qntEscolhida);
								break;
							} else {
								System.out.println("\nProduto fora de estoque!");
							}
					}
				}
//			}
		}while(id != 0);
		fecharCompra();
	}

}
