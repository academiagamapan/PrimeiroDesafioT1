package LojaVirtualFilipe;

public class Produto {
	private int codigo;
	private String nomeProduto;
	private int quantidade;
	private double precounit;
	public Produto(int codigo, String nomeProduto, int quantidade, double precounit) {
		super();
		this.codigo = codigo;
		if (nomeProduto.length() < 15) {
	     	for(int i = 1; i <= 15 - nomeProduto.length();i++) {
	      		nomeProduto += " ";
	       	}
           } else {
	        		nomeProduto = nomeProduto.substring(0,15);
	        }
		this.nomeProduto = nomeProduto;
		this.quantidade = quantidade;
		this.precounit = precounit;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getPrecounit() {
		return precounit;
	}
	public void setPrecounit(double precounit) {
		this.precounit = precounit;
	}
	@Override
	public String toString() {
		return (codigo + "\t\t" + nomeProduto + "\t\t" + quantidade + "\t\t" + "R$ " + precounit).replace('.', ',');
	}
	public String toStringNota() {
		return (nomeProduto + "\t" + quantidade + "\t\tR$ " + precounit).replace('.', ',');
	}
	
}