package Loja;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Scanner;

public class Loja {
	
	static LinkedList<Produto> estoque = new LinkedList<>();											//Lista de produtos do estoque.
	static LinkedList<Produto> carrinhoCompras = new LinkedList<>();									//Lista de produtos do carrinho de compras.
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);															//Cria��o da vari�vel para entrada de dados.
		
		criarEstoque(estoque);																			//Popula��o da lista estoque.
		verMenuPrincipal(in);																			//Inicia a aplica��o chamando o m�todo para gerar o menu principal.
			
	}
	
	
	public static void verMenuPrincipal(Scanner in) {													//Aplica��o que ir� gerar o menu principal.
		
		div("Gama PAN Supermarket");																	//Gera o formul�rio do menu principal (linhas 26 a 30).
		System.out.println("[1] - Ver Produtos");
		System.out.println("[2] - Ver Carrinho");
		System.out.println("[0] - Sair");
		System.out.println("Digite a op��o desejada");
		int opcao = in.nextInt();																		//Guarda a op��o do menu desejada para ser usada na estrutura de decis�o.
		
		if (opcao == 1) {																				//Estrutura de decis�o do menu principal (linhas 33 a 43).
			verProduto(in);																				//Chama o m�todo que ir� gerar a lista de produtos em estoque.
		}else if (opcao == 2) {
			verCarrinho(in);																			//Chama o m�todo que ir� gerar a lista de produtos do carrinho de compras.
		}else if(opcao == 0){
			System.out.println("Volte sempre!");
			System.exit(0);																				//Sai da aplica��o.
		}else {
			System.out.println("Op��o inv�lida");
			verMenuPrincipal(in);																		//Chama o m�todo que ir� gerar a o menu principal de forma recursiva.
		}
	}
	
	
	public static void criarEstoque(LinkedList<Produto>estoque) {										//Popula o estoque com valores pr� definidos.
		estoque.add(new Produto("Iogurte", 3.60, 12));
		estoque.add(new Produto("Chocolate", 7.45, 10));
		estoque.add(new Produto("Farinha", 7.50, 10));
		estoque.add(new Produto("Feij�o", 11.30, 8));
		estoque.add(new Produto("Macarr�o", 3.50, 12));
		estoque.add(new Produto("Manteiga", 7.30, 8));
		estoque.add(new Produto("Biscoito", 4.60, 5));
		estoque.add(new Produto("Ch� Mate", 3.80, 12));
		
	}
	
	
	public static void verProduto(Scanner in) {															//Gera o formul�rio com a lista de produtos em estoque.
		div("Produtos dispon�veis");
		System.out.format("%2s%20s%22s%20s%n", "ID", "Produto","Pre�o Unit.","Quantidade");
		
		for (Produto x : estoque) {																								//Percorre a lista do estoque e imprime os �tens na tela.
			System.out.format("%2d%20s%22.2f%20d%n", x.getIdProduto(), x.getNome(), x.getPreco(), x.getQuantidade());
		}
		
		div("");
		System.out.println("\nDigite a op��o desejada\n[ID] para adicionar produto desejado\n"								
				+ "[0] para ir para o carrinho.\n[-1] para retornar ao menu principal.");
		int prod = in.nextInt();																								//Guarda na v�riavel prod para fazer a escolha de produto ou navegar para outras �reas da aprica��o.
																																//Estrutura de dicis�o que:
		if(prod > 0 && prod <= estoque.size()) {																				//Se a vari�vel prod for positiva e estiver dentro do tamanho da lista estoque.
			System.out.println("Qual quantidade deseja adicionar?");															//Guarda a quantidade de produtos que se deseja inserir no carrinho de compras na vari�vel quantidadeRemor.
			int quantidadeRemover = in.nextInt();
			
			if( estoque.get(prod-1).getQuantidade() >= quantidadeRemover) {														//Se a vari�vel quantidadeRemover menor ou igual � quantidade do produto em estoque	.
				Produto produto = new Produto(estoque.get(prod-1).getIdProduto(), estoque.get(prod-1).getNome(),				//Cria um novo produto com os atributos do produto escolhido em estoque.
						estoque.get(prod-1).getPreco(), estoque.get(prod-1).getQuantidade());
				
				produto.setQuantidade(quantidadeRemover);																		//Atribui o valor da vari�vel quantidadeRemover como atributo quantidade desse novo produto.
				carrinhoCompras.add(produto);																					//Adiciona esse novo produto ao carrinho de compras.
//				carrinhoCompras.getLast().setIdProduto(prod);
				estoque.get(prod-1).decrementarQuantidade(quantidadeRemover);													//Remove a quantidade do produto escolhido, e adicionado ao carrinho, do estoque.
				System.out.println("Produto adicionado com sucesso!Precione a tecla Enter para continuar.");
				verProduto(in);																									//Chama a tela de produtos em estoque atualizada.
				}else {																											//Ir� informar que a quantidade de produto est� indispon�vel ou � inv�lida(Ex. qnt negativa).
					System.out.println("Quantidade indispon�vel ou inv�lida!Precione a tecla Enter para continuar.");					
					verProduto(in);																								//Chama a tela de produtos em estoque atualizada.
				}
			
		}else if(prod == 0) {
			verCarrinho(in);																									//Chama a tela do carrinho de compras.		
			
		}else if(prod == -1) {
			verMenuPrincipal(in);																								//Chama a tela do menu principal.
			
		}else {																													//Se o valor de ID digitado n�o estiver dentro dos falores v�lidos informa ao usu�rio.
			System.out.println("Valor incorreto! Por Favor digite um valor entre 1 e " + estoque.size());
			verProduto(in);
		}		
	}
	
	
	public static void verCarrinho(Scanner in) {														//Gera o formul�rio com a lista de produtos do carrinho de compras
		
		div("Carrinho de compras");
		System.out.format("%2s%20s%22s%20s%20s%n", "ID", "Produto","Pre�o Unit.","Quantidade","Total");
		int i = 1;
		for (Produto x : carrinhoCompras) {																//Percorre a lista do carrinho e imprime os �tens na tela.
			System.out.format("%2d%20s%22.2f%20d%20.2f%n",i, x.getNome(), x.getPreco(), x.getQuantidade(), x.getPreco()*x.getQuantidade() );
			i++;
		}
		
		div("");
		System.out.println("\nDigite a op��o desejada:\n[1] Para pagamento.\n"							//Imprime as op��es de menu do carrinho de compras.
				+ "[2] Para remover algum produto de seu carrinho.\n[3] Continuar comprando.\n"
				+ "Ou qualquer outra tecla para retornar ao menu principal.");
		int opcao = in.nextInt();																		//Recebe a escolha do usu�rio e guarda na vari�vel op��o.
		
		if(opcao == 1) {																				//Estrutura de decis�o do menu
			pagamento(in);
		}else if(opcao == 2) {
			removerProduto(in);
		}else if(opcao == 3) {
			verProduto(in);
		}else {
			verMenuPrincipal(in);
		}

	}
	
	
	private static void removerProduto(Scanner in) {													//M�todo de remo��o de produtos do carrinho de compras.
		
		System.out.println("Digite o ID do produto que deseja remover.");
		int prod = in.nextInt();																		//Guarda na vari�vel prod o ide do produto a ser removido do carrinho de compras.
		
		if(prod > 0 && prod <= carrinhoCompras.size()) {												//verifica se o ID do produto � v�lida.
			System.out.println("Qual quantidade deseja remover?");
			int quantidadeRemover = in.nextInt();														//Guarda a quantidade que se deseja remover do produto na vari�vel quantidadeRemover.
			
			if( carrinhoCompras.get(prod-1).getQuantidade() > quantidadeRemover && quantidadeRemover > 0) {				//Verifica se a quantidade no carrinho � maior que o valor que se deseja remover.
				carrinhoCompras.get(prod-1).decrementarQuantidade(quantidadeRemover);									//Retira do carrinho a quantidade desejada do produto.
				estoque.get(carrinhoCompras.get(prod-1).getIdProduto()-1).incrementarQuantidade(quantidadeRemover);		//Adiciona a quantidade removida do carrinho de volta ao estoque.
				System.out.println("Produto removido com sucesso!");
				verCarrinho(in);
			}else if ( carrinhoCompras.get(prod-1).getQuantidade() == quantidadeRemover ){								//Verifica se a quantidade removida � igual a quantidade do produto no carrinho de compras.
				estoque.get(carrinhoCompras.get(prod-1).getIdProduto()-1).incrementarQuantidade(quantidadeRemover);		//Adiciona a quantidade removida do carrinho de volta ao estoque.
				carrinhoCompras.remove(carrinhoCompras.get(prod-1));													//Caso afirmativo remove o produto da lista do carrinho de compras.
				System.out.println("Produto removido com sucesso!");
				verCarrinho(in);
			}else {
				System.out.println("Quantidade inv�lida!");																//Avisa que a quantidade informada n�o � v�lida(negativa ou maior que a existente).
				verCarrinho(in);				
			}
			
		}else {
			System.out.println("Valor incorreto! Por Favor digite um valor entre 1 e " + carrinhoCompras.size());		//Se o valor de ID digitado n�o estiver dentro dos falores v�lidos informa ao usu�rio.
			verCarrinho(in);
		}		
	}

	
	private static void pagamento(Scanner in) {															//M�todo que calcula o valor total, permite a escolha de m�todo de pagamentos, calcula valor de parcelas. 

		double total = 0;
		int parcelas = 1;
		
		for (Produto x : carrinhoCompras){																//Percorre o o carrinho de compras multiplica o valor unit. pela quantidade e soma o resultado na vari�vel total.
			total += x.getPreco() * x.getQuantidade();
		}
		
		div("Pagamento");
		System.out.format("%2s%20s%22s%20s%20s%n", "ID", "Produto","Pre�o Unit.","Quantidade","Total");
		int i = 1;
		for (Produto x : carrinhoCompras) {																										//Imprime o formul�rio do carrinho de compras para o pagamento.
			System.out.format("%2d%20s%22.2f%20d%20.2f%n",i, x.getNome(), x.getPreco(), x.getQuantidade(), x.getPreco()*x.getQuantidade() );
			i++;
		}
		
		div("");
		System.out.printf("\nO valor total da compra foi de R$%.2f%n", total);							
		System.out.println("\nDigite a op��o desejada:");
		System.out.println("[1]Cart�o Banco PAN \n[2] PIX ou Dinheiro\n");
		
		int opcao = in.nextInt();																		//Guarda a op��o de pagamento na vari�vel opcao.
		
		if(opcao==1) {														
			System.out.println("[1] � vista - 15% de desconto\n"										//Estrutura de decis�o da forma de pagamento (linhas 189 a 215)
					+ "[2] 3x sem juros\n[3] Parcelado em at� 6x(1.5% a.m)");
			int parcelamento = in.nextInt();
			
			if(parcelamento == 1) {
				total *= 0.85;
				parcelas = 1;
				notaFiscal(total, parcelas, parcelamento , in);			
			}else if(parcelamento == 2) {
				total /= 3;
				parcelas = 3;
				notaFiscal(total, parcelas, parcelamento, in);
			}else if(parcelamento == 3) {
				total *= 1.09344;
				total /= 6;
				parcelas = 6;
				notaFiscal(total, parcelas, parcelamento, in);
			}else {
				System.out.println("Op��o inv�lida!");
				pagamento(in);
			}
			
		}else if(opcao == 2 ) {
			notaFiscal(total, parcelas,opcao, in);
		}
	}
			

	
	private static void notaFiscal(double total, int parcelas, int opcao, Scanner in) {					//Gera formul�rio da nota fiscal.
		div("");
		System.out.println("Gama PAN Supermarket \nAv. dos Programadores n�6\nCNPJ: 11.123.456/0001-12");
		System.out.println("Data da compra:" + getDateTime());
				
		div("Nota Fiscal");
		
		System.out.format("%10s%22s%20s%20s%n", "Produto","Pre�o Unit.","Quantidade","Total");
		
		for (Produto x : carrinhoCompras) {																//Percorre o carrinho de compras e imprime os �tens na nota fiscal.
			if(x.getQuantidade() != 0) {																//Caso a quantidade dos protos produtos for diferente de 0 imprime os �tens do carrinho.
				System.out.format("%10s%22.2f%20d%20.2f%n", x.getNome(), x.getPreco(), x.getQuantidade(), x.getPreco()*x.getQuantidade() );
			}
		}
		carrinhoCompras.clear();																		//Ap�s imprimir os itens comprados na nota fiscal limpa o carrinho de compras.
		
		if(opcao == 1) {																				//Imprime os valores das notas comforme as op��es de pagamento desejada (linhas 235 a 242).
		System.out.printf("%nDesconto na compra: R$ %.2f%n", total/0.85*0.15);
		
		}else if(opcao == 2 || opcao == 3) {
			System.out.printf("%nValor parcelado a ser pago: %dx R$ %.2f%n", parcelas, total);
		}
		System.out.printf("Valor total a ser pago: R$ %.2f%n", total*parcelas);
		System.out.printf("Valor do imposto: R$ %.2f%n", total*parcelas*0.09);
		
		
		div("");
		System.out.println("\nDigie a op��o desejada: \n[1] Retornar ao menu principal\n[2] Sair");
		opcao=in.nextInt();
		
		if(opcao == 1) {
			verMenuPrincipal(in);
		}else {
			System.out.println("Volte sempre!");
			System.exit(0);																				//Sai da aplica��o.
		}
	}

	


	static public void  div(String title) {																//Gera uma divis�ria para ser usada nos formul�rios, com ou sem um t�tulo.
		
		System.out.println("\t\t\t\t\t" + title);
		System.out.println("=================================================================================================");
		
	}

		

	
private static String getDateTime() {																	//Gera a data e hora do sistema para ser usada na nota fiscal, no formato 
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}}

