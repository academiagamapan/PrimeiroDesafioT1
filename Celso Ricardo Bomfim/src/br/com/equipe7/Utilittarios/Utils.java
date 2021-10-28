package br.com.equipe7.Utilittarios;

import br.com.equipe7.Modelos.Produto;

import java.util.TreeSet;

/**
 * Classe com métodos utilitários
 */
public class Utils {

    /**
     * Método responsável por popular o estoque.
     * @return TreeSet
     */
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

        Produto p6 = new Produto();
        p6.setCodigoDoProduto(6);
        p6.setNomeDoProduto("Azeite");
        p6.setPrecoDoProduto(4.55);
        p6.setQtdEmEstoque(10);
        p6.setUnidade("LT");
        produtos.add(p6);

        Produto p7 = new Produto();
        p7.setCodigoDoProduto(7);
        p7.setNomeDoProduto("Oleo");
        p7.setPrecoDoProduto(7.33);
        p7.setQtdEmEstoque(10);
        p7.setUnidade("PC");
        produtos.add(p7);

        Produto p8 = new Produto();
        p8.setCodigoDoProduto(8);
        p8.setNomeDoProduto("Sabão");
        p8.setPrecoDoProduto(1.99);
        p8.setQtdEmEstoque(10);
        p8.setUnidade("TB");
        produtos.add(p8);

        Produto p9 = new Produto();
        p9.setCodigoDoProduto(9);
        p9.setNomeDoProduto("Sal");
        p9.setPrecoDoProduto(3.82);
        p9.setQtdEmEstoque(10);
        p9.setUnidade("PC");
        produtos.add(p9);

        return produtos;
    }

    /**
     * Método responsável por limpar o console
     */
    public static void limparTela(){
        for (int i = 0; i < 13; i++){
            System.out.println(" ");
        }
    }

    /**
     * Retorna a quantidade de caracteres de uma string
     * @param produtos
     * @return int
     */
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