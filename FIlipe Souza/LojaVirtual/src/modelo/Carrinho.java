package modelo;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

	private Double precoTotal;
	private List<ItemCarrinho> itens = new ArrayList<>();
	
	public Carrinho() {	
		
	}

	public List<ItemCarrinho> getItens() {
		return itens;
	}

	public void setItens(List<ItemCarrinho> itens) {
		this.itens = itens;
	}

	public Double getPrecoTotal() {
		return precoTotal;
	}			

	public void setPrecoTotal(Double precoTotal) {
		this.precoTotal = precoTotal;
	}

	public void addItens(ItemCarrinho itens) {
		this.itens.add(itens);
	}
	
	public Double calculaPrecoTotal() {
		for (ItemCarrinho itemCarrinho : this.itens) {
			this.precoTotal += itemCarrinho.getPrecoTotal();
		}		
		return this.precoTotal;
	}
	

	@Override
	public String toString() {
		return "Carrinho [precoTotal=" + precoTotal + ", itens=" + itens + "]";
	}		
	
}
