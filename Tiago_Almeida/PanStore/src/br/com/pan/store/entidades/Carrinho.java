package br.com.pan.store.entidades;

import java.util.ArrayList;

import br.com.pan.store.dados.Database;

public class Carrinho {
    @SuppressWarnings("unused")
	private Integer id;
    private String nomeCliente;
    @SuppressWarnings("unused")
	private FormaDePagamento formaPagamento;
    private Integer notaFiscal;
    private ArrayList<CarrinhoItem> itens = new ArrayList<CarrinhoItem>();

    public Carrinho(Integer id, String nomeCliente) {
        this.id = id;
        this.nomeCliente = nomeCliente;
    }
    
    public ArrayList<CarrinhoItem> getItens() {
		return itens;
	}

	public void adicionarItem(CarrinhoItem carrinhoItem){
        this.itens.add(carrinhoItem);
    }

    public boolean adicionarItem(Produto produto, Integer quantidade) {
        CarrinhoItem item = new CarrinhoItem(produto,quantidade,produto.getPreco());
        if(produto.reduzirQuantidade(quantidade)) {
            System.out.printf("\n%d %s ADICIONADO(S) AO CARRINHO\n", quantidade, produto.getNome());
            adicionarItem(item);
            return true;
        } else return false;
    }

    public void gerarCupomFiscal(FormaDePagamento formaDePagamento){
        this.formaPagamento = formaDePagamento;

        this.notaFiscal = Database.getVendasRealizadas().size()+1;

        Database.getVendasRealizadas().add(this);
        Double totalDaCompra = 0.0;
        Double totalComDesconto = formaDePagamento.calculaValor(this);
        System.out.println("\n=========================================================================");
        System.out.println("|                              CUPOM FISCAL                             |");
        System.out.println("=========================================================================");
        System.out.println("| PANSTORE LTDA - A sua store de Informática!                           |\n"
				+ "| Rua das calçadas, nº 003 - Futura Visão                               |\n"
				+ "| CNPJ: 011.111.456/0001-11                                             |"	);
        System.out.println("=========================================================================");
        System.out.format("| %-37s%-12s%-14s%-6s |\n", "PRODUTO", "QUANT", "PRECO", "TOTAL");
        for (CarrinhoItem item:itens) {
            System.out.format("| %-37s%-12d%-13.2f%-5.2f |\n", item.getProduto().getNome().toUpperCase(), item.getQuantidade(), item.getPrecoVendido(), item.getQuantidade() * item.getPrecoVendido());
            totalDaCompra += item.getQuantidade() * item.getPrecoVendido();
        }
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("| TOTAL R$ %.2f                        TOTAL COM DESCONTO R$ %.2f |\n", totalDaCompra, totalComDesconto);
        System.out.printf("| TRIBUTOS R$ %.2f                                                    |\n", totalComDesconto * 0.09);
        System.out.println("=========================================================================");
        System.out.printf("| CLIENTE: %s                                                        |\n", this.nomeCliente.toUpperCase());
        System.out.printf("| NÚMERO DO CUPOM: %d                 FORMA DE PAGAMENTO: %s |\n", this.notaFiscal, formaDePagamento.getNome());
        System.out.println("=========================================================================");
        System.out.println("| Obrigado pela sua preferência!                                     \n"
				+ "| Volte sempre!            ");
    }
    
	public void listarProdutosCarrinho() {
		System.out.println("\n=============================================================================");
        System.out.println("|                                 CARRINHO                                  |");
		System.out.println("=============================================================================");
		System.out.println("| CÓDIGO \t NOME \t\t QUANTIDADE \t PREÇO \t\t  MARCA     |");
		
		for(CarrinhoItem item : itens) {
			System.out.printf("|   %d \t     %s \t     %s \t       R$%.2f \t %s  |\n", 
					item.getProduto().getId(),
					item.getProduto().getNome(),
					item.getQuantidade(),
					item.getProduto().getPreco() * item.getQuantidade(),
					item.getProduto().getMarca());
		}
		
		System.out.println("=============================================================================");
	}
}
