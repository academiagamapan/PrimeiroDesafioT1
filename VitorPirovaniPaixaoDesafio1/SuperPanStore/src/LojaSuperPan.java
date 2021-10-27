import java.util.ArrayList;
import java.util.Scanner;

public class LojaSuperPan {

	private ArrayList<Produto> estoque = new ArrayList<Produto>();
	private ArrayList<Produto> carrinho = new ArrayList<Produto>();
	
	public ArrayList<Produto> getEstoque() {
		return estoque;
	}
	public void setEstoque(Produto produto) {
		this.estoque.add(produto);
	}
	public ArrayList<Produto> getCarrinho() {
		return carrinho;
	}
	public void setCarrinho(Produto produto) {
		this.carrinho.add(produto);
	}
	
	public void boasVindas() {
		System.out.println("--------------------------------------------------------------------");
		System.out.println("-------------------- Bem-vindo a Super PAN store -------------------");
	}	

	public void mostraEstoque(){
		System.out.println("\t\t Confira abaixo a nossa lista de produtos:");
		System.out.println("\t CÓDIGO \t NOME \t\t VALOR \t\t UNIDADES");
		for(Produto produto : this.estoque) {
			System.out.printf("\t %d \t\t %s \t %.2f \t %d \n",produto.getCodigo(),produto.getNome() ,
					produto.getValorUnitario(),produto.getUnidadesDisponiveis());
		}
		System.out.println("-----------------------------------------------------------------------");
	}
	
	public void sucesso(Produto produto) {
		System.out.println("--------------------------------------------------------");
		System.out.println(produto.getUnidadesDisponiveis()+" unidades do produto " +produto.getNome()+" adicionada(s) com sucesso!");
		System.out.println("--------------------------------------------------------");
	}
	
	public void mostraCarrinho(){
		System.out.println("\t\t Confira abaixo o seu carrinho:");
		System.out.println("\t CÓDIGO \t NOME \t\t VALOR \t\t UNIDADES");
		for(Produto produto : this.carrinho) {
			System.out.printf("\t %d \t\t %s \t %.2f \t %d \n",produto.getCodigo(),produto.getNome() ,
					produto.getValorUnitario(),produto.getUnidadesDisponiveis());
		}
		double total = 0;
		for(Produto produto: this.carrinho) {
			total+=produto.getUnidadesDisponiveis()*produto.getValorUnitario();
		}
		System.out.printf("TOTAL = R$ %.2f ",total);
		System.out.println("--------------------------------------------------------\n");
	}
	
	public void fazerCompras() {
		Scanner entrada = new Scanner(System.in);
		
		System.out.println("Gostaria de iniciar as compras? S/N");
		String comprar = entrada.next();
		System.out.println(comprar);
		int totalUnidades=1;
		while((comprar.equals("s") || comprar.equals("S")) && totalUnidades > 0) {
			mostraEstoque();
			System.out.println("Digite o código do produto que deseja adicionar ao seu carrinho de compras:");
			int codigo= entrada.nextInt();
			for(Produto produto: this.estoque) {
				if(produto.getCodigo()==codigo && produto.getUnidadesDisponiveis()==0) {
					System.out.println("Desculpe. O produto "+produto.getNome()+" está esgotado.");
					System.out.println("Gostaria de comprar outro produto? S/N");
					comprar = entrada.next();
				}else if(produto.getCodigo()==codigo){
					System.out.println("Quantas unidades de "+produto.getNome()+" deseja comprar?");
					int unidades = entrada.nextInt();
					if(unidades <= 0) {
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
							this.carrinho.add(produtoCar);
							sucesso(produtoCar);
							System.out.println("Gostaria de comprar outro produto? S/N");
							comprar = entrada.next();
						}else {
							System.out.println("Gostaria de comprar outro produto? S/N");
							comprar = entrada.next();
						}
					}else {
						Produto produtoCar = new Produto(codigo,produto.getNome(),produto.getValorUnitario(),unidades);
						produto.setUnidadesDisponiveis(produto.getUnidadesDisponiveis()-unidades);
						this.carrinho.add(produtoCar);
						sucesso(produtoCar);
						System.out.println("Gostaria de comprar outro produto? S/N");
						comprar = entrada.next();
					}
				}
			}
			totalUnidades=0;
			for(Produto produto: estoque) {
				totalUnidades +=produto.getUnidadesDisponiveis();
			}
			if(totalUnidades == 0) {
				System.out.println("----------------------------------------------------------------------------\n");
				System.out.println("------------ Desculpe. Todo nosso estoque de produtos se esgotou!-----------\n");
				System.out.println("----------------------------------------------------------------------------\n\n");
			}
		}
//		entrada.close();
	}
	
	
	public void agradecimento() {
		System.out.println("--------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\t A Super PAN Store agradece. Volte sempre!");
		System.out.println("____________________________________________________________________________________________");
	}
	
}
