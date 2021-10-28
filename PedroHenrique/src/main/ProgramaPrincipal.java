package main;

import java.util.List;
import java.util.Scanner;

import entidades.CarrinhoDeCompras;
import entidades.Item;
import entidades.Loja;
import entidades.Produto;

public class ProgramaPrincipal {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Loja loja = new Loja();
		CarrinhoDeCompras carrinho = new CarrinhoDeCompras();

		Produto p0 = new Produto(0, "CAMISETA CTRL + ALT + DEL", 39.90, 10);
		Produto p1 = new Produto(1, "CAMISETA AQUI TA FUNCIONANDO", 29.90, 10);
		Produto p2 = new Produto(2, "CAMISETA ESTRANHO...", 19.90, 10);
		Produto p3 = new Produto(3, "CAMISETA CONFIA!", 99.90, 10);

		List<Produto> produtos = loja.getProdutos();
		produtos.add(p0);
		produtos.add(p1);
		produtos.add(p2);
		produtos.add(p3);

		int qtd;
		String entrada2 = "s";

		
		System.out.println("\t============================================================================================ \n" );
		System.out.println("\t============================= Bem vindo ao Grupo3 - Mega Store ============================= \n" );
		System.out.println("\t=============================  A loja das camisetas de TI :D  ============================== \n" );
		System.out.println("\t============================================================================================ \n\n\n" );
		
		// ENQUANTO O USUARIO DIGITAR 's' NA entrada2 REPETE ESTE LAÇO WHILE
		while (entrada2.equals("s")) {
			System.out.println("\t\t\t   	TABELA DE PRODUTOS " + loja.getNome());
			System.out.println("\t---------+-----------------------------------------------+-----------------------+----------");
			System.out.println("\tCODIGO \t | \t\t\tNOME  \t\t\t | \tPREÇO DE VENDA \t |   ESTOQUE");
			System.out.println("\t---------+-----------------------------------------------+-----------------------+----------");

			for (Produto produto: produtos){
				int cod = produto.getCodigo();
			    String nome = produto.getNome();
				double preco = produto.getPreço();
				int estoque = produto.getEstoque();
				
	           	System.out.format("%12d \t |   %-40s \t | \t R$ %.2f \t | %6d %n", cod, nome, preco, estoque);
	           	System.out.println("\t---------+-----------------------------------------------+-----------------------+----------");
			}

			System.out.println();
			System.out.print("Digite o código do produto que deseja para adicioná-lo ao carrinho: ");

			int entrada = sc.nextInt();
			while (entrada < 0 || entrada > produtos.size() - 1) {
				System.out.print(
						"Entrada inválida, digite o código do produto que deseja para adicioná-lo ao carrinho: ");
				entrada = sc.nextInt();
			}
			System.out.println("==========================================================================================================");

			// FOR PARA PERCORRER DE 0 A 4 E CHECAR SE O USURÁRIO DIGITOU ALGUM DESSES CODIGOS
			for (int i = 0; i < loja.getProdutos().size(); i++) {
				if (entrada == i) {
					Produto produto = loja.getProdutos().get(i);
					if (produtos.get(i).getEstoque() > 0) {                                                             //CHECA SE TEM ESTOQUE
						System.out.print("Digite a quantidade de '" + produto.getNome() + "' que você deseja: ");
						qtd = sc.nextInt();
						while (qtd < 0 || qtd == 0) {
							System.out.print("Entrada inválida, digite uma quantidade até " + produtos.get(i).getEstoque() + ": ");
							qtd = sc.nextInt();
						}

						if (qtd > produtos.get(i).getEstoque()) {
							System.out.println("Em nosso estoque temos somente " + produtos.get(i).getEstoque()
									+ " unidades disponíveis do produto '" + produto.getNome() + "'");
							System.out.print("Deseja adicioná-los ao seu carrinho? (s/n): ");
							String entrada3 = sc.next();
							while (!entrada3.equals("n") && !entrada3.equals("s")) {
								System.out.print("Entrada inválida, digite 's' para adicionar ou 'n' para não adicionar: ");
								entrada3 = sc.next();
							}
							if (entrada3.equals("s")) {
								qtd = produtos.get(i).getEstoque();
								Item item = new Item(produto, qtd);
								carrinho.inserir(item);
								carrinho.somaCarrinho(produto, qtd);
								produtos.get(i).setEstoque(0);
								System.out.println(
										"==========================================================================================================");
								System.out.println(
										"Você adcionou " + qtd + " unidade(s) de " + produto.getNome() + " no seu carrinho!");
							}

						} else {
							Item item = new Item(produto, qtd);
							carrinho.inserir(item);
							carrinho.somaCarrinho(produto, qtd);
							produtos.get(i).setEstoque(produtos.get(i).getEstoque() - qtd);
							System.out.println(
									"==========================================================================================================");
							System.out.println(
									"Você adcionou " + qtd + " unidade(s) de '" + produto.getNome() + "' no seu carrinho!");
						}

					} else {
						System.out.println("Desculpe, estoque insuficiente de " + produto.getNome() + " no momento.");
					}
				}
			}
			System.out.println();
			System.out.printf("Valor total do Carrinho: R$%.2f %n", carrinho.getValorTotal());
			System.out.print("Deseja adicionar mais itens no carrinho? (s/n): ");
			entrada2 = sc.next();
			while (!entrada2.equals("n") && !entrada2.equals("s")) {
				System.out.print(
						"Entrada inválida, digite 's' para continuar comprando ou 'n' para ir à seção de pagamento: ");
				entrada2 = sc.next();
			}
			System.out.println("==========================================================================================================");
			
		}//FIM LAÇO WHILE

