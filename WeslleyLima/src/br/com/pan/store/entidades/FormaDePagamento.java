package br.com.pan.store.entidades;

public abstract class FormaDePagamento {

    private Integer id;
    private String nome;
    private Integer nParcelas;
    private Double desconto;

    public FormaDePagamento(Integer id, String nome, Integer nParcelas, Double desconto) {
		this.id = id;
		this.nome = nome + " ".repeat(14 - nome.length());
		this.nParcelas = nParcelas;
		this.desconto = desconto;
	}

	public Double calculaValor(Carrinho carrinho) {
        return null;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getnParcelas() {
        return nParcelas;
    }

    public Double getDesconto() {
        return desconto;
    }

}
