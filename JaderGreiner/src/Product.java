// classe produtos será uma classe de dados em nosso projeto

public class Product {
	
	// atributos da classe produto
	private int codigo;
	private String nome;
	private float preco;
	private int estoque;
	
	//metodo construtor
	public Product() {
		this.codigo = 0;
		this.nome = "";
		this.preco = 0;
		this.estoque = 0;
	}
	
	// geters e setters

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}
	
	public int getEstoque() {
		return estoque;
	}
	
	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}
	
}
