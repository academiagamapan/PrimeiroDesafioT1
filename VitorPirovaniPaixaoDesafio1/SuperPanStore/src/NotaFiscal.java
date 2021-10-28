import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;   

public class NotaFiscal {
	
	private ArrayList<Produto> produtos;
	private double totalGeral = 0;
	private double desconto=0;
	private double juros=0;
	private int formaPagamento;
	
		
	public NotaFiscal(ArrayList<Produto> carrinho) {
		this.produtos = carrinho;
	}
	
	
	public ArrayList<Produto> getCarrinho() {
		return produtos;
	}


	public void setCarrinho(ArrayList<Produto> carrinho) {
		this.produtos = carrinho;
	}


	public double getTotalGeral() {
		return totalGeral;
	}


	public void setTotalGeral() {
		for(Produto produto: this.produtos) {
			this.totalGeral+=produto.getUnidadesDisponiveis()*produto.getValorUnitario();
		}
	}

	public double getDesconto() {
		return desconto;
	}
	
	public void setDesconto() {
		if(this.formaPagamento== 1) {
			this.desconto=this.totalGeral*0.3;
		}
		else if(this.formaPagamento==2) {
			this.desconto=this.totalGeral*0.1;
		}
		else if(this.formaPagamento==3) {
			this.desconto=0;
		};
	}
	
	public double getJuros() {
		return juros;
	}
	
	public void setJuros() {
		if(this.formaPagamento== 4) {
			this.juros = this.totalGeral*0.035;
		}
		
	}
	
	public int getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento() {
		Scanner entrada = new Scanner(System.in);
		System.out.println("Escolha uma das formas de pagamento abaixo:");
		System.out.println("Digite 1 para pagamanto com cart�o PAN no d�bito e voc� ter� 30% de desconto sobre o valor total porque PAN � PAN!");
		System.out.println("Digite 2 para pagamanto � vista (dinheiro ou pix) e voc� ter� 10% de desconto sobre o valor total.");
		System.out.println("Digite 3 para pagamanto � vista no cr�dito.");
		System.out.println("Digite 4 para pagamanto em 3x no cr�dito (Juros de 3,5%).");
		int pagamento = entrada.nextInt();

		while(pagamento != 1 && pagamento != 2 && pagamento != 3 && pagamento != 4 && pagamento != 5) {
			System.out.println("Digite 1 para pagamanto com cart�o PAN no d�bito e voc� ter� 30% de desconto sobre o valor total porque PAN � PAN!");
			System.out.println("Digite 2 para pagamanto � vista (dinheiro ou pix) e voc� ter� 10% de desconto sobre o valor total.");
			System.out.println("Digite 3 para pagamanto � vista no cr�dito.");
			System.out.println("Digite 4 para pagamanto em 3x no cr�dito (Juros de 3,5%).");
			System.out.println("Digite 5 para sair (CUIDADO: O carrinho ser� esvaziado!).");
			pagamento = entrada.nextInt();
		};
		entrada.close();
		this.formaPagamento = pagamento;
	}


	public void imprimeNotaFiscal(){
		System.out.println("Aqui est� sua nota fiscal...\n");
		System.out.println("--------------------------------------------------------------------------------------------");
		System.out.println("---------------------------------------- NOTA FISCAL----------------------------------------");
		System.out.println("--------------------------------------------------------------------------------------------");
	   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
	   LocalDateTime agora = LocalDateTime.now();  
	   System.out.println("Data da emiss�o: "+dtf.format(agora));
		System.out.println("\t C�DIGO \t NOME \t\t VALOR \t\t UNIDADES \t TOTAL EXTRATIFICADO");
		for(Produto produto : this.produtos) {
			System.out.printf("\t %d \t\t %s \t %.2f \t %d \t\t %.2f \n",produto.getCodigo(),produto.getNome() ,
					produto.getValorUnitario(),produto.getUnidadesDisponiveis(),
					produto.getUnidadesDisponiveis()*produto.getValorUnitario());
		}

		double imposto = (this.totalGeral-this.desconto+this.juros)*0.39;
		if(this.formaPagamento== 1) {
			System.out.printf("TOTAL = R$ %.2f \n",this.totalGeral);
			System.out.printf("TOTAL COM DESCONTO = R$ %.2f \n",this.totalGeral-this.desconto);
			System.out.println("--------------------------------------------------------------------------------------------\n");
			System.out.printf("O cart�o PAN te proporcionou R$ %.2f (30%%) de desconto!\n",this.desconto);
			System.out.println("--------------------------------------------------------------------------------------------\n");
			System.out.printf("Do valor total pago, R$ %.2f � imposto!\n", imposto);
			System.out.println("--------------------------------------------------------------------------------------------\n");
		}
		else if(this.formaPagamento==2) {
			System.out.printf("TOTAL = R$ %.2f \n",this.totalGeral);
			System.out.printf("TOTAL COM DESCONTO = R$ %.2f \n",this.totalGeral-this.desconto);
			System.out.printf("Pagando � vista voc� obteve R$ %.2f (10%%) de desconto!\n",this.desconto);
			System.out.println("--------------------------------------------------------------------------------------------\n");
			System.out.printf("Utilize o cart�o PAN na sua pr�xima compra! Com ele voc� obt�m 30%% de desconto\n");
			System.out.println("--------------------------------------------------------------------------------------------\n");
			System.out.printf("Do valor total pago, R$ %.2f � imposto!\n", imposto);
			System.out.println("--------------------------------------------------------------------------------------------\n");
		}
		else if(this.formaPagamento==3) {
			System.out.printf("TOTAL = R$ %.2f \n",this.totalGeral);
			System.out.println("--------------------------------------------------------------------------------------------\n");
			System.out.printf("Utilize o cart�o PAN na sua pr�xima compra! Com ele voc� obt�m 30%% de desconto\n");
			System.out.println("--------------------------------------------------------------------------------------------\n");
			System.out.printf("Do valor total pago, R$ %.2f � imposto!\n", imposto);
			System.out.println("--------------------------------------------------------------------------------------------\n");
		}else{
			System.out.printf("TOTAL (com juros) = R$ %.2f \n",this.totalGeral+this.juros);
			System.out.println("--------------------------------------------------------------------------------------------\n");
			System.out.printf("Utilize o cart�o PAN na sua pr�xima compra! Com ele voc� obt�m 30%% de desconto\n");
			System.out.println("--------------------------------------------------------------------------------------------\n");
			System.out.printf("Do valor total pago, R$ %.2f � imposto!\n", imposto);
			System.out.println("--------------------------------------------------------------------------------------------\n");		
		};	
	}
}
