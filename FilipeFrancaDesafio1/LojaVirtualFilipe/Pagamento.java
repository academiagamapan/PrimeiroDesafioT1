package LojaVirtualFilipe;

import java.util.ArrayList;
import java.util.Scanner;

public class Pagamento {

	private double novoValor;

	public void setPixDinheiro(double total) {
		this.novoValor = total * 0.8;
	}

	public void setCredito(double total, char r) {

		if (r == 's' || r == 'S') {
			this.novoValor = total * 0.85;
		} else {
			this.novoValor = total * 0.9;
		}

	}

	public void setParcelado(double total) {
			this.novoValor = total;
	}

	public double getNovoValor() {
		return novoValor;
	}

	@SuppressWarnings("resource")
	public void formaDePagamento(ArrayList<Produto> carrinho, double total) {
		int formaPagamento;
		System.out.println("\n===================================================================================");
		System.out.println("                                FORMA DE PAGAMENTO                                ");
		System.out.println("===================================================================================");
		do {
			System.out.println("1 - Pix ou Dinheiro (20% de desconto).");
			System.out.println("2 - Cartão à vista (10% de desconto).");
			System.out.println("3 - Cartão parcelado em até 3x.");
			System.out.print("Escolha o código da opção de pagamento: ");
			formaPagamento = (new Scanner(System.in)).nextInt();
		} while (formaPagamento < 1 || formaPagamento > 3);

		if (formaPagamento == 3) {
			this.setParcelado(total);
		} else if (formaPagamento == 2) {
			System.out.println("Cartão Pan de oferece 15% de desconto.");
			System.out.print("Você quer usar o seu cartão Pan? [S/N] ");
			char r = (new Scanner(System.in)).next().charAt(0);
			this.setCredito(total, r);
		} else {
			this.setPixDinheiro(total);
		}
		
	}

}