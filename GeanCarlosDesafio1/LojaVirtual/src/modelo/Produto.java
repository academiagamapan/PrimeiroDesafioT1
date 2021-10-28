package modelo;

public class Produto {

	private Long codigo;
	private String nome;
	private Long qtdEstoque;
	private Double preco;
		
	public Produto() {
		
	}

	public Produto(Long codigo, String nome, Long qtdEstoque, Double preco) {
		this.codigo = codigo;
		this.nome = nome;
		this.qtdEstoque = qtdEstoque;
		this.preco = preco;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public Long getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(Long qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public void removerEstoque(Long quantidade) {
		this.qtdEstoque -= quantidade;
	}
	
	public void adicionarEstoque(Long quantidade) {
		this.qtdEstoque += quantidade;
	}

	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", nome=" + nome + ", qtdEstoque=" + qtdEstoque + ", preco=" + preco + "]";
	}		
	
}
