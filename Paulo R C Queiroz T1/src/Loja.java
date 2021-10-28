import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Scanner;

public class Loja {

	static LinkedList<Produto> estoque = new LinkedList<>();
	static LinkedList<Produto> carrinhoCompras = new LinkedList<>();

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		criarEstoque(estoque);
		verMenuPrincipal(in);

	}

	public static void verMenuPrincipal(Scanner in) {

		div("Gama PAN Supermarket");
		System.out.println("[1] - Ver Produtos");
		System.out.println("[2] - Ver Carrinho");
		System.out.println("[0] - Sair");
		System.out.println("Digite a opção desejada");
		int opcao = in.nextInt();

		if (opcao == 1) {
			verProduto(in);
		} else if (opcao == 2) {
			verCarrinho(in);
		} else if (opcao == 0) {
			System.out.println("Volte sempre!");
			System.exit(0);
		} else {
			System.out.println("Opção inválida");
			verMenuPrincipal(in);
		}
	}

	public static void verProduto(Scanner in) {
		div("Produtos disponíveis");
		System.out.format("%2s%20s%22s%20s%n", "ID", "Produto", "Preço Unit.", "Quantidade");

		for (Produto x : estoque) {
			System.out.format("%2d%20s%22.2f%20d%n", x.getIdProduto(), x.getNome(), x.getPreco(), x.getQuantidade());
		}

		div("");
		System.out.println(
				"\nDigite a opção desejada\n[ID] para adicionar produto desejado\n[0] para ir para o carrinho.\n[-1] para retornar ao menu principal.");
		int prod = in.nextInt();

		if (prod > 0 && prod <= estoque.size()) {
			System.out.println("Qual quantidade deseja adicionar?");
			int quantidadeRemover = in.nextInt();

			if (estoque.get(prod - 1).getQuantidade() >= quantidadeRemover) {
				Produto produto = new Produto(estoque.get(prod - 1).getIdProduto(), estoque.get(prod - 1).getNome(),
						estoque.get(prod - 1).getPreco(), estoque.get(prod - 1).getQuantidade());
				produto.setQuantidade(quantidadeRemover);
				carrinhoCompras.add(produto);
				carrinhoCompras.getLast().setIdProduto(prod);
				estoque.get(prod - 1).decrementarQuantidade(quantidadeRemover);
				System.out.println("Produto adicionado com sucesso!Precione a tecla Enter para continuar.");
				verProduto(in);
			} else {
				System.out.println("Quantidade indisponível!Precione a tecla Enter para continuar.");
				verProduto(in);
			}

		} else if (prod == 0) {
			verCarrinho(in);

		} else if (prod == -1) {
			verMenuPrincipal(in);

		} else {
			System.out.println("Valor incorreto! Por Favor digite um valor entre 1 e " + estoque.size());
			verProduto(in);
		}
	}

	public static void verCarrinho(Scanner in) {

		div("Carrinho de compras");
		System.out.format("%2s%20s%22s%20s%20s%n", "ID", "Produto", "Preço Unit.", "Quantidade", "Total");

		for (Produto x : carrinhoCompras) {
			System.out.format("%2d%20s%22.2f%20d%20.2f%n", x.getIdProduto(), x.getNome(), x.getPreco(), x.getQuantidade(),
					x.getPreco() * x.getQuantidade());
		}
		div("");
		System.out.println(
				"\nDigite a opção desejada:\n[1] Para pagamento.\n[2] Para remover algum produto de seu carrinho.\n[3] Continuar comprando.\nOu qualquer outra tecla para retornar ao menu principal.");
		int opcao = in.nextInt();

		if (opcao == 1) {
			pagamento(in);
		} else if (opcao == 2) {
			removerProduto(in);
		} else if (opcao == 3) {
			verProduto(in);
		} else {
			verMenuPrincipal(in);
		}

	}

	private static void removerProduto(Scanner in) {

		System.out.println("Digite o ID do produto que deseja remover.");
		int prod = in.nextInt();

		boolean found = false;
		Produto achado = carrinhoCompras.getFirst();
		for (Produto x : carrinhoCompras) {
			if (prod == x.getIdProduto()) {
				found = true;
				achado = x;
			}
		}

		if (!found) {
			System.out.println("Valor incorreto! Por favor, digite um valor de ID válido.");
			verCarrinho(in);
		} else {
			System.out.println("Qual quantidade deseja remover?");
			int quantidadeRemover = in.nextInt();

			if (achado.getQuantidade() >= quantidadeRemover) {
				achado.decrementarQuantidade(quantidadeRemover);
				if (achado.getQuantidade() == 0) {
					carrinhoCompras.remove(achado);
				}
				for (Produto x : estoque) {
					if (x.getIdProduto() == achado.getIdProduto()) {
						x.incrementarQuantidade(quantidadeRemover);
					}
				}
				System.out.println("Produto removido com sucesso!");
				verCarrinho(in);
			} else {
				System.out.println("Quantidade inválida!");
				verCarrinho(in);
			}
		}

//		if (prod > 0 && prod <= carrinhoCompras.size()) {
//			System.out.println("Qual quantidade deseja remover?");
//			int quantidadeRemover = in.nextInt();
//
//			if (carrinhoCompras.get(prod - 1).getQuantidade() >= quantidadeRemover) {
//				carrinhoCompras.get(prod - 1).decrementarQuantidade(quantidadeRemover);
//				estoque.get(carrinhoCompras.get(prod - 1).getIdProduto() - 1).incrementarQuantidade(quantidadeRemover);
//				System.out.println("Produto removido com sucesso!");
//				verCarrinho(in);
//			} else {
//				System.out.println("Quantidade inválida!");
//				verCarrinho(in);
//			}
//
//		} 
//		else {
//			System.out.println("Valor incorreto! Por Favor digite um valor entre 1 e " + carrinhoCompras.size());
//			verCarrinho(in);
//		}
	}

	private static void pagamento(Scanner in) {

		double total = 0;
		int parcelas = 1;

		for (Produto x : carrinhoCompras) {
			total += x.getPreco() * x.getQuantidade();
		}

		div("Pagamento");
		for (Produto x : carrinhoCompras) {
			if (x.getQuantidade() != 0) {
				System.out.format("%10s%22.2f%20d%20.2f%n", x.getNome(), x.getPreco(), x.getQuantidade(),
						x.getPreco() * x.getQuantidade());
			}
		}
		System.out.printf("\nO valor total da compra foi de R$%.2f%n", total);
		System.out.println("\nDigite a opção desejada:");
		System.out.println("[1]Cartão Banco PAN \n[2] PIX ou Dinheiro\n");

		int opcao = in.nextInt();

		if (opcao == 1) {
			System.out.println("[1] À vista - 15% de desconto\n[2] 3x sem juros\n[3] Parcelado em até 6x(1.5% a.m)");
			int parcelamento = in.nextInt();

			if (parcelamento == 1) {
				total *= 0.85;
				parcelas = 1;
				notaFiscal(total, parcelas, parcelamento, in);
			} else if (parcelamento == 2) {
				total /= 3;
				parcelas = 3;
				notaFiscal(total, parcelas, parcelamento, in);
			} else if (parcelamento == 3) {
				total *= 1.09344;
				total /= 6;
				parcelas = 6;
				notaFiscal(total, parcelas, parcelamento, in);
			} else {
				System.out.println("Opção inválida!");
				pagamento(in);
			}

		} else if (opcao == 2) {
			notaFiscal(total, parcelas, opcao, in);
		}
	}

	private static void notaFiscal(double total, int parcelas, int opcao, Scanner in) {
		div("");
		System.out.println("Gama PAN Supermarket \nAv. dos Programadores nº6\nCNPJ: 11.123.456/0001-12");
		System.out.println("Data da compra:" + getDateTime());

		div("Nota Fiscal");

		System.out.format("%10s%22s%20s%20s%n", "Produto", "Preço Unit.", "Quantidade", "Total");

		for (Produto x : carrinhoCompras) {
			if (x.getQuantidade() != 0) {
				System.out.format("%10s%22.2f%20d%20.2f%n", x.getNome(), x.getPreco(), x.getQuantidade(),
						x.getPreco() * x.getQuantidade());
			}
		}
		if (opcao == 1) {
			System.out.printf("%nDesconto na compra: R$ %.2f%n", total / 0.85 * 0.15);

		} else if (opcao == 2 || opcao == 3) {
			System.out.printf("%nValor parcelado a ser pago: %dx R$ %.2f%n", parcelas, total);
		}
		System.out.printf("Valor total a ser pago: R$ %.2f%n", total * parcelas);
		System.out.printf("Valor do imposto: R$ %.2f%n", total * parcelas * 0.09);

		div("");
		System.out.println("\nDigie a opção desejada: \n[1] Retornar ao menu principal\n[2] Sair");
		opcao = in.nextInt();

		if (opcao == 1) {
			verMenuPrincipal(in);
		} else {
			System.out.println("Volte sempre!");
			System.exit(0);
		}
	}

	static public void div(String title) {
		System.out.println("\t\t\t\t\t" + title);
		System.out.println(
				"=================================================================================================");
	}

	private static String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static void criarEstoque(LinkedList<Produto> estoque) {
		estoque.add(new Produto("Iogurte", 3.60, 10));
		estoque.add(new Produto("Chocolate", 7.45, 10));
		estoque.add(new Produto("Farinha", 7.50, 10));
		estoque.add(new Produto("Feijão", 11.30, 10));
		estoque.add(new Produto("Macarrão", 3.50, 10));
		estoque.add(new Produto("Manteiga", 7.30, 10));
		estoque.add(new Produto("Biscoito", 4.60, 10));
		estoque.add(new Produto("Chá Mate", 3.80, 10));
	}
}