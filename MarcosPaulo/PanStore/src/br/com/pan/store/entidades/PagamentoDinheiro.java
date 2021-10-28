package br.com.pan.store.entidades;

public class PagamentoDinheiro extends FormaDePagamento {

	public PagamentoDinheiro(Integer id ) {
		super(id, "DINHEIRO", 1, 20.0);
	}

	@Override
	public Double calculaValor(Carrinho carrinho) {
		Double total = 0.0;
		
		for (CarrinhoItem item : carrinho.getItens()) {	
			total += item.getQuantidade() * item.getPrecoVendido();		
		}
		
		total -= total * (this.getDesconto() / 100);
		
		return total;
	}	
}
