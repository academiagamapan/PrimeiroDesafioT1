
import java.util.ArrayList;
import java.util.Scanner;

public class Carrinho {

	private ArrayList<Item> compras;
	
	public Carrinho() {
		this.compras = new ArrayList<>();
	}
	
	public void inserirItem(Item item) {
		this.compras.add(item);
	}
	
	public void removerItem(Item item) {
		this.compras.remove(item);
	}
	
	public void fecharConta() {
		double valorTotal = 0;
		
		System.out.println("----------------------------------------------------------------- /n ");
		System.out.println("Finalizando as suas compras \n ");
		
		for (Item itemCompra: compras) {
			String nomeProduto = itemCompra.getProduto().getNome();
			int codProduto = itemCompra.getProduto().getCodigo();
			float precoProduto = itemCompra.getProduto().getPreco();
			int qtd = itemCompra.getQuantidade();
			
			System.out.println(qtd + " - "+nomeProduto);
			
			valorTotal += precoProduto*qtd;
			
			System.out.println("==============================");
		}
		
		System.out.println("");
		System.out.printf("O total dos itens � R$ %.2f", valorTotal);
		System.out.println("");
		
		System.out.println("Qual a forma de Pagamento? \n");
		System.out.println("Nossas op��es s�o : Informe o c�digo abaixo \n");
		System.out.println("[1] - A vista com Cart�o PAN (15% desconto)");
		System.out.println("[2] - Parcelada sem juros (at� 3 vezes)");
		System.out.println("[3] - Parcelada com juros (acima de 3 vezes)");
		
		int formaPagto = 0;
		double valorDesc = 0;
		
		Scanner sc2 = new Scanner(System.in);
		
		formaPagto = sc2.nextInt();
		
		int qtdParc = 0;
		
		if(formaPagto == 1) {
			
			qtdParc = 1;
			
			valorDesc = valorTotal * 0.15;
			
			System.out.printf("Sua compra foi de R$ %.2f", valorTotal);
			
			System.out.printf("\nVoc� economizou R$ %.2f", valorDesc);
			
			System.out.printf("\nO valor a pagar � de R$ %.2f" , (valorTotal - valorDesc));
			
		} else if (formaPagto == 2 ) {
			
			//int qtdParc = 0;
			System.out.println("Informe o n�mero de parcelas - 1,2 ou 3");
			
			qtdParc = sc2.nextInt();
			
			if (qtdParc >0 && qtdParc <4) {
				
				System.out.println("Voc� escolheu a op��o de Compra parcelada sem Juros em " +qtdParc+" parcelas \n");
				System.out.printf("O valor de cada parcela � de R$ %.2f" , (valorTotal /qtdParc));
			} else {
				System.out.println("O n�mero de parcelas est� incorreto");
			}
					
		} else if (formaPagto == 3) {
			
			//int qtdParc = 0;
			System.out.println("Informe o n�mero de parcelas de 3 a 12");
			
			qtdParc = sc2.nextInt();
			
			System.out.println("Voc� optou por parcelar com juros");
			System.out.println("Os juros cobrados s�o de 1% ao m�s");
			
			double jurosTotal = (qtdParc * 0.01);
			
			jurosTotal = valorTotal * jurosTotal;
			
			valorTotal = valorTotal + jurosTotal;
			
			System.out.println("Sua compra ser� paga em "+qtdParc + " parcelas");
			System.out.printf("O valor de cada parcela � de R$ %.2f" , (valorTotal /qtdParc));
			
		}
		
		System.out.println("\n ");
		
		System.out.println("processando o pagamento ...");
		
		System.out.println("SEU PAGAMENTO FOI CONFIRMADO \n");
		
		System.out.println("Inserir alguma observa��o ao pedido? [1] Sim [0] Emitir Nota Fiscal");
		
		int optaObs = 0;
		String obs = "";
		
		
		optaObs = sc2.nextInt();
		
		if (optaObs == 1) {
			obs = sc2.next();
		}
		
		
		System.out.println("Finalizando o pedido e gerando a nota fiscal ");
		
		
		System.out.println("\n \n=======================================================================================");
		System.out.println("======= NOTA FISCAL N�. 1050 - MEGA STORE - PEDIDO 5544 -  EMISS�O 25/10/2021 ========= ");
		System.out.println("========================================================================================");
		System.out.println("");
		System.out.println("                                             PRODUTOS");
		System.out.println("");
		System.out.println("........................................................................................... \n");
		
		int seq = 1;
		
		for (Item itemCompra: compras) {
			String nomeProduto = itemCompra.getProduto().getNome();
			int codProduto = itemCompra.getProduto().getCodigo();
			float precoProduto = itemCompra.getProduto().getPreco();
			int qtd = itemCompra.getQuantidade();
			
			
			System.out.println("Item n�mero: " + seq++);
			System.out.println(codProduto + " - "+nomeProduto);
			System.out.printf("Valor: R$ %.2f",precoProduto);
			System.out.println("");
			System.out.println("Quantidade: "+qtd);
			System.out.printf("Total do item : R$ %.2f" , precoProduto * qtd);
			
			System.out.println(" \n");
			
		}
		
		System.out.println("                                             PAGAMENTO");
		System.out.println("........................................................................................... \n");
		System.out.println("Forma de pagamento :"+ (formaPagto == 1 ? "[1] - A vista com Cart�o PAN (15% desconto)" :
													formaPagto == 2 ? "[2] - Parcelada sem juros (at� 3 vezes)" : 
														              "[3] - Parcelada com juros (acima de 3 vezes)"));
		
		System.out.println("QUANTIDADE DE PARCELAS "+qtdParc);
		System.out.printf("VALOR DA PARCELA R$ %.2f" , (valorTotal /qtdParc));
		System.out.println("");
		
		System.out.println("                                             TOTAIS E FRETE");
		
		System.out.println("........................................................................................... \n");
		
		System.out.printf("TOTAL DA NOTA FISCAL ...................... R$ %.2f ",valorTotal);
		System.out.println("\n");
		System.out.println("Frete por conta do vendedor");
		System.out.println("TRANSPORTADORA: CONFIA TRANSPORTES");
		
		
		System.out.printf("\nOs impostos nesta compra s�o de R$ %.2f", valorTotal * 0.09);
		System.out.println("\n");
		System.out.println("+=======================================================================================");
		System.out.println("Observa��es finais: "+obs);
		System.out.println("======================================================================================== \n");
		
		System.out.println("Grupo 3 Mega Store agradece sua prefer�ncia, at� a pr�xima, Dev! \n");
			
		
	}
		
}
