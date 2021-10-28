
public class TesteSuperPanStore {
	
	public static void main(String[] args) {
		
		LojaSuperPan superPanStore = new LojaSuperPan();
		
		Produto iPhone13 = new Produto(1, "iPhone 13", 12003.5, 5);
		Produto camisa = new Produto(2, "Camisa", 150, 20);
		Produto sapato = new Produto(3, "Sapato", 123.5, 12);
		
		superPanStore.setEstoque(iPhone13);
		superPanStore.setEstoque(camisa);
		superPanStore.setEstoque(sapato);
		
		superPanStore.boasVindas();
		superPanStore.fazerCompras();
		
		if(superPanStore.getCarrinho().isEmpty()) {
			superPanStore.agradecimento();
		}else {
			superPanStore.mostraCarrinho();
			NotaFiscal notaFiscal = new NotaFiscal(superPanStore.getCarrinho());
			notaFiscal.setFormaPagamento();
			if(notaFiscal.getFormaPagamento()==5) {
				System.out.println("Seu carrinho foi esvaziado com sucesso!");
				superPanStore.agradecimento();
			}else {
				notaFiscal.setTotalGeral();
				notaFiscal.setDesconto();
				notaFiscal.setJuros();
				notaFiscal.imprimeNotaFiscal();
				superPanStore.agradecimento();
			}
			
		}
	}
}
