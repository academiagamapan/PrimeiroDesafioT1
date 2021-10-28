package entidades;

public class Produto {
	
	int codigo;
	String nome;
	double preço;
	int estoque;
	
	public Produto() {
		
	}
	
	public Produto(int codigo, String nome, double preço, int estoque) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.preço = preço;
		this.estoque = estoque;
	}

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
	public double getPreço() {
		return preço;
	}
	public void setPreço(double preço) {
		this.preço = preço;
	}
	public int getEstoque() {
		return estoque;
	}
	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	@Override
	public String toString() {
		return "["+codigo+"]" +"	    " + nome + "	       	  " + preço + "	       		" +  estoque;
	}
	
}
