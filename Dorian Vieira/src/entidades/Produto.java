package entidades;

public class Produto {
	
	int codigo;
	String nome;
	double pre�o;
	int estoque;
	
	public Produto() {
		
	}
	
	public Produto(int codigo, String nome, double pre�o, int estoque) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.pre�o = pre�o;
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
	public double getPre�o() {
		return pre�o;
	}
	public void setPre�o(double pre�o) {
		this.pre�o = pre�o;
	}
	public int getEstoque() {
		return estoque;
	}
	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	@Override
	public String toString() {
		return "["+codigo+"]" +"	    " + nome + "	       	  " + pre�o + "	       		" +  estoque;
	}
	
}
