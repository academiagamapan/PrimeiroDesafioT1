package br.com.mercado.pan.pagamentos;

import java.util.Scanner;

public class MenuPagamento {
	
	public Integer MenuFormaPagamento(Scanner scan) {
		Integer opcao = 0;
		
		while(true) {
			System.out.println("\n\n--------------- Pagamento --------------------");
			System.out.print("\nFormas de pagamento:" +
					         "\n1- � vista no dinheiro ou pix - 20% de desconto"+
			                 "\n2- � vista no cr�dito - 10% de desconto" +
					         "\n3- Parcelado em at� 3x sem juros" +
			                 "\nDigite a op��o de pagamento: ");
			opcao = scan.nextInt();
			
			if(opcao < 1 || opcao > 3) System.out.println("Opc��o inv�lida!");
			else return opcao;				
		}				
	}
	
	
}
