package br.com.pan.store.entidades;

public class PagamentoCartaoCredito extends FormaDePagamento {

    public PagamentoCartaoCredito(Integer id ) {
        super(id, "PARCELADO", 3, 0.0);
    }

    @Override
    public Double calculaValor(Carrinho carrinho) {
        Double total = 0.0;

        for (CarrinhoItem item : carrinho.getItens()) {
            total += item.getQuantidade() * item.getPrecoVendido();
        }

        return total;
    }

}
