
import java.util.*;

public class Principal {

	public static void main(String[] args) {
		Carrinho carrinho = new Carrinho();
		
		Product p1 = new Product();
		p1.setCodigo(1);
		p1.setNome("CAMISETA CTRL + ALT + DEL   ");
		p1.setPreco(39.90F);
		p1.setEstoque(30);
		
		Product p2 = new Product();
		p2.setCodigo(2);
		p2.setNome("CAMISETA AQUI TA FUNCIONANDO");
		p2.setPreco(29.90F);
		p2.setEstoque(25);
		
		Product p3 = new Product();
		p3.setCodigo(3);
		p3.setNome("CAMISETA ESTRANHO");
		p3.setPreco(10.9F);
		p3.setEstoque(32);
		
		Product p4 = new Product();
		p4.setCodigo(4);
		p4.setNome("CAMISETA CONFIA!");
		p4.setPreco(99.9F);
		p4.setEstoque(31);
		
		List<Product> produtos = new ArrayList<Product>();
		produtos.add(p1);
		produtos.add(p2);
		produtos.add(p3);
		produtos.add(p4);
		
		System.out.println("\t============================================================================================ \n" );
		System.out.println("\t============================= Bem vindo ao Grupo3 - Mega Store ============================= \n" );
		System.out.println("\t=============================  A loja das camisetas de TI :D  ============================== \n" );
		System.out.println("\t============================================================================================ \n\n\n" );
		
		System.out.println("\t---------+-----------------------------------------------+-----------------------+----------");
		System.out.println("\tCODIGO \t | \t\t\tNOME  \t\t\t | \tPREÇO DE VENDA \t |   ESTOQUE");
		System.out.println("\t---------+-----------------------------------------------+-----------------------+----------");
		
		for (Product products: produtos){
			int cod = products.getCodigo();
		    String nome = products.getNome();
			float preco = products.getPreco();
			int estoque = products.getEstoque();
			
           	System.out.format("%12d \t |   %-40s \t | \t R$ %.2f \t | %6d %n", cod, nome, preco, estoque);
           	System.out.println("\t---------+-----------------------------------------------+-----------------------+----------");
		}
		
		
		Scanner sc = new Scanner(System.in);
		int opcao = 0;
		
		System.out.println("Digite o numero da opção desejada");
		System.out.println("\t1 - Iniciar as compras");
		System.out.println("\t2 - Fica para proxima");
		
		opcao = sc.nextInt();
			
	    switch (opcao) {
	    	case 1:
	    		int cod =  0;
	    		System.out.println("Maravilha! Vamos as compras... ");
				System.out.println("Informe o código do Produto");
				try {
					cod = sc.nextInt();
				} catch (Exception e) {
					// TODO: handle exception
				}				
				
				
				System.out.println("Ótimo! Qual a quantidade desejada?");
					
				int qtd= 0;
				qtd = sc.nextInt();
								
				if (cod == 1) {
					Item item1 = new Item(p1, qtd);
						carrinho.inserirItem(item1);
					} else if (cod == 2) {
						Item item1 = new Item(p2, qtd);
						carrinho.inserirItem(item1);
						
					} else if (cod == 3) {
						Item item1 = new Item(p3, qtd);
						carrinho.inserirItem(item1);
					} else if (cod == 4) {
						Item item1 = new Item(p4, qtd);
						carrinho.inserirItem(item1);
					}
					
					System.out.println("Foram adicionados "+qtd +" unidades ao carrinho" );
					
					System.out.println("Continuar comprando 1-S ou 0-N");
					
					int continua = -1;
					
					continua = sc.nextInt();
					
					System.out.println("Informe o código do Produto");
					
					cod = sc.nextInt();
					
					System.out.println("Ótimo! Qual a quantidade desejada?");
					
					qtd = sc.nextInt();
								
					if (cod == 1) {
						Item item2 = new Item(p1, qtd);
						carrinho.inserirItem(item2);
					} else if (cod == 2) {
						Item item2 = new Item(p2, qtd);
						carrinho.inserirItem(item2);
						
					} else if (cod == 3) {
						Item item2 = new Item(p3, qtd);
						carrinho.inserirItem(item2);
					} else if (cod == 4) {
						Item item2 = new Item(p4, qtd);
						carrinho.inserirItem(item2);
					}
					
					carrinho.fecharConta();	
	                     break;
	            case 2:  
	            	System.out.println("Fim do Programa");
	                     break;
	            default: 
	            	System.out.println("Escolha 1 ou 2");
	                     break;
	        }
		

		sc.close();
	}
}