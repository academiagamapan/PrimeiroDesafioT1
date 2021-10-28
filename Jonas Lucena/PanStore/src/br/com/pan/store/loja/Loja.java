package br.com.pan.store.loja;

import java.util.Scanner;

import br.com.pan.store.dados.Database;
import br.com.pan.store.entidades.Carrinho;
import br.com.pan.store.entidades.FormaDePagamento;
import br.com.pan.store.utils.ValidaCodigos;

public class Loja {

    public static void main(String[] args) {

        Database.popularProdutos();
        Database.popularFormasDePagamento();

        Scanner sc = new Scanner(System.in);

        String opcao = "";

        Carrinho carrinho = null;

        Integer sequencia = 0;
        
        System.out.println("========================== BEM VINDO A PANSTORE =============================");

        while (true) {

            Database.listarProdutos();

            if (carrinho == null) {
            	 System.out.println("======================== ESCOLHA O PRODUTO PELO CÓDIGO ======================\n"         
                 		+ "============================= 0 PARA SAIR DA LOJA ===========================\n");
            } else {
            	System.out.println("======================== ESCOLHA O PRODUTO PELO CÓDIGO ======================\n"
                 		+ "========================= F PARA FINALIZAR O CARRINHO =======================\n"
                 		+ "============================= 0 PARA SAIR DA LOJA ===========================\n");
            }
                               
            System.out.print("DIGITE A SUA OPÇÃO: ");
            
            opcao = sc.next();
            
            if (opcao.equals("0")) {
            	System.out.println("\n======================= OBRIGADO POR USAR A NOSSA LOJA ======================");
                break;
            } else if (ValidaCodigos.validaCodigoProduto(opcao) && !Character.isAlphabetic((opcao.charAt(0)))) {
            	
                if (carrinho == null) {
                	System.out.print("DIGITE O NOME DO CLIENTE: ");
                    String nomeCliente = sc.next();
                    sequencia++;
                    carrinho = new Carrinho(sequencia, nomeCliente);
                    nomeCliente = sc.nextLine();
                }

                while(true) {
                	System.out.print("DIGITE A QUANTIDADE: ");
                    String qtd = sc.next();
                    
                    try {
                    	Integer quantidade = Integer.parseInt(qtd);
                    	
                    	 if (!carrinho.adicionarItem(Database.getProdutos().get(Integer.valueOf(opcao)), quantidade)) {
                             System.out.println("\nNÃO HÁ QUANTIDADE SUFICIENTE PARA O ITEM SELECIONADO\n");
                         }
                         
                         carrinho.listarProdutosCarrinho();
                         
                         break;
                    } catch (NumberFormatException nfe)  {
                 	    System.out.println("\nQUANTIDADE INVÁLIDA\n");
                    }        
                }                    

            } else if (opcao.toUpperCase().equals("F") && carrinho != null) {
            	
            	while (true) {
            		Database.listarFormasDePagamento();
                    System.out.print("ESCOLHA UMA FORMA DE PAGAMENTO: ");

                    String codigoForma = sc.next();

                    if (ValidaCodigos.validaCodigoPagamento(codigoForma)) {
                        FormaDePagamento forma = Database.getFormasDePagamento().get(Integer.valueOf(codigoForma));

                        carrinho.gerarCupomFiscal(forma);

                        carrinho = null;
                        
                        break;
                    } else {
                   	 System.out.println("\nOPÇÃO INVÁLIDA");
                   }                  
            	}
                                              
            } else {
                System.out.println("\nOPÇÃO INVÁLIDA");
            }
        }
        sc.close();
    }
}
