
public class Produto {
	
	private int codigo;
	private String nome;
	private double valorUnitario;
	private int unidadesDisponiveis;
	
	public Produto(int codigo,String nome,double valorUnitario, int unidadesDisponiveis) {
		this.codigo = codigo;
		this.nome = nome;
		this.valorUnitario = valorUnitario;
		this.unidadesDisponiveis = unidadesDisponiveis;
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

	public double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public int getUnidadesDisponiveis() {
		return unidadesDisponiveis;
	}

	public void setUnidadesDisponiveis(int unidadesDisponiveis) {
		this.unidadesDisponiveis = unidadesDisponiveis;
	}
	
	

}
