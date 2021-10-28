import java.util.ArrayList;
import java.util.Scanner;

public class TesteSuperPanStore {

	public static void boasVindas() {
		System.out.println("____________________________________________________________________");
		System.out.println("-------------------- Bem-vindo a Super PAN store -------------------");
	}
	
	public static void agradecimento() {
		System.out.println("--------------------------------------------------------");
		System.out.println("\t A Super PAN Store agradece. Volte sempre!");
		System.out.println("________________________________________________________");
	}
	
	public static void sucesso(Produto produto) {
		System.out.println("--------------------------------------------------------");
		System.out.println(produto.getUnidadesDisponiveis()+" unidades do produto " +produto.getNome()+" adicionada(s) com sucesso!");
		System.out.println("--------------------------------------------------------");
	}
	
	public static void mostraEstoque(ArrayList<Produto> estoque){
		System.out.println("\t\t Confira abaixo a nossa lista de produtos:");
		System.out.println("\t CÓDIGO \t NOME \t\t VALOR \t\t UNIDADES");
		for(Produto produto : estoque) {
			System.out.printf("\t %d \t\t %s \t %.2f \t %d \n",produto.getCodigo(),produto.getNome() ,
					produto.getValorUnitario(),produto.getUnidadesDisponiveis());
		}
		System.out.println("-----------------------------------------------------------------------");
	}
	
	public static void mostraCarrinho(ArrayList<Produto> carrinho){
		System.out.println("\t\t Confira abaixo o seu carrinho:");
		System.out.println("\t CÓDIGO \t NOME \t\t VALOR \t\t UNIDADES");
		for(Produto produto : carrinho) {
			System.out.printf("\t %d \t\t %s \t %.2f \t %d \n",produto.getCodigo(),produto.getNome() ,
					produto.getValorUnitario(),produto.getUnidadesDisponiveis());
		}
		double total = 0;
		for(Produto produto: carrinho) {
			total+=produto.getUnidadesDisponiveis()*produto.getValorUnitario();
		}
		System.out.printf("TOTAL = R$ %.2f ",total);
		System.out.println("--------------------------------------------------------\n");
	}
	
	public static void notaFiscal(ArrayList<Produto> carrinho, double desconto){
		System.out.println("Aqui está su not fiscal...\n");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("---------------------------------- NOTA FISCAL----------------------------------");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("\t CÓDIGO \t NOME \t\t VALOR \t\t UNIDADES \t TOTAL EXTRATIFICADO");
		for(Produto produto : carrinho) {
			System.out.printf("\t %d \t\t %s \t %.2f \t %d \n",produto.getCodigo(),produto.getNome() ,
					produto.getValorUnitario(),produto.getUnidadesDisponiveis(),produto.getUnidadesDisponiveis()*produto.getValorUnitario());
		}
		double total = 0;
		for(Produto produto: carrinho) {
			total+=produto.getUnidadesDisponiveis()*produto.getValorUnitario();
		}
		double imposto = (total+desconto)*0.39;
		System.out.printf("TOTAL COM DESCONTO = R$ %.2f \n",total-desconto);
		System.out.printf("Do valor total pago, R$ %.2f é imposto!\n", imposto);
		System.out.println("--------------------------------------------------------\n");
	}
	
