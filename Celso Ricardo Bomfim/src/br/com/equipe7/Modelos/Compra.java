package br.com.equipe7.Modelos;

import java.time.LocalDate;
import java.util.TreeSet;

/**
 * Classe modelo de comprar (Items do carrinho)
 */
public class Compra {

    private double valorTotalDaCompra;
    private TreeSet<Produto> produtos = new TreeSet<>();
    private LocalDate data = LocalDate.now();
    private boolean compraFinalizada = false;

    public double getValorTotalDaCompra() {
        return valorTotalDaCompra;
    }

    public void setValorTotalDaCompra(double valorTotalDaCompra) {
        this.valorTotalDaCompra = valorTotalDaCompra;
    }

    public TreeSet<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(TreeSet<Produto> produtos) {
        this.produtos = produtos;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public boolean isCompraFinalizada() {
        return compraFinalizada;
    }

    public void setCompraFinalizada(boolean compraFinalizada) {
        this.compraFinalizada = compraFinalizada;
    }
}
