package main;

import java.util.List;
import java.util.Scanner;

import entidades.CarrinhoDeCompras;
import entidades.Item;
import entidades.Loja;
import entidades.Produto;

public class ProgramaPrincipal {

	public static void main(String[] args) {
		Loja loja = new Loja();

		Produto p0 = new Produto(0, "CAMISETA CTRL + ALT + DEL", 50.00, 10);
		Produto p1 = new Produto(1, "CAMISETA AQUI TA FUNCIONANDO", 50.00, 10);
		Produto p2 = new Produto(2, "CAMISETA ESTRANHO", 50.00, 10);
		Produto p3 = new Produto(3, "CAMISETA CONFIA!", 50.00, 10);

		List<Produto> produtos = loja.getProdutos();
		produtos.add(p0);
		produtos.add(p1);
		produtos.add(p2);
		produtos.add(p3);

		CarrinhoDeCompras carrinho = new CarrinhoDeCompras();

		Scanner sc = new Scanner(System.in);
		int qtd;

		String entrada2 = "s";

		while (entrada2.equals("s")) {
			System.out.println("\t============================================================================================ \n" );
			System.out.println("\t============================= Bem vindo ao Grupo3 - Mega Store ============================= \n" );
			System.out.println("\t=============================  A loja das camisetas de TI :D  ============================== \n" );
			System.out.println("\t============================================================================================ \n\n\n" );
			
			System.out.println("\t---------+-----------------------------------------------+-----------------------+----------");
			System.out.println("\tCODIGO \t | \t\t\tNOME  \t\t\t | \tPREÇO DE VENDA \t |   ESTOQUE");
			System.out.println("\t---------+-----------------------------------------------+-----------------------+----------");
			for (Produto products: produtos){
				int cod = products.getCodigo();
			    String nome = products.getNome();
				double preco = products.getPreço();
				int estoque = products.getEstoque();
				
	           	System.out.format("%12d \t |   %-40s \t | \t R$ %.2f \t | %6d %n", cod, nome, preco, estoque);
	           	System.out.println("\t---------+-----------------------------------------------+-----------------------+----------");
			}
			System.out.println();
			System.out.print("Digite o código do produto que deseja para adicioná-lo ao carrinho: ");

			int entrada = sc.nextInt();
			System.out.println("===================================================================================");
			while (entrada < 0 || entrada > produtos.size() - 1) {
				System.out.print(
						"Entrada inválida, digite o código do produto que deseja para adicioná-lo ao carrinho: ");
				entrada = sc.nextInt();
			}

			for (int i = 0; i < loja.getProdutos().size(); i++) {
				if (entrada == i) {
					Produto produto = loja.getProdutos().get(i);
					if (produtos.get(i).getEstoque() > 0) {
						System.out.print("Digite a quantidade de " + produto.getNome() + " que você deseja: ");
						qtd = sc.nextInt();
						while (qtd > produtos.get(i).getEstoque() || qtd < 0 || qtd == 0) {
							System.out.print("Entrada inválida ou estoque insuficiente de " + produto.getNome()
									+ ", digite uma quantidade até " + produtos.get(i).getEstoque() + ": ");
							qtd = sc.nextInt();
						}
						Item item = new Item(produto, qtd);
						carrinho.inserir(item);
						carrinho.somaCarrinho(produto, qtd);
						produtos.get(i).setEstoque(produtos.get(i).getEstoque() - qtd);
						System.out.println("===================================================================================");
						System.out.println(
								"Você adcionou " + qtd + " unidade(s) de " + produto.getNome() + " no seu carrinho!");
					} else {
						System.out.println("Desculpe, estoque insuficiente de " + produto.getNome() + " no momento.");
					}
				}
			}
			System.out.println();
			System.out.printf("Valor total do Carrinho: R$%.2f %n", carrinho.getValorTotal());
			System.out.print("Deseja adicionar mais itens no carrinho? (s/n): ");
			entrada2 = sc.next();
			System.out.println("===================================================================================");
			while (!entrada2.equals("n") && !entrada2.equals("s")) {
				System.out.println(
						"Entrada inválida, digite 's' para continuar comprando ou 'n' para ir à sessão de pagamento: ");
				entrada2 = sc.next();
			}
		}
		
		System.out.println();
		System.out.println();
		System.out.println("           	          ~ Sessão de pagamento " + loja.getNome() + " ~");
		System.out.println();
		System.out.println("Itens no seu carrinho: ");
		System.out.println();
		System.out.println("Produto 	       Preço unit.(R$)         Qtd no carrinho     Preço Total(R$)");
		for (Item item : carrinho.getItens()) {
			System.out.printf("%s                      %.2f                       %d              %.2f %n",
					item.getProduto().getNome(),item.getProduto().getPreço(),item.getQuantidade(),(item.getProduto().getPreço() * item.getQuantidade()));
				System.out.println("===================================================================================");
		}
		
		System.out.println();
		double totalComImposto = (carrinho.getValorTotal() * 0.09) + carrinho.getValorTotal();
		System.out.printf("Valor total do Carrinho: R$%.2f %n", carrinho.getValorTotal());
		System.out.print("Valor total da compra com 9% de imposto: R$");
		System.out.printf("%.2f %n", totalComImposto);
		System.out.println("===================================================================================");
		System.out.println("Formas de pagamento: ");
		System.out.println("	[0] - À vista no dinheiro ou PIX (20% de desconto)");
		System.out.println("	[1] - À vista no cartão de crédito (10% de desconto)");
		System.out.println("	[2] - À vista no no cartão de Crédito Banco Pan (15% de desconto");
		System.out.println("	[3] - Parcelado em até 3x sem juros");
		System.out.println("	[4] - Parcelado em mais de 3x com juros");
		System.out.println();
		System.out.print("Digite o código da forma de pagamento: ");
		int entrada = sc.nextInt();
		System.out.println();
		while (entrada < 0 || entrada > 4) {
			System.out.print(
					"Entrada inválida, digite o código do produto que deseja para adicioná-lo ao carrinho: ");
			entrada = sc.nextInt();
		}
		System.out.println("===================================================================================");
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
		
		System.out.println("O PAN STORE AGRADECE A PREFERÊNCIA!");
		System.out.println("===================================================================================");
		sc.close();
	}
}