		// SEÇÃO DE PAGAMENTO
		System.out.println();
		System.out.println();
		System.out.println("           	      ~ Seção de pagamento " + loja.getNome() + " ~");
		System.out.println();
		System.out.println("Itens no seu carrinho: ");
		System.out.println();
		System.out.println("Produto 	      			 Preço unit.(R$)         Qtd no carrinho     Preço Total(R$)");
		System.out.println();

		for (Item item : carrinho.getItens()){
		    String nome = item.getProduto().getNome();
			double preco = item.getProduto().getPreço();
			int qtdCarrinho =  item.getQuantidade();
			double precoTotal = item.getProduto().getPreço() * item.getQuantidade();
			
           	System.out.format("%-35s \t    R$%.2f \t  \t  	%d \t  	   R$%.2f %n", nome, preco, qtdCarrinho, precoTotal);
           	System.out.println("==========================================================================================================");
		}

		System.out.println();
		double totalComImposto = (carrinho.getValorTotal() * 0.09) + carrinho.getValorTotal();
		System.out.printf("Valor total do Carrinho: R$%.2f %n", carrinho.getValorTotal());
		System.out.print("Valor total da compra com 9% de imposto: R$");
		System.out.printf("%.2f %n", totalComImposto);
		System.out.println("==========================================================================================================");
		System.out.println("Formas de pagamento: ");
		System.out.println();
		System.out.println("	[0] - À vista no dinheiro ou PIX (20% de desconto)");
		System.out.println("	[1] - À vista no cartão de crédito (10% de desconto)");
		System.out.println("	[2] - À vista no no cartão de Crédito Banco Pan (15% de desconto)");
		System.out.println("	[3] - Parcelado em até 3x sem juros");
		System.out.println("	[4] - Parcelado em mais de 3x com juros");
		System.out.println();
		System.out.print("Digite o código da forma de pagamento: ");

		int entrada = sc.nextInt();
		System.out.println();
		while (entrada < 0 || entrada > 4) {
			System.out.print("Entrada inválida, digite o código da forma de pagamento desejada(0 a 4): ");
			entrada = sc.nextInt();
		}
		System.out.println("==========================================================================================================");
		System.out.println();
		System.out.println();

		if (entrada == 0) {
			loja.notaFiscal(carrinho);
			loja.pagDinPix(carrinho.getValorTotal());
		} else if (entrada == 1) {
			loja.notaFiscal(carrinho);
			loja.pagAVistaCred(carrinho.getValorTotal());
		} else if (entrada == 2) {
			loja.notaFiscal(carrinho);
			loja.pagAVistaPan(carrinho.getValorTotal());
		} else if (entrada == 3) {
			System.out.println("Deseja parcelar em 2 ou 3 vezes? ");
			int parcela = sc.nextInt();
			loja.notaFiscal(carrinho);
			loja.pagSemJuros(carrinho.getValorTotal(), parcela);
		} else {
			System.out.println("Deseja parcelar em quantas vezes(entre 4 e 10)? ");
			int parcela = sc.nextInt();
			loja.notaFiscal(carrinho);
			loja.pagComJuros(carrinho.getValorTotal(), parcela);
		}

		System.out.println("O " + loja.getNome() + " AGRADECE PELA PREFERÊNCIA!");
		System.out.println("==========================================================================================================");
		System.out.println();
		sc.close();

	}
}
