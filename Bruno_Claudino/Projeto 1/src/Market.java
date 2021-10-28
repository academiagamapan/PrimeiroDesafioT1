import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Market {
	static LinkedList<Product> stock = new LinkedList<>();                                 // Lista contendo o estoque dos produtos da loja
	static LinkedList<Product> shoppingCart = new LinkedList<>();                          // Lista que representa o carrinho de compras

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);                                               // Scanner da entrada de dados

		criarEstoque(stock);                                                               // Popula o estoque
		verMenuPrincipal(in);                                                              // Mostra o menu inicial

	}

	public static void verMenuPrincipal(Scanner in) {
		div("Gama PAN Supermarket");                                                       // Imprime div com título do supermercado
		System.out.println("[1] - Ver Produtos");                                          // Print das opções do menu principal
		System.out.println("[2] - Ver Carrinho");
		System.out.println("[0] - Sair");
		System.out.println("Digite a opção desejada");
		int opcao = in.nextInt();                                                          // Pega a entrada da navegação do menu
		if (opcao == 1) {                                                                  // Se for opção 1
			verProduto(in);                                                                // Chama estado de exibição dos produtos em estoque
		} else if (opcao == 2) {                                                           // Senão se entrada for opção 2
			verCarrinho(in);                                                               // Chama estado que exibe o carrinho de compras
		} else if (opcao == 0) {                                                           // Senão se entrada for opção 0
			System.out.println("Volte sempre!");                                           // Exibe mensagem de tchau
			System.exit(0);                                                                // Sai do ciclo de execução do programa
		} else {                                                                           // Senão
			System.out.println("Opção inválida");                                          // Exibe mensagem de opção inválida
			verMenuPrincipal(in);                                                          // Volta para si mesma para executar em loop
		}
	}

	public static void criarEstoque(LinkedList<Product> stock) {                         // Método que popula o estoque com as opções a baixo
		stock.add(new Product("Iogurte", 3.60, 12));
		stock.add(new Product("Chocolate", 7.45, 10));
		stock.add(new Product("Farinha", 7.50, 10));
		stock.add(new Product("Feijão", 11.30, 8));
		stock.add(new Product("Macarrão", 3.50, 12));
		stock.add(new Product("Manteiga", 7.30, 8));
		stock.add(new Product("Biscoito", 4.60, 5));
		stock.add(new Product("Chá Mate", 3.80, 12));
	}

	public static void verProduto(Scanner in) {                                                                     // Estado que exibe os produtos do estoque e opções de compra
		div("Produtos disponíveis");                                                                                // Exibe div com título
		System.out.format("%2s%20s%22s%20s%n", "ID", "Produto", "Preço Unit.", "Quantidade");                       // Formata e exibe o cabeçalho
		for (Product x : stock) {                                                                                   // For que percorre todos os itens do estoque
			System.out.format("%2d%20s%22.2f%20d%n", x.getProductId(), x.getName(), x.getPrice(), x.getQuantity()); // Formata e exibe cada item da lista de estoque
		}
		div("");                                                                                                    // Exibe divisão
		System.out.println("\nDigite a opção desejada:");                                                           // Exibe as opções do menu de compras
		System.out.println("[ID] para adicionar produto desejado.");
		System.out.println("[0] para ir para o carrinho.");
		System.out.println("[-1] para retornar ao menu principal.\n");
		int prod = in.nextInt();                                                                                    // Recebe a entrada
		if (prod > 0 && prod <= stock.size()) {                                                                     // Verifica se a entrada corresponde a um valor válido do estoque
			System.out.println("Qual quantidade deseja adicionar?");                                                // Pergunta qual quantidade quer colocar no carrinho
			int quantidadeRemover = in.nextInt();                                                                   // Recebe a quantidade
			if (stock.get(prod - 1).getQuantity() >= quantidadeRemover){                                            // Verifica se a quantidade desejada de compra está disponível em estoque
				boolean tem = false;                                                                                // Variável que guardará se o item já existe no carrinho para somente mudar a quantidade (não é realmente necessária, está ai mais para facilitar a compreensão)
				for(Product x : shoppingCart) {                                                                     // Laço que percorre os itens do carrinho de compra
					if(x.getProductId() == prod) {                                                                  // Verifica se um desses itens tem o id correspondente ao desejado a colocar no carrinho
						tem = true;                                                                                 // Indica que já existem itens semelhantes no carrinho
						x.raiseQtd(quantidadeRemover);                                                              // Aumenta a quantidade do produto no carrinho
						stock.get(prod - 1).decreaseQtd(quantidadeRemover);                                         // Diminui a quantidade do produto em estoque
						verProduto(in);                                                                             // Retorna para si mesmo para atualizar as informações exbidas
					}
				}
				if(!tem){                                                                                           // Caso não tenha itens semelhantes na lista
					Product produto = new Product(stock.get(prod - 1).getProductId(), stock.get(prod - 1).getName(),// Cria um novo produto
							stock.get(prod - 1).getPrice(), stock.get(prod - 1).getQuantity());
					produto.setQuantity(quantidadeRemover);                                                         // Diz a quantidade do produto a ser adicionada no carrinho
					shoppingCart.add(produto);                                                                      // Adiciona o item na lista carrinho de compras
					stock.get(prod - 1).decreaseQtd(quantidadeRemover);                                             // Remove a mesma quantidade do estoque da loja
					System.out.println("Produto adicionado com sucesso!Precione a tecla Enter para continuar.");    // Exibe mensagem de sucesso
					verProduto(in);                                                                                 // Laço para exibir atualizações
				}
			}else{                                                                                                  // Senão
				System.out.println("Quantidade indisponível!Precione a tecla Enter para continuar.");               // Mensagem de quantidade indisponível
				verProduto(in);                                                                                     // Repete
			}
		}else if (prod == 0){                                                                                       // Senão se selecionado a opção 0
			verCarrinho(in);                                                                                        // Chama a exibição do carrinho
		}else if (prod == -1) {                                                                                     // Senão se selecionado a opção -1
			verMenuPrincipal(in);                                                                                   // Volta para o menu principal
		}else{                                                                                                      // Senão
			System.out.println("Valor incorreto! Por Favor digite um valor entre 1 e " + stock.size());             // Exibe mensagem de valor incorreto para item
			verProduto(in);                                                                                         // Laço para repetição
		}
	}

	public static void verCarrinho(Scanner in) {                                                                    // Estado de exibição do carrinho de compras
		div("Carrinho de compras");                                                                                 // Exibe divisão com título de carrinho de compras
		System.out.format("%2s%20s%22s%20s%20s%n", "ID", "Produto", "Preço Unit.", "Quantidade", "Total");          // Formata e exibe o cabeçalho
		for(Product x : shoppingCart){                                                                              // Pecorre os itens na lista do carrinho de compras
			System.out.format("%2d%20s%22.2f%20d%20.2f%n", x.getProductId(), x.getName(), x.getPrice(),             // Formata e exibe cada um dos itens
					x.getQuantity(), x.getPrice() * x.getQuantity());
		}
		div("");                                                                                                    // Exibe divisão sem título
		System.out.println("\nDigite a opção desejada:");                                                           // Exibe opções do menu do carrinho de compras
		System.out.println("[1] Para pagamento.");
		System.out.println("[2] Para remover algum produto de seu carrinho.");
		System.out.println("[3] Continuar comprando.");
		System.out.println("Ou qualquer outra tecla para retornar ao menu principal.");
		int opcao = in.nextInt();                                                                                   // Pega a entrada de usuário
		if(opcao == 1){                                                                                             // Se opção for igual a 1
			pagamento(in);                                                                                          // Chama o estado de Pagamento
		}else if(opcao == 2){                                                                                       // Senão se opção for igual a 2
			removerProduto(in);                                                                                     // Chama estado de remoção de produtos
		}else if(opcao == 3) {                                                                                      // Senão se opção for igual a 3
			verProduto(in);                                                                                         // Volta para o estado que permite compras
		}else{                                                                                                      // Senão
			verMenuPrincipal(in);                                                                                   // Volta para o menu principal
		}
	}

	private static void removerProduto(Scanner in){                                                                 // Estado de remoção de produto
		System.out.println("Digite o ID do produto que deseja remover.");                                           // Exibe pedido de entrada de ID
		int prod = in.nextInt();                                                                                    // Salva entrada
		boolean found = false;                                                                                      // Boolean que define se foi achado o item
		Product achado = shoppingCart.getFirst();                                                                   // Instancia um produdo
		for(Product x : shoppingCart){                                                                              // Laço que percorre as posições do carrinho de compras
			if(prod == x.getProductId()){                                                                           // Verifica se achou o item com o id digitado
				found = true;                                                                                       // Se sim, found recebe verdadeiro
				achado = x;                                                                                         // Achado recebe uma instância desse produt
			}
		}
		if(!found){                                                                                                 // Se não tiver sido achado
			System.out.println("Valor incorreto! Por Favor digite um valor de ID válido");                          // Exibe mensagem de valor inválido
			verCarrinho(in);                                                                                        // Volta para o carrinho
		}else{                                                                                                      // Senão
			System.out.println("Qual quantidade deseja remover?");                                                  // Entra com a quantidade que deseja remover
			int quantidadeRemover = in.nextInt();                                                                   // Salva a quantidade de entrada
			if(achado.getQuantity() >= quantidadeRemover){                                                          // Se a quantidade digita está dentro da quantidade dentro do carrinho
				achado.decreaseQtd(quantidadeRemover);                                                              // Diminui essa quantidade
				if(achado.getQuantity() == 0){                                                                      // Se depois de remover uma quantidade, não sobrar nenhum item do mesmo tipo
					shoppingCart.remove(achado);                                                                    // Retira aquele tipo de item do carrinho
				}
				for(Product x : stock){                                                                             // Laço que percorre os itens do estoque
					if(x.getProductId() == achado.getProductId()){                                                  // Quando achar o item que a pessoa tirou do carrinho de compras
						x.raiseQtd(quantidadeRemover);                                                              // Retorna para o estoque a mesma quantidade que foi retirada do carrinho
					}
				}
				System.out.println("Produto removido com sucesso!");                                                // Mensagem de sucesso
				verCarrinho(in);                                                                                    // Retorna ao carrinho
			}else{                                                                                                  // Senão
				System.out.println("Quantidade inválida!");                                                         // Quantidade inválida para remoção
				verCarrinho(in);                                                                                    // Volta para o carrinho
			}
		}
	}

	private static void pagamento(Scanner in){                                                                      // Estado para pagamento
		double total = 0;                                                                                           // Inicia variável de valor total da venda
		int parcelas = 1;                                                                                           // Inicia variável de parcelar
		for(Product x : shoppingCart){                                                                              // Percorre o carrinho somando os preço dos produtos
			total += x.getPrice() * x.getQuantity();                                                                // Soma o valor de cada item vezes a quantidade de itens pego
		}
		div("Pagamento");                                                                                           // Exibe divisão com título "Pagamento"
		System.out.printf("O valor total da compra foi de R$%.2f%n", total);                                        // Exibe o valor total da compra
		System.out.println("\nDigite a opção desejada:");                                                           // Pede a opção de pagamento
		System.out.println("[1]Cartão Banco PAN \n[2] PIX ou Dinheiro\n");
		int opcao = in.nextInt();                                                                                   // Recebe as opções de pagamento
		if (opcao == 1) {                                                                                           // Se for pagamento por Cartão Pan
			System.out.println("[1] À vista - 15% de desconto\n[2] 3x sem juros\n[3] Parcelado em até 6x(1.5% a.m)");//Pede o número de parcelas
			int parcelamento = in.nextInt();                                                                        // Recebe o número de parcelas da entrada
			if(parcelamento == 1){                                                                                  // Se for á vista
				total *= 0.85;                                                                                      // Aplica o desconto
				parcelas = 1;                                                                                       // Define o número de parcelas igual a 1
				notaFiscal(total, parcelas, parcelamento, in);                                                      // Chama o estado da nota fiscal
			}else if(parcelamento == 2){                                                                            // Se for parcelado em 3x 
				total /= 3;                                                                                         // Divide o total em 3 parcelas
				parcelas = 3;                                                                                       // Define o número de parcelas como 3
				notaFiscal(total, parcelas, parcelamento, in);                                                      // Chama estado de nota fiscal
			}else if(parcelamento == 3){                                                                            // Se for parcelado em 6x
				total *= 1.09344;                                                                                   // Aplica o juros
				total /= 6;                                                                                         // Divide o total para 6 parcelas
				parcelas = 6;                                                                                       // Define a quantidade de parcelas como 6
				notaFiscal(total, parcelas, parcelamento, in);                                                      // Chama o estado da nota fiscal
			}else{                                                                                                  // Senão
				System.out.println("Opção inválida!");                                                              // Exibe mensagem de opção de pagamento inválido
				pagamento(in);                                                                                      // Laço para repetir o processo de pagamento
			}
		}else if(opcao == 2){                                                                                       // Senão se o pagamento for em dinheiro ou pix
			notaFiscal(total, parcelas, opcao, in);                                                                 // Chama estado de nota fiscal
		}
	}

	private static void notaFiscal(double total, int parcelas, int opcao, Scanner in){                              // Estado de exibição da nota fiscal
		div("");                                                                                                    // Exibe div
		System.out.println("Gama PAN Supermarket \nAv. dos Programadores nº6\nCNPJ: 11.123.456/0001-12");           // Exibe nome, endereço e CNPJ
		System.out.println("Data da compra:" + getDateTime());                                                      // Exibe a data e hora da compra
		div("Nota Fiscal");                                                                                         // Exibe div com título de nota fiscal
		System.out.format("%10s%22s%20s%20s%n", "Produto", "Preço Unit.", "Quantidade", "Total");                   // Formata e exibe o cabeçalho
		for(Product x : shoppingCart){                                                                              // Laço que percorre o carrinho de compras
			System.out.format("%10s%22.2f%20d%20.2f%n", x.getName(), x.getPrice(), x.getQuantity(),                 // Exibe a informações de cada produto a ser comprado
					x.getPrice() * x.getQuantity());
		}
		if(opcao == 1){                                                                                             // Se a opção de parcelamento tiver sido 1
			System.out.printf("%nDesconto na compra: R$ %.2f%n", total / 0.85 * 0.15);                              // Exibe o desconto
		}else if(opcao == 2 || opcao == 3){                                                                         // Se for outras opções de parcelamento
			System.out.printf("%nValor parcelado a ser pago: %dx R$ %.2f%n", parcelas, total);                      // Exibe o valor a ser pago
		}
		System.out.printf("Valor total a ser pago: R$ %.2f%n", total * parcelas);                                   // Exibe o total a ser pago
		System.out.printf("Valor do imposto: R$ %.2f%n", total * parcelas * 0.09);                                  // Exibe o valor do imposto
		div("");                                                                                                    // Exibe diviisão
		System.out.println("\nDigie a opção desejada: \n[1] Retornar ao menu principal\n[2] Sair");                 // Opções de finalização
		opcao = in.nextInt();                                                                                       // Recebe a opção de entrada
		shoppingCart.clear();                                                                                       // Limpa o carrinho de compras
		if(opcao == 1){                                                                                             // Se for opção 1
			verMenuPrincipal(in);                                                                                   // Volta para o menu principal
		}else{                                                                                                      // Senão
			System.out.println("Volte sempre!");                                                                    // Exibe mensagem de volte sempre
			System.exit(0);                                                                                         // Finaliza a execução do sistema
		}
	}

	static public void div(String title){                                                                           // Método que exibe as divs com Título ou não
		System.out.println("\t\t\t\t\t" + title);
		System.out.println(
				"=================================================================================================");
	}

	private static String getDateTime(){                                                                            // Método que pega a data e hora
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");                                        // Define a formatação da data e hora
		Date date = new Date();                                                                                     // Instancia uma nova data
		return dateFormat.format(date);                                                                             // Retorna essa data
	}
}
