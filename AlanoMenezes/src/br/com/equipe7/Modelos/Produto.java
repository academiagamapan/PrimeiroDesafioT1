package br.com.equipe7.Modelos;


public class Produto implements Comparable<Produto>{

    private int codigoDoProduto;
    private int qtdEmEstoque;
    private int qtdDoPedido;
    private String nomeDoProduto;
    private double precoDoProduto;
    private double pre�oTotaldoProduto;
    private String unidade;

    public int getCodigoDoProduto() {
        return codigoDoProduto;
    }

    public void setCodigoDoProduto(int codigoDoProduto) {
        this.codigoDoProduto = codigoDoProduto;
    }

    public int getQtdEmEstoque() {
        return qtdEmEstoque;
    }

    public void setQtdEmEstoque(int qtdEmEstoque) {
        this.qtdEmEstoque = qtdEmEstoque;
    }

    public String getNomeDoProduto() {
        return nomeDoProduto;
    }

    public void setNomeDoProduto(String nomeDoProduto) {
        this.nomeDoProduto = nomeDoProduto;
    }

    public double getPrecoDoProduto() {
        return precoDoProduto;
    }

    public void setPrecoDoProduto(double precoDoProduto) {
        this.precoDoProduto = precoDoProduto;
    }

    public int getQtdDoPedido() {
        return qtdDoPedido;
    }

    public void setQtdDoPedido(int qtdDoPedido) {
        this.qtdDoPedido = qtdDoPedido;
    }

    public double getPre�oTotaldoProduto() {
        return pre�oTotaldoProduto;
    }

    public void setPre�oTotaldoProduto(double pre�oTotaldoProduto) {
        this.pre�oTotaldoProduto = pre�oTotaldoProduto;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }


    @Override
    public int compareTo(Produto o) {
        Integer a = this.codigoDoProduto;
        Integer b = o.codigoDoProduto;
        return a.compareTo(b);

    }
}