package br.com.pan.store.entidades;

public class PagamentoCartaoVista extends FormaDePagamento {

    public PagamentoCartaoVista(Integer id ) {
        super(id, "CREDITO", 1, 10.0);
    }

    @Override
    public Double calculaValor(Carrinho carrinho) {
        Double total = 0.0;

        for (CarrinhoItem item : carrinho.getItens()) {
            total += item.getQuantidade() * item.getPrecoVendido();
        }

        return total - ( total * (this.getDesconto() / 100) ) ;
    }

}
