package entidades;

public class Item {
	private Produto produto;
	private Integer quantidade;

	public Item() {
		
	}
	
	public Item(Produto produto, int qtd) {
		this.produto = produto;
		this.quantidade = qtd;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return produto + " " + quantidade;
	}

}