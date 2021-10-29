package br.com.equipe7.Utilittarios;

import br.com.equipe7.Modelos.Produto;

import java.util.TreeSet;

public class Utils {

    public static TreeSet<Produto> getProdutos(){
        TreeSet<Produto> produtos = new TreeSet<>();

        Produto p1 = new Produto();
        p1.setCodigoDoProduto(1);
        p1.setNomeDoProduto("Leite");
        p1.setPrecoDoProduto(4.57);
        p1.setQtdEmEstoque(10);
        p1.setUnidade("CX");
        produtos.add(p1);

        Produto p2 = new Produto();
        p2.setCodigoDoProduto(2);
        p2.setNomeDoProduto("Cereal");
        p2.setPrecoDoProduto(3.02);
        p2.setQtdEmEstoque(10);
        p2.setUnidade("CX");
        produtos.add(p2);

        Produto p3 = new Produto();
        p3.setCodigoDoProduto(3);
        p3.setNomeDoProduto("Arroz");
        p3.setPrecoDoProduto(9.43);
        p3.setQtdEmEstoque(10);
        p3.setUnidade("PC");
        produtos.add(p3);

        Produto p4 = new Produto();
        p4.setCodigoDoProduto(4);
        p4.setNomeDoProduto("Atum");
        p4.setPrecoDoProduto(3.55);
        p4.setQtdEmEstoque(10);
        p4.setUnidade("LT");
        produtos.add(p4);

        Produto p5 = new Produto();
        p5.setCodigoDoProduto(5);
        p5.setNomeDoProduto("Feijão");
        p5.setPrecoDoProduto(6.55);
        p5.setQtdEmEstoque(10);
        p5.setUnidade("PC");
        produtos.add(p5);

        return produtos;
    }

    public static void limparTela(){
        for (int i = 0; i < 500; i++){
            System.out.println(" ");
        }
    }

    public static int tamanhoMaiorString(TreeSet<Produto> produtos ){
        boolean primeiroValor = true;
        int maxTamanhoItem = 0;

        for (Produto p : produtos) {
            maxTamanhoItem = (primeiroValor) ? p.getNomeDoProduto().length() : Math.max(maxTamanhoItem, p.getNomeDoProduto().length());
            primeiroValor = false;
        }
        return maxTamanhoItem;
    }

}