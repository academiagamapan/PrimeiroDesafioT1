package modelo.testes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import modelo.Carrinho;
import modelo.ItemCarrinho;
import modelo.Produto;
import modelo.repositorios.ProdutoRepositorio;

public class TesteCarrinho {
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);

		List<Produto> produtos = ProdutoRepositorio.getAll();		
		Produto novoProduto;
		ItemCarrinho novoItem;
		
		Integer opcaoMenu;
		Integer codProduto;
		Long qtdProduto;
			
		Carrinho carrinho = new Carrinho();
	
		System.out.println("**************************************************************");
		System.out.println("  ╔═╗╔═╗╔═══╗╔═══╗╔═══╗╔═══╗╔═══╗╔═══╗     ╔═══╗╔═══╗╔═╗─╔╗\r\n"
				+ "  ║║╚╝║║║╔══╝║╔═╗║║╔═╗║║╔═╗║╚╗╔╗║║╔═╗║     ║╔═╗║║╔═╗║║║╚╗║║\r\n"
				+ "  ║╔╗╔╗║║╚══╗║╚═╝║║║─╚╝║║─║║─║║║║║║─║║     ║╚═╝║║║─║║║╔╗╚╝║\r\n"
				+ "  ║║║║║║║╔══╝║╔╗╔╝║║─╔╗║╚═╝║─║║║║║║─║║     ║╔══╝║╚═╝║║║╚╗║║\r\n"
				+ "  ║║║║║║║╚══╗║║║╚╗║╚═╝║║╔═╗║╔╝╚╝║║╚═╝║     ║║───║╔═╗║║║─║║║\r\n"
				+ "  ╚╝╚╝╚╝╚═══╝╚╝╚═╝╚═══╝╚╝─╚╝╚═══╝╚═══╝     ╚╝───╚╝─╚╝╚╝─╚═╝");
		System.out.println("--------------------------------------------------------------");
		System.out.println("           Bem vindo ao Mercado Pan!\n");
		
		do {
			System.out.println("-----------------------------------------------");
			System.out.println("              Catálogo de Produtos");
			System.out.println("-----------------------------------------------");
			System.out.printf("  Cod\t    Nome       Quantidade     Preço       \n");			
			
			for (Produto produto : produtos) {
				System.out.println(criaLinha(produto));
			}
			
			System.out.println(
					"\nMenu principal:                  " 
					+ "\n1 - Adicionar produto" 
					+ "\n2 - Ver carrinho"					
					+ "\n3 - Sair\n"
					+ "Escolha a opção desejada:" );

			opcaoMenu = entrada.nextInt();

			switch (opcaoMenu) {
				case 1:								
					codProduto = getCodProdutoValido(entrada, produtos.size());									
					qtdProduto = getQtdProdutosValida(entrada, produtos.get(codProduto - 1).getQtdEstoque());
													
					novoProduto = produtos.get(codProduto - 1);	
					
					Integer codProdutoSeAdicionado = codProdutoSeAdicionado(carrinho.getItens(), codProduto);
					
					if(codProdutoSeAdicionado != 0) {
						carrinho.getItens().get(codProdutoSeAdicionado);
					}
					else {
						novoItem = new ItemCarrinho(carrinho.getItens().size()+1, novoProduto.getPreco(), qtdProduto, null, novoProduto);
						carrinho.addItens(novoItem);
						
						produtos.get(codProduto - 1).removerEstoque(qtdProduto);
					}				
											
					System.out.println("Produto adicionado ao carrinho com sucesso!\n");
							
					break;
				case 2:
					if(carrinho.getItens().size() != 0) {
						opcaoMenu = menuCarrinho(entrada, carrinho, opcaoMenu);
					} else {
						System.out.println("Você não possui itens no carrinho!\n");
					}
						
					break;							
	
				default:
					break;
			}

		} while (opcaoMenu != 3);
	
		System.out.println("\n\n\n  Obrigado pela preferência! Volte sempre!!!");
		entrada.close();

	}

	private static Integer codProdutoSeAdicionado(List<ItemCarrinho> itens, Integer codProduto) {
		for (ItemCarrinho itemCarrinho : itens) {
			if(itemCarrinho.getProduto().getCodigo() == Long.valueOf(codProduto)) {
				return itemCarrinho.getCodItem();
			}
		}
		return 0;
	}

	private static Long getQtdProdutosValida(Scanner entrada, Long qtdEstoque) {
		Long quantidade;
		do {
			System.out.println("Digite a quantidade desejada: ");
			quantidade = entrada.nextLong();
			if(quantidade < 0 || quantidade > qtdEstoque) {
				System.out.println("Quantidade inválida!");
			}
		} while(quantidade < 0 || quantidade > qtdEstoque);
		
		return quantidade;	
	}

	private static Integer getCodProdutoValido(Scanner entrada, Integer tamanhoTotal) {
		Integer codProduto;
		do {
			System.out.println("Digite o código do produto: ");
			codProduto = entrada.nextInt();
			if(codProduto < 0 || codProduto > tamanhoTotal) {
				System.out.println("Código inválido!");
			}
		} while(codProduto < 0 || codProduto > tamanhoTotal);
		return codProduto;
	}

	private static Integer menuCarrinho(Scanner entrada, Carrinho carrinho, Integer menuOpcao) {
		Integer opcao;
		Long quantidade;
		Integer codProdutoItem;				
		
		do {
			verCarrinho(entrada, carrinho);
			List<ItemCarrinho> itens = carrinho.getItens();
									
			System.out.println("Menu Carrinho:\n"
					+ "1 - Adicionar quantidade\n"
					+ "2 - Remover item\n"
					+ "3 - Finalizar compra\n"
					+ "4 - Sair do carrinho\n"
					+ "Digite a opção desejada:\n");
			
			opcao = entrada.nextInt();
			
			switch (opcao) {
			case 1:											
				codProdutoItem = getCodProdutoValido(entrada, itens.size());				
				quantidade = getQtdProdutosValida(entrada, itens.get(codProdutoItem - 1).getQuantidade());			
				
				itens.get(codProdutoItem - 1).adicionarQuantidade(quantidade);
				itens.get(codProdutoItem - 1).getProduto().removerEstoque(quantidade);
				System.out.println("Adicionado com sucesso!");
						
				break;
			case 2:				
				codProdutoItem = getCodProdutoValido(entrada, itens.size());					
				quantidade = getQtdProdutosValida(entrada, itens.get(codProdutoItem-1).getProduto().getQtdEstoque());	
						
				itens.get(codProdutoItem - 1).removeQuantidade(quantidade);
				itens.get(codProdutoItem - 1).getProduto().adicionarEstoque(quantidade);
				if(itens.get(codProdutoItem - 1).getQuantidade() == 0) {
					carrinho.getItens().remove(codProdutoItem - 1);
				}			
				System.out.println("Removido com sucesso!");
				break;
			case 3:
				menuFormaPagamento(entrada, carrinho);
				opcao = 4;
				menuOpcao = 3;
				break;

			default:
				break;
			}
			
		} while(opcao != 4);
		
		return menuOpcao;
	}

	private static void menuFormaPagamento(Scanner entrada, Carrinho carrinho) {
		Integer opcao;
		Double desconto;
		Double totalAPagar;
		Double tributo;
		LocalDateTime dataAtual = LocalDateTime.now();	
		String data = formatarData(dataAtual);
			
		
		do {
			System.out.println("Menu forma de pagamento:"
							 + "\n1 - Pagamento à vista (20% de desconto)"
							 + "\n2 - À vista no credito (10% de desconto)"
							 + "\n3 - Parcelado em até 3x");			
			System.out.println("Digite a forma de pagamento desejada:");
			
			opcao = entrada.nextInt();
		} while(opcao < 0 || opcao > 3);
		
		System.out.println("--------------------------------------------------------");
		System.out.println("                     Cupom Fiscal\n");
		mostrarItensCarrinho(carrinho.getItens());
								
		Double total = 0.0;
		for (ItemCarrinho item : carrinho.getItens()) {
			total += item.getPrecoUnitario() * item.getQuantidade();
		}
		switch (opcao) {
		case 1:			
						
			desconto = total * 0.2;
			totalAPagar = total * 0.8;			
			tributo =  totalAPagar * 0.09;
			
			System.out.println(" Forma de pagamento: Pagamento à vista (20% de desconto)");
			System.out.println("--------------------------------------------------------");
			System.out.printf("  Subtotal:                         R$ %.2f\n", total);	
			System.out.printf("  Desconto:                         R$ %.2f\n", desconto);
			System.out.printf("  Total:                            R$ %.2f\n", totalAPagar);			
			System.out.println("--------------------------------------------------------");	
			System.out.printf("  Tributo (9%%):                     R$ %.2f\n", tributo);
			System.out.printf("  Data:                             %s", data);
			System.out.println("\n--------------------------------------------------------");
			break;
		case 2:
			
			desconto = total * 0.1;
			totalAPagar = total * 0.9;			
			tributo =  totalAPagar * 0.09;
			System.out.println(" Forma de pagamento: À vista no credito (10% de desconto)");
			System.out.println("--------------------------------------------------------");
			System.out.printf("  Subtotal:                         R$ %.2f\n", total);	
			System.out.printf("  Desconto:                         R$ %.2f\n", desconto);
			System.out.printf("  Total:                            R$ %.2f\n", totalAPagar);			
			System.out.println("--------------------------------------------------------");	
			System.out.printf("  Tributo (9%%):                     R$ %.2f\n", tributo);
			System.out.printf("  Data:                             %s", data);
			System.out.println("\n--------------------------------------------------------");
			break;
		case 3:
			Integer qtdParcelas = 0;
			System.out.println("Digite a quantidade de parcelas (no máximo 3):");
			qtdParcelas  = entrada.nextInt();
			
			System.out.println("                     Cupom Fiscal");
			
			mostrarItensCarrinho(carrinho.getItens());
								
			total = 0.0;
			for (ItemCarrinho item : carrinho.getItens()) {
				total += item.getPrecoUnitario() * item.getQuantidade();
			}
								
			tributo =  total * 0.09;
			System.out.println(" Forma de pagamento: Parcelado");
			System.out.println("-------------------------------------------------");				
			System.out.printf("  Total:                            R$ %.2f\n", total);
			System.out.printf("  Quantidade de parcelas:              %d\n", qtdParcelas);
			System.out.printf("  Valor da parcela:                 R$ %.2f\n", total / qtdParcelas);
			System.out.println("-------------------------------------------------");	
			System.out.printf("  Tributo (9%%):                     R$ %.2f\n", tributo);
			System.out.printf("  Data:                             %s", data);
			System.out.println("\n--------------------------------------------------------");
		
			break;

		default:
			break;
		}
		
	}

	private static String formatarData(LocalDateTime dataAtual) {		
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");       
		return dataAtual.format(dateFormatter);
	}

	private static void verCarrinho(Scanner entrada, Carrinho carrinho) {	
		
		System.out.println("   \n           Seu Carrinho De Compras");
		System.out.println("-----------------------------------------------");
		
		mostrarItensCarrinho(carrinho.getItens());				
	}
	 
	private static void mostrarItensCarrinho(List<ItemCarrinho> itens) {
		System.out.println("  Cod        Nome      Quantidade     Preço");
		for(ItemCarrinho item : itens){		
			if(item.getQuantidade() != 0) {
				System.out.println(criaLinhaItem(item)); 
			}						
		}	
		System.out.println();
	}
	
	private static String criaLinhaItem(ItemCarrinho item) {
		StringBuilder sb = new StringBuilder();
		
		String codItem = String.valueOf(item.getCodItem());
		String nomeProduto = item.getProduto().getNome();
		String quantidade = String.valueOf(item.getQuantidade());		
		Double preco = item.getProduto().getPreco() * item.getQuantidade();
		Double preArredondado =  Math.round(preco * 100.0)/100.0;
		String precoProduto = String.valueOf(preArredondado);
		sb.append("   " + adicionaEspacos(codItem, 3));
		sb.append("   " + adicionaEspacos(nomeProduto, 12));
		sb.append("    " + adicionaEspacos(quantidade, 6));
		sb.append("  R$ " + adicionaEspacos(precoProduto, 8));	
		
		return sb.toString();
	}

	private static String criaLinha(Produto produto) {
		StringBuilder sb = new StringBuilder();
		
		String codProduto = String.valueOf(produto.getCodigo());
		String nomeProduto = produto.getNome();
		String qtdEstoque = String.valueOf(produto.getQtdEstoque());
		Double precoArredondado = Math.round(produto.getPreco() * 100.0)/100.0;
		String precoProduto = String.valueOf(precoArredondado);
		sb.append("   " + adicionaEspacos(codProduto, 3));
		sb.append("   " + adicionaEspacos(nomeProduto, 12));
		sb.append("    " + adicionaEspacos(qtdEstoque, 6));
		sb.append("  R$ " +  adicionaEspacos(precoProduto, 7));	
		
		return sb.toString();
	}

	private static String adicionaEspacos(String texto, int tamanho) {		
		StringBuilder sb = new StringBuilder();
		sb.append(texto);
		for(int i = texto.length(); i < tamanho; i++) {
			sb.append(" ");
		}
		sb.append("|");				
		return sb.toString();
	}

}
