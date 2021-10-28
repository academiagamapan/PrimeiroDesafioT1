package loja;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Scanner;



public class Loja {
	
	static LinkedList<Produto> estoque = new LinkedList<>(); //Instanciando a lista encadeada para o estoque
	static LinkedList<Produto> carrinhoCompras = new LinkedList<>(); //Instanciando a lista encadeada para o carrinho de compras
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		criarEstoque(estoque); //popular o estoque com produtos
		verMenuPrincipal(in);
			
	}
	
	
	public static void verMenuPrincipal(Scanner in) {
		
		//Imprimindo o menu inicial
		div("Gama PAN Supermarket");
		System.out.println("[1] - Ver Produtos");
		System.out.println("[2] - Ver Carrinho");
		System.out.println("[0] - Sair");
		System.out.println("Digite a op��o desejada");
		int opcao = in.nextInt();
		div();
		
		//Selecionar as op��es e direcionar para pr�xima a��o
		System.out.println("\n" + opcao);
		if (opcao == 1) {
			verProduto(in);
		}else if (opcao == 2) {
			verCarrinho(in);
		}else if(opcao == 0){
			System.out.println("Volte sempre!");
			System.exit(0);

		}else {
			System.out.println("Op��o inv�lida");
			verMenuPrincipal(in);
		}
	}
	
	
	public static void criarEstoque(LinkedList<Produto>estoque) {
		//populando a lista de estoque
		estoque.add(new Produto("Iogurte", 3.60, 12));
		estoque.add(new Produto("Chocolate", 7.45, 10));
		estoque.add(new Produto("Farinha", 7.50, 10));
		estoque.add(new Produto("Feij�o", 11.30, 8));
		estoque.add(new Produto("Macarr�o", 3.50, 12));
		estoque.add(new Produto("Manteiga", 7.30, 8));
		estoque.add(new Produto("Biscoito", 4.60, 5));
		estoque.add(new Produto("Ch� Mate", 3.80, 12));

		
		
	}
	
	
	public static void verProduto(Scanner in) {
		//mostrar no console os produtos 
		div("Produtos dispon�veis");
		System.out.format("%2s%20s%22s%20s%n", "ID", "Produto","Pre�o Unit.","Quantidade");
		
		for (Produto x : estoque) {
			System.out.format("%2d%20s%22.2f%20d%n", x.getIdProduto(), x.getNome(), x.getPreco(), x.getQuantidade());
		}
		
		div();
		System.out.println("\nDigite a op��o desejada\n[ID] para adicionar produto desejado\n[0] para ir para o carrinho.\n[-1] para retornar ao menu principal.");
		int prod = in.nextInt();
		
		if(prod > 0 && prod <= estoque.size()) { //verificar se a op��o digitada � valida
			System.out.println("Qual quantidade deseja adicionar?");
			int quantidadeRemover = in.nextInt();//selecionar quantidade que vai ser comprada
			
			if( estoque.get(prod-1).getQuantidade() >= quantidadeRemover && quantidadeRemover > 0) { //verificando se a op��o � valida
				Produto produto = new Produto(estoque.get(prod-1).getIdProduto(), estoque.get(prod-1).getNome(), estoque.get(prod-1).getPreco(), quantidadeRemover); //criar um novo objeto para adicionar no carrinho

				boolean produtoNoCarrinho = false; //booleano para verificar se o produto a adicionar existe no carrinho				
				for (Produto x : carrinhoCompras) { //verificar se o produto est� no carrinho
					if(x.getIdProduto() == produto.getIdProduto()) {
						produtoNoCarrinho = true;
						x.incrementarQuantidade(quantidadeRemover);//como produto j� existe, apenas incrementa quantidade no carrinho
					}
				}
				if(!produtoNoCarrinho) {//caso n�o exista produto no carrinho, adicionar um objeto
					carrinhoCompras.add(produto);
				}
				estoque.get(prod-1).decrementarQuantidade(quantidadeRemover); //decrementar quantidade adicionada ao carrinho no estoque
				System.out.println("Produto adicionado com sucesso!");
				verProduto(in);
				}else { //se quantidade adicionada for indispon�vel, exibir "Quantidade indispon�vel"
					System.out.println("Quantidade indispon�vel!");					
					verProduto(in);				
				}
			
		}else if(prod == 0) {
			verCarrinho(in);			
			
		}else if(prod == -1) {
			verMenuPrincipal(in);			
			
		}else {
			System.out.println("Valor incorreto! Por Favor digite um valor entre 1 e " + estoque.size());
			verProduto(in);
		}		
	}
	
	
	public static void verCarrinho(Scanner in) {
		//exibir carrinho de compras
		div("Carrinho de compras");
		System.out.format("%2s%20s%22s%20s%20s%n", "Item", "Produto","Pre�o Unit.","Quantidade","Total");
		
		for (Produto x : carrinhoCompras) {
			System.out.format("%2d%20s%22.2f%20d%20.2f%n",carrinhoCompras.indexOf(x) + 1, x.getNome(), x.getPreco(), x.getQuantidade(), x.getPreco()*x.getQuantidade() );
		}
		
		div();
		System.out.println("\nDigite a op��o desejada:\n[1] Para pagamento.\n[2] Para remover algum produto de seu carrinho.\n[3] Continuar comprando.\nOu qualquer outra tecla para retornar ao menu principal.");
		int opcao = in.nextInt();
		
		if(opcao == 1) {
			pagamento(in);
		}else if(opcao == 2) {
			removerProduto(in);
		}else if(opcao == 3) {
			verProduto(in);
		}else {
			verMenuPrincipal(in);
		}

	}
	
	
	private static void removerProduto(Scanner in) {
		
		System.out.println("Digite o ID do produto que deseja remover.");
		int prod = in.nextInt();
		
		if(prod > 0 && prod <= carrinhoCompras.size()) { //verificar o produto selecionado foi v�lido
			System.out.println("Qual quantidade deseja remover?");
			int quantidadeRemover = in.nextInt();
			
			if( carrinhoCompras.get(prod-1).getQuantidade() >= quantidadeRemover && quantidadeRemover > 0) {// Verificar se a quantidade a remover � v�lida

				estoque.get(carrinhoCompras.get(prod-1).getIdProduto()- 1).incrementarQuantidade(quantidadeRemover); //Adicionar a quantidade removida ao estoque
				if(quantidadeRemover == carrinhoCompras.get(prod-1).getQuantidade()) {
					carrinhoCompras.remove(prod-1);//remover o item do carrinho caso sejam removidos toda quantidade
				}else {
					carrinhoCompras.get(prod-1).decrementarQuantidade(quantidadeRemover);//decrementar quantidade do carrinho caso seja menor que a quantidade total
				}
				System.out.println("Produto removido com sucesso!");
				verCarrinho(in);
			}else {
				System.out.println("Quantidade inv�lida!");
				verCarrinho(in);				
			}
			
		}else {
			System.out.println("Valor incorreto! Por Favor digite um valor entre 1 e " + carrinhoCompras.size());
			verCarrinho(in);
		}		
	}

	
	private static void pagamento(Scanner in) {
		
		double total = 0;
		int parcelas = 1;
		
		for (Produto x : carrinhoCompras){//varrer o carrinho de compras para calcular o total
			total += x.getPreco() * x.getQuantidade();
		}
		//mostrar op��es de pagamento
		div("Pagamento");
		System.out.printf("O valor total da compra foi de R$%.2f%n", total);
		System.out.println("\nDigite a op��o desejada:");
		System.out.println("[1]Cart�o Banco PAN \n[2] PIX ou Dinheiro\n");
		
		int opcao = in.nextInt();
		
		
		if(opcao==1) {//pagamento com Cart�o Banco PAN
			System.out.println("[1] � vista - 15% de desconto\n[2] 3x sem juros\n[3] Parcelado em at� 6x(1.5% a.m)");
			int parcelamento = in.nextInt();//seleciona op��es do cart�o Banco PAN
			
			if(parcelamento == 1) {
				total *= 0.85;//aplicando 15% desconto
				parcelas = 1;
				notaFiscal(total, parcelas,parcelamento , in);			
			}else if(parcelamento == 2) {
				total /= 3; //divindo em 3 parcelas
				parcelas = 3;
				notaFiscal(total, parcelas,parcelamento, in);
			}else if(parcelamento == 3) {
				total *= 1.09344; //juros compostos em 6x, 1.5% ao m�s
				total /= 6; //divindo em 6 parcelas
				parcelas = 6;
				notaFiscal(total, parcelas,parcelamento, in);
			}else {
				System.out.println("Op��o inv�lida!");
				pagamento(in);
			}
			
		}else if(opcao == 2 ) { //Pagamentos em dinheiro/PIX
			notaFiscal(total, parcelas,opcao, in);
		}
	}
			

	private static void notaFiscal(double total, int parcelas, int opcao, Scanner in) {
		//exibir nota fiscal
		div();
		System.out.println("Gama PAN Supermarket \nAv. dos Programadores n�6\nCNPJ: 11.123.456/0001-12");
		System.out.println("Data da compra:" + getDateTime());//exibe a data e hora de execu��o na nota fiscal
				
		div("Nota Fiscal");
		
		System.out.format("%12s%22s%20s%20s%n", "Produto","Pre�o Unit.","Quantidade","Total");
		
		for (Produto x : carrinhoCompras) {//exibe todos os produtos do carrinho de compras na nota fiscal
			System.out.format("%12s%22.2f%20d%20.2f%n", x.getNome(), x.getPreco(), x.getQuantidade(), x.getPreco()*x.getQuantidade() );		
		}
		if(opcao == 1) {//exibe desconto no caso de compra com cart�o Banco Pan
		System.out.printf("\nDesconto na compra: R$ %.2f%n", total/0.85*0.15);
		
		}else if(opcao == 2 || opcao == 3) {//exibe valor da parcela nas compras parceladas e com PIX/dinheiro
			System.out.printf("\nValor parcelado a ser pago: %dx R$ %.2f%n", parcelas, total);
		}
		System.out.printf("Valor total a ser pago: R$ %.2f%n", total*parcelas);
		System.out.printf("Valor do imposto: R$ %.2f%n", total*parcelas*0.09);
		
		div();
		System.out.println("\nDigie a op��o desejada: \n[1] Retornar ao menu principal\n[0] Sair");
		opcao=in.nextInt();
		carrinhoCompras.clear();//esvazia o carrinho de compras para uma futura compra
		
		if(opcao == 1) {
			verMenuPrincipal(in);
		}else {
			System.out.println("Volte sempre!");
			System.exit(0);
		}
	}


	static public void  div(String title) {
		System.out.println("\t\t\t\t\t" + title);
		System.out.println("=================================================================================================");
		
	}
	
	static public void  div() {
			
			System.out.println("=================================================================================================");
			
		}

	
	//Fun��o para criar e formatar um objeto DATE
	private static String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
}

