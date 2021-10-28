package LojaVirtualFilipe;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Carrinho {
	public static void main(String[] args) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		Scanner scan = new Scanner(System.in);
		
		int cod; // código do item
		int quantidade; // quantidade de itens
		int nf; // nota fiscal
		char resposta;
		double total;
		final double imposto = 0.09;
		
		// ArrayList dos produtos do estoque e dos produtos do carrinho.
		ArrayList<Produto> produtos = new ArrayList<Produto>();
		ArrayList<Produto> carrinho = new ArrayList<Produto>();
		
		// Adicionando produtos no estoque.
		produtos.add(new Produto(0, "Picanha", 50, 59.98));
		produtos.add(new Produto(1, "Óleo", 50, 3.98));
		produtos.add(new Produto(2, "Chocolate", 50, 4.98));
		produtos.add(new Produto(3, "Morangos", 50, 6.98));
		produtos.add(new Produto(4, "Danoninho", 50, 5.98));
		produtos.add(new Produto(5, "Requeijão", 50, 2.98));
		produtos.add(new Produto(6, "Alface", 50, 1.98));
		produtos.add(new Produto(7, "Laranja", 50, 3.98));
		produtos.add(new Produto(8, "Desodorante", 30, 4.98));
		produtos.add(new Produto(9, "Papel Higiênico", 50, 11.98));
		
		System.out.print("Nome do cliente: ");
		String nomeCliente = scan.next();
		System.out.println("Olá, " + nomeCliente + "! Seja bem vindo!"); // Boas vindas ao cliente.
		
		// INCLUINDO ITENS NO CARRINHO
		do {
			Util.mostrarProdutos(produtos); // mostrando a tabela de produtos do estoque para compra.
			do {
				System.out.print("Informe o código do produto desejado: "); // pedindo código do produto para compra.
				cod = scan.nextInt();
			} while (cod < 0 || cod > 9);
			System.out.print("Informe a quantidade que deseja comprar: "); // pedindo quantidade de produto para compra.
			quantidade = scan.nextInt();
			
			if (produtos.get(cod).getQuantidade() - quantidade >= 0) { // verificando se há estoque para compra.
				produtos.get(cod).setQuantidade(produtos.get(cod).getQuantidade() - quantidade); // reduzindo a quantidade de compra do estoque.
				carrinho.add(new Produto((cod), produtos.get(cod).getNomeProduto(), quantidade,  // instanciando o produto escolhido para compra dentro do ArrayList do carrinho.
						produtos.get(cod).getPrecounit())); 
			} else {
				System.out.println("Estoque indisponível.");
			}
			System.out.println("Deseja continuar escolhendo? [S/N] ");
			resposta = scan.next().charAt(0);
		} while (resposta == 'S' || resposta == 's');
		
		total = Util.calculaCarrinho(carrinho); // calcula o carrinho
		
		// REMOVE ITENS DO CARRINHO
		System.out.print("Remover itens do carrinho? [S/N] ");
		resposta = scan.next().charAt(0);
		while ((resposta == 'S' || resposta == 's')) {
			Util.removerProduto(carrinho);
			if (carrinho.isEmpty()) {
				break;
			}
			System.out.print("Deseja continuar removendo? [S/N] ");
			resposta = scan.next().charAt(0);
			total = Util.calculaCarrinho(carrinho);
		}
		
		// ALTERA A QUANTIDADE DE UNIDADES DO PRODUTO
		if (!carrinho.isEmpty()) {
			total = Util.calculaCarrinho(carrinho);
			System.out.print("Deseja alterar a quantidade de algum item do carrinho? [S/N] ");
			resposta = scan.next().charAt(0);
			while ((resposta == 'S' || resposta == 's')) {
				Util.alterarQuantidade(carrinho, produtos);
				System.out.print("Deseja continuar alterando? [S/N] ");
				resposta = scan.next().charAt(0);
				total = Util.calculaCarrinho(carrinho);
			}
		}
		
		// FORMA DE PAGAMENTO, NOVO VALOR E NOTA FISCAL
		Pagamento pagamento = new Pagamento();
		if (!carrinho.isEmpty()) {
			pagamento.formaDePagamento(carrinho, total);
			
			// NÚMERO DA NOTA FISCAL
			do {
				Random gerador = new Random();
				nf = gerador.nextInt(999);
			} while (nf < 100);
			
			// NOTA FISCAL
			System.out.println("\n=============================================================");
			System.out.println("                         NOTA FISCAL                         ");
			System.out.println("=============================================================");
			System.out.println("RAZÃO SOCIAL: Supermercado PANdeLó");
			System.out.println("CNPJ: 12.456.789/0001-12");
			System.out.println("ENDEREÇO: Rua do Pão de Ló, 123, Beco Diagonal, São Paulo-SP");
			System.out.println(dtf.format(LocalDateTime.now()));
			System.out.println("NF-e: " + nf);
			System.out.println("-------------------------------------------------------------");
			for (Produto p : carrinho)
				System.out.printf("%s\t\tR$ %.2f\n", p.toStringNota(), p.getPrecounit() * p.getQuantidade());
			System.out.println("-------------------------------------------------------------");
			System.out.printf("VALOR DO DESCONTO: R$ %.2f\n", (total - pagamento.getNovoValor()));
			System.out.printf("VALOR FINAL: R$ %.2f\n", pagamento.getNovoValor());
			System.out.printf("VALOR DO IMPOSTO: R$ %.2f\n", total * imposto);
			System.out.println("=============================================================");
		} else {
			System.out.println("Que pena! O seu carrinho está vazio...");
			System.out.println("Espero vê-lo em breve!");
		}
		scan.close();
	}

}
