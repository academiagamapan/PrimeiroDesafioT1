package br.com.pan.store.entidades;

public class CarrinhoItem {
    private Produto produto;
	private Integer quantidade;
	private Double precoVendido;

    public CarrinhoItem(Produto produto, Integer quantidade, Double precoVendido) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoVendido = precoVendido;
    }

	public Produto getProduto() {
		return produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Double getPrecoVendido() {
		return precoVendido;
	}
}
