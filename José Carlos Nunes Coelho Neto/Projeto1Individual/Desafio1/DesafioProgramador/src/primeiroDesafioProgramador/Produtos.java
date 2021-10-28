package primeiroDesafioProgramador;

public class Produtos {

	private String nome;
	private Double preco;
	private int quantidade;	
	
	public Produtos() {
		super();
		// TODO Auto-generated constructor stub
	}	
	
	public Produtos(String nome, Double preco, int quantidade) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
}
