
public class Item {
	private Product produto;
	private int quantidade;
	
	public Item(Product p, int qtd) {
		this.produto = p;
		this.quantidade = qtd;
	}
	
	public Product getProduto() {
		return produto;
	}
	
	public void setProduto(Product produto) {
		this.produto = produto;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}