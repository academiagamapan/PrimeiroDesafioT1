package entidades;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Loja {
	
	private String nome = "GRUPO 3 - MEGA STORE";
	private double tributo = 0.09;
	private List<Produto> produtos = new ArrayList<>();
	
	public String getNome() {
		return nome;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public double getTributo() {
		return tributo;
	}
	

	public void pagDinPix(double valorCarrinho) {
		double valorTributos = valorCarrinho * this.tributo;
		double valorDesc = (valorCarrinho + valorTributos) * 0.2;
		double valorTotal = valorCarrinho + valorTributos - valorDesc;
		
		System.out.println("PAGAMENTO À VISTA/PIX");
		System.out.println();
		System.out.printf("TOTAL DO CARRINHO:   R$%.2f %n", valorCarrinho);
		System.out.printf("VALOR TRIBUTÁRIO:    R$%.2f %n", valorTributos);
		System.out.printf("DESCONTO NA COMPRA:  R$%.2f %n", valorDesc);
		System.out.printf("TOTAL PAGO:          R$%.2f %n", valorTotal);
		System.out.println();
		
	}
	
	public void pagAVistaCred(double valorCarrinho) {
		double valorTributos = valorCarrinho * this.tributo;
		double valorDesc = (valorCarrinho + valorTributos) * 0.1;
		double valorTotal = valorCarrinho + valorTributos - valorDesc;
		
		System.out.println("PAGAMENTO À VISTA CARTÃO DE CRÉDITO");
		System.out.println();
		System.out.printf("TOTAL DO CARRINHO:   R$%.2f %n", valorCarrinho);
		System.out.printf("VALOR TRIBUTÁRIO:    R$%.2f %n", valorTributos);
		System.out.printf("DESCONTO NA COMPRA:  R$%.2f %n", valorDesc);
		System.out.printf("TOTAL PAGO:          R$%.2f %n", valorTotal);
		System.out.println();
	}
	
	public void pagAVistaPan(double valorCarrinho) {
		double valorTributos = valorCarrinho * this.tributo;
		double valorDesc = (valorCarrinho + valorTributos) * 0.15;
		double valorTotal = valorCarrinho + valorTributos - valorDesc;
		
		System.out.println("PAGAMENTO À VISTA CARTÃO BANCO PAN");
		System.out.println();
		System.out.printf("TOTAL DO CARRINHO:   R$%.2f %n", valorCarrinho);
		System.out.printf("VALOR TRIBUTÁRIO:    R$%.2f %n", valorTributos);
		System.out.printf("DESCONTO NA COMPRA:  R$%.2f %n", valorDesc);
		System.out.printf("TOTAL PAGO:          R$%.2f %n", valorTotal);
		System.out.println();
	}
	
	public void pagSemJuros(double valorCarrinho, int parcela) {
		double valorTributos = valorCarrinho * this.tributo;
		double valorTotal = valorCarrinho + valorTributos;
		double valorParcela = valorTotal / parcela;
		
		System.out.printf("PAGAMENTO PARCELADO EM %dx SEM JUROS CARTÃO DE CRÉDITO %n", parcela);
		System.out.println();
		System.out.printf("TOTAL DO CARRINHO:    R$%.2f %n", valorCarrinho);
		System.out.printf("VALOR TRIBUTÁRIO:     R$%.2f %n", valorTributos);
		System.out.println("DESCONTO NA COMPRA:   R$0");
		System.out.printf("TOTAL PAGO:           R$%.2f em %d vezes de R$%.2f %n", valorTotal,  parcela,  valorParcela);
		System.out.println();
	}
	
	public void pagComJuros(double valorCarrinho, int parcela) {
		double valorTributos = valorCarrinho * this.tributo;
		double valorJuros = (valorCarrinho + valorTributos) * 0.03;
		double valorTotal =  valorCarrinho + valorTributos + valorJuros;
		double valorParcela = valorTotal / parcela;
		
		System.out.printf("PAGAMENTO PARCELADO EM %dx COM JUROS CARTÃO DE CRÉDITO %n", parcela);
		System.out.println();
		System.out.printf("TOTAL DO CARRINHO:    R$%.2f %n", valorCarrinho);
		System.out.printf("VALOR TRIBUTÁRIO:     R$%.2f %n", valorTributos);
		System.out.printf("TOTAL DE JUROS:       R$%.2f %n", valorJuros);
		System.out.println("DESCONTO NA COMPRA:   R$0");
		System.out.printf("TOTAL PAGO:           R$%.2f em %d vezes de R$%.2f %n", valorTotal,  parcela,  valorParcela);
		System.out.println();
	}
	
	public void notaFiscal(CarrinhoDeCompras carrinho) {
			System.out.println("                                		 ~ NOTA FISCAL ~                              ");
			System.out.println();
			System.out.println();
			System.out.println(this.nome);
			System.out.println("Rua da Confiança, nº3");
			System.out.println("CNPJ: 1566547899-00 ");
			LocalDateTime agora = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			System.out.println(agora.format(formatter));
			System.out.println();
			System.out.println();
			System.out.println("==========================================================================================================");
			System.out.println("PRODUTO 	    		  	  PREÇO UNIT.(R$)         QTD PRODUTOS        PREÇO TOTAL(R$)");
			for (Item item : carrinho.getItens()){
			    String nome = item.getProduto().getNome();
				double preco = item.getProduto().getPreço();
				int qtdCarrinho =  item.getQuantidade();
				double precoTotal = item.getProduto().getPreço() * item.getQuantidade();
				
	           	System.out.format("%-35s \t    R$%.2f \t  \t  	%d \t  	   R$%.2f %n", nome, preco, qtdCarrinho, precoTotal);
	           	System.out.println("==========================================================================================================");
			}
			System.out.println();
			System.out.println();
			
	}
	
		
	
}
