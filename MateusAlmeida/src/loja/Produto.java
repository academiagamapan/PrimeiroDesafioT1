package loja;

public class Produto {
	
	private static int idContador = 0; //contador para cria��o de produtos com ID's �nicos
	private String nome;
	private double preco;
	private int quantidade;
	private int idProduto;
	
	//Construtor para adi��o no estoque
	public Produto(String nome, double preco, int quantidade) {
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		this.idProduto = ++idContador;
		
	}
	//Construtor para adi��o no carrinho de compras (n�o incrementa no contador do ID do produto)
	public Produto(int idProduto, String nome, double preco, int quantidade) {
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;	
		this.idProduto = idProduto;
	}
	
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int id) {
		this.idProduto = id;
	}
		
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public void decrementarQuantidade(int quantidade) {
		this.quantidade -= quantidade;
	}
	
	public void incrementarQuantidade(int quantidade) {
		this.quantidade += quantidade;
	}
	
	@Override
	public String toString() {;
		return "idProduto [nome =" + nome + ", pre�o = " + preco + ", quantidade = " + quantidade + "]";
	}
}

