package primeiroDesafioProgramador;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class MenuCompras {

	private ArrayList<Produtos> produtoMenu = new ArrayList<Produtos>();
	
	private ArrayList<CarrinhoCompra> produtosCarrinho = new ArrayList<CarrinhoCompra>();
	
	CarrinhoCompra carrinho = new CarrinhoCompra();
	FormaPagamento pagamento = new FormaPagamento();
	
	
	public ArrayList<Produtos> getProdutoMenu() {
		return produtoMenu;
	}

	public void setProdutoMenu(ArrayList<Produtos> produtoMenu) {
		this.produtoMenu = produtoMenu;
	}
	
	public ArrayList<CarrinhoCompra> getProdutosCarrinho() {
		return produtosCarrinho;
	}

	public void setProdutosCarrinho(ArrayList<CarrinhoCompra> produtosCarrinho) {
		this.produtosCarrinho = produtosCarrinho;
	}
	
	public static String design(String nome) {
		return nome = nome + " ".repeat(27 - nome.length());
	}
	
	public String designMenu() {
		String textoMenu = "==============MENU DE OPÇÕES===============\n"
						 + "1 - Adicionar Produto\n"
						 + "2 - Remover Produto\n"
						 + "3 - Ver Carrinho de Compras\n"
						 + "4 - Resetar Carrinho de Compras\n"
						 + "5 - Finalizar Compras\n"
						 + "6 - Sair do programa";
		return textoMenu;		
	}
	
	public Produtos testaQuantidadeProduto(int indice) {
		Produtos produto = new Produtos();
		int quantidade = produtoMenu.get(indice).getQuantidade();
		if (quantidade > 0) {
			quantidade = quantidade - 1;
			produtoMenu.get(indice).setQuantidade(quantidade);
			produto = produtoMenu.get(indice);			
		} else {
			JOptionPane.showMessageDialog(null, "Quantidade insuficiente do produto!\n"
												+"   ---Operação negada!---");
		}
		return produto;
	}
	
	public int exibeProdutos() {
		String listaProdutos = "==============Digite o número produto a ser selecionado==============\n"
							 + "N°     Nome                     Estoque                  Valor\n";
		String design = "                                        ";		
		int escolhaProduto;
		int i = 0;
		for (Produtos produtos : produtoMenu) {			
			int tamanhoDesignNome = produtos.getNome().length();
			int tamanhoDesignPreco = Integer.toString(produtos.getQuantidade()).length();
			listaProdutos = listaProdutos +(i+1)+"° - "+design(produtos.getNome())
											+design(Integer.toString(produtos.getQuantidade()))
											+design(Double.toString(produtos.getPreco()))+"\n";
			i = i + 1;
		}
		escolhaProduto = Integer.parseInt(JOptionPane.showInputDialog(null, listaProdutos));	
		escolhaProduto = escolhaProduto - 1;
		return escolhaProduto;				
	}
	
	public void exibeCarrinho() {
		JOptionPane.showMessageDialog(null, carrinho.exibeCarrinho(produtosCarrinho));
	}
	
	public void removeCarrinho() {
			
			int opcao = 0;
			int indice = 0;
			if (produtosCarrinho.isEmpty()) {
				JOptionPane.showMessageDialog(null, carrinho.exibeCarrinho(produtosCarrinho));
			} else {
				indice = Integer.parseInt(JOptionPane.showInputDialog(null, carrinho.exibeCarrinho(produtosCarrinho)));
				indice = indice - 1;
				int quantidade = produtosCarrinho.get(indice).getQuantidadeCarrinho();
				if (quantidade == 1) {
					opcao = 2;
				} else if (quantidade > 1) {
					opcao = Integer.parseInt(JOptionPane.showInputDialog(null, "Qual opção deseja?\n"
																				+ "1 - Reitrar uma unidade do Produto\n"
																				+ "2 - Exluir produto do carrinho de compras"));
				} else {	
					JOptionPane.showMessageDialog(null, "----- Opção Inváldia ------");
				}
			}
			carrinho.removeProduto(produtosCarrinho, indice, opcao);
		}
	
	public boolean pagamento() {
		Double valorTotal = null;
		String texto;
		boolean validaMenu;
		int opcao = 0;
		if (produtosCarrinho.isEmpty()) {
			texto = "===O carrinho de compras está vazio!===\n"
							+ "      Por favor, continue nas compras!";
			validaMenu = true;
		} else {
			texto = carrinho.exibeCarrinho(produtosCarrinho);
			valorTotal = carrinho.valorTotalCarrinho(produtosCarrinho);
			texto = texto +"\n"+ "----- Valor Total do Carrinho -----\n"
						 +"               R$"+valorTotal+" ";
			texto = texto +"\n"+ pagamento.tipoPagamento();
			opcao = Integer.parseInt(JOptionPane.showInputDialog(null,texto));
			texto = pagamento.formaPagamento(opcao, valorTotal);
			JOptionPane.showMessageDialog(null, texto);
			validaMenu = false;
		}
		
		texto = carrinho.exibeCarrinho(produtosCarrinho);
		texto = texto + "\n" + pagamento.formaPagamento(opcao, valorTotal);
		JOptionPane.showMessageDialog(null, texto);
		return validaMenu;
	}
		
	public void menu() {
		ArrayList<Produtos> produtoMenu2 = new ArrayList<Produtos>();
		produtoMenu2 = produtoMenu;
		Produtos produtoAux = new Produtos();
		int opcaoMenu;
		int opcaoAux;
		boolean validaMenu = true;
		while(validaMenu == true) {
			
			opcaoMenu = Integer.parseInt(JOptionPane.showInputDialog(null, designMenu()));			
			switch (opcaoMenu) {
			case 1:
				opcaoAux = exibeProdutos();
				produtoAux = testaQuantidadeProduto(opcaoAux);
				produtosCarrinho = carrinho.adicionaProduto(produtoAux, produtosCarrinho);				
				break;
			case 2:
				removeCarrinho();
				break;
			case 3:
				exibeCarrinho();
				break;
			case 4:
				produtoMenu = produtoMenu2;
				produtosCarrinho.clear();				
				break;
			case 5:
				validaMenu = pagamento();				
				break;
			case 6:
				JOptionPane.showMessageDialog(null, "----- SAINDO DO PROGRAMA ------");
				validaMenu = false;
				break;
			default:
				JOptionPane.showMessageDialog(null, "----- Operação Inválida ------\n"
						+ "        Tente Novamente!");
				break;
			}	
		}		
	}	
}