	public static void main(String[] args) {
		
		Scanner entrada = new Scanner(System.in);

		ArrayList<Produto> estoque = new ArrayList<Produto>();
		ArrayList<Produto> carrinho = new ArrayList<Produto>();
		
		Produto iPhone13 = new Produto(1, "iPhone 13", 12003.5, 5);
		Produto camisa = new Produto(2, "Camisa", 150, 20);
		Produto sapato = new Produto(3, "Sapato", 123.5, 12);
		
		estoque.add(iPhone13);
		estoque.add(camisa);
		estoque.add(sapato);
		
		boasVindas();
		
		System.out.println("Gostaria de iniciar as compras? S/N");
		String comprar = entrada.next();
		System.out.println(comprar);
		while(comprar.equals("s") || comprar.equals("S")) {
			mostraEstoque(estoque);
			System.out.println("Digite o código do produto que deseja adicionar ao seu carrinho de compras:");
			int codigo= entrada.nextInt();
			for(Produto produto: estoque) {
				if(produto.getCodigo()==codigo && produto.getUnidadesDisponiveis()==0) {
					System.out.println("Desculpe. O produto "+produto.getNome()+" está esgotado.");
					System.out.println("Gostaria de comprar outro produto? S/N");
					comprar = entrada.next();
				}else if(produto.getCodigo()==codigo){
					System.out.println("Quantas unidades de "+produto.getNome()+" deseja comprar?");
					int unidades = entrada.nextInt();
					if(unidades==0) {
						System.out.println("O valor de unidades deve ser maior que zero.");
						System.out.println("--------------------------------------------");
					}
					else if(produto.getUnidadesDisponiveis() < unidades) {
						System.out.println("Infelizmente, no momento não conseguiremos atender a sua demanda.");
						System.out.println("Em nosso estoque temos somente "+produto.getUnidadesDisponiveis() 
											+" unidade(s) disponíveis do produto " +produto.getNome());
						System.out.println("Gostaria de adicioná-la(s) aos seu carrinho?");
						String adicionar = entrada.next();
						if(adicionar.equals("s") || adicionar.equals("S")) {
							Produto produtoCar = new Produto(codigo,produto.getNome(),produto.getValorUnitario(),produto.getUnidadesDisponiveis());
							produto.setUnidadesDisponiveis(0);
							carrinho.add(produtoCar);
							sucesso(produtoCar);
							System.out.println("Gostaria de comprar outro produto? S/N");
							comprar = entrada.next();
						}
					}else {
						Produto produtoCar = new Produto(codigo,produto.getNome(),produto.getValorUnitario(),unidades);
						produto.setUnidadesDisponiveis(produto.getUnidadesDisponiveis()-unidades);
						carrinho.add(produtoCar);
						sucesso(produtoCar);
						System.out.println("Gostaria de comprar outro produto? S/N");
						comprar = entrada.next();
					}
				}
			}
		}
		
		if(carrinho.isEmpty()) {
			agradecimento();
		}else {
			mostraCarrinho(carrinho);
			System.out.println("Escolha uma das formas de pagamento abaixo:");
			System.out.println("Digite 1 para pagamanto com cartão PAN no débito e você terá 30% de desconto sobre o valor total porque PAN é PAN!");
			System.out.println("Digite 2 para pagamanto à vista (dinheiro ou pix) e você terá 10% de desconto sobre o valor total.");
			System.out.println("Digite 3 para pagamanto à vista no crédito.");
			System.out.println("Digite 4 para pagamanto em 3x no crédito (Juros de 1,5% a.m).");
	
			int pagamento = entrada.nextInt();
	
			while(pagamento != 1 || pagamento != 2  || pagamento != 3 || pagamento != 4 || pagamento != 5) {
//				System.out.println("linha 142 "+pagamento);
				System.out.println("Digite 1 para pagamanto com cartão PAN no débito e você terá 30% de desconto sobre o valor total porque PAN é PAN!");
				System.out.println("Digite 2 para pagamanto à vista (dinheiro ou pix) e você terá 10% de desconto sobre o valor total.");
				System.out.println("Digite 3 para pagamanto à vista no crédito.");
				System.out.println("Digite 4 para pagamanto em 3x no crédito (Juros de 3,5%).");
				System.out.println("Digite 5 para sair (CUIDADO: O carrinho será esvaziado!).");
				pagamento = entrada.nextInt();
				System.out.println(pagamento);
				break;
			}
			System.out.println(pagamento);
			double total = 0;
			for(Produto produto: carrinho) {
				total+=produto.getUnidadesDisponiveis()*produto.getValorUnitario();
			}
			if(pagamento== 1) {
				double desconto=-total*0.3;
				System.out.println("Excelente escolha!! O cartão PAN te proporcionou R$ "+-desconto+"  de desconto!");
				notaFiscal(carrinho, desconto);
			}else if(pagamento==2) {
				double desconto=-total*0.1;
				System.out.println("Pagando à vista você obteve R$ "+ -desconto+ " de desconto!");
				notaFiscal(carrinho, desconto);
			}
			else if(pagamento==3) {
				System.out.println("1x no crédito você não economiza! Mude para o PAN par obter 30% de desconto!");
				notaFiscal(carrinho, 0);
			}else{
				double desconto=total*0.035;
				System.out.println("O valor total da sua compra foi "+ total+desconto+ " de desconto!");
				notaFiscal(carrinho, desconto);
				
			};
			
		}
		
		entrada.close();

	}

}
