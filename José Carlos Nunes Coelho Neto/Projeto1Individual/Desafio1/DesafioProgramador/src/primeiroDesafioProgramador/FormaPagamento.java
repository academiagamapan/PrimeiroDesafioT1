package primeiroDesafioProgramador;

public class FormaPagamento {

	public String tipoPagamento() {
		
		String texto = "================== FORMA DE PAGAMENTO ==================\n"
					 + "1 - � vista	(dinheiro ou pix) tem 20% de desconto\n"
					 + "2 - � vista no cart�o Banco Pan tem 15% de desconto\n"
					 + "3 - � vista no cr�dito tem 10% de desconto\n"
					 + "4 - Parcelado em at� 3x sem juros\n"
					 + "5 - Parcelado em at� 12x com juros(10%)";
		return texto;
	}
	
	
	
	public String formaPagamento(int opcao, Double valor) {
		
		Double desconto = 0.0;
		Double juros = 0.0;
		String texto = "";
		Double valorFinal;
		Double valorImposto = 0.09;
		Double valorTotal;
		if (opcao == 1) {
			desconto = 0.2;
			valorFinal = valor - (desconto * valor);
			valorTotal = valorFinal + (valorImposto * valorFinal);			
			texto = "==========================DADOS DO PAGAMENTO===========================\n"
					+ "Pagamento: � vista	(dinheiro ou pix) tem 20% de desconto\n"
					+ "Valor da Compra: "+valor+"\n"
					+ "Valor da compra com Desconto: "+valorFinal+"\n"
					+ "Impostos: "+valorImposto*100+"%\n"
					+ "Valor Total: "+valorTotal+"\n"
					+ "========================================================================";
		} else if (opcao == 2) {
			desconto = 0.16;
			valorFinal = valor - (desconto * valor);
			valorTotal = valorFinal + (valorImposto * valorFinal);
			texto = "==========================DADOS DO PAGAMENTO===========================\n"
					+ "� vista no cart�o Banco Pan tem 15% de desconto\n"
					+ "Valor da Compra: "+valor+"\n"
					+ "Valor da compra com Desconto: "+valorFinal+"\n"
					+ "Impostos: "+valorImposto*100+"%\n"
					+ "Valor Total: "+valorTotal+"\n"
					+ "========================================================================";
		} else if (opcao == 3) {
			desconto = 0.10;
			valorFinal = valor - (desconto * valor);
			valorTotal = valorFinal + (valorImposto * valorFinal);
			texto = "==========================DADOS DO PAGAMENTO===========================\n"
					+ "� vista no cr�dito tem 10% de desconto\n"
					+ "Valor da Compra: "+valor+"\n"
					+ "Valor da compra com Desconto: "+valorFinal+"\n"
					+ "Impostos: "+valorImposto*100+"%\n"
					+ "Valor Total: "+valorTotal+"\n"
					+ "========================================================================";
		} else if (opcao == 4) {
			valorFinal = valor;
			valorTotal = valorFinal + (valorImposto * valorFinal);
			texto = "==========================DADOS DO PAGAMENTO===========================\n"
					+ "Parcelado em at� 3x sem juros\n"
					+ "Valor da Compra: "+valor+"\n"
					+ "Valor da compra com Desconto: "+valorFinal+"\n"
					+ "Impostos: "+valorImposto*100+"%\n"
					+ "Valor Total: "+valorTotal+"\n"
					+ "3 Parcelas de: "+(valorTotal/3)+"\n"
					+ "========================================================================";
		} else if (opcao == 5) {
			juros = 0.1;
			valorFinal = valor + (juros * valor);
			valorTotal = valorFinal + (valorImposto * valorFinal);
			texto = "==========================DADOS DO PAGAMENTO===========================\n"
					+ "Parcelado em at� 12x com juros(10%)\n"
					+ "Valor da Compra: "+valor+"\n"
					+ "Valor da compra com juros: "+valorFinal+"\n"
					+ "Impostos: "+valorImposto*100+"%\n"
					+ "Valor Total: "+valorTotal+"\n"
					+ "12 Parcelas de: "+(valorTotal/3)+"\n"
					+ "========================================================================";
		}
		return texto;
	}
	

	
}
