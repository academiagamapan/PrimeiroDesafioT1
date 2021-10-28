package modelo;

public class ItemCarrinho {

	private Integer codItem;
	private Double precoUnitario;
	private Long quantidade;
	private Double precoTotal;
	private Produto produto;

	public ItemCarrinho() {

	}		

	public ItemCarrinho(Integer codItem, Double precoUnitario, Long quantidade, Double precoTotal, Produto produto) {
		
		this.codItem = codItem;
		this.precoUnitario = precoUnitario;
		this.quantidade = quantidade;
		this.precoTotal = precoTotal;
		this.produto = produto;
	}

	public Double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(Double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(Double precoTotal) {
		this.precoTotal = precoTotal;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public void removeQuantidade(Long quantidade) {
		this.quantidade -= quantidade;
	}

	public void adicionarQuantidade(Long quantidade) {
		this.quantidade += quantidade;
	}

	public Integer getCodItem() {
		return codItem;
	}

	public void setCodItem(Integer codItem) {
		
		this.codItem = codItem;
	}
	
	public Double calculaPrecoTotal() {
		return this.precoTotal = this.precoUnitario * this.quantidade;
	}

	@Override
	public String toString() {
		return "ItemCarrinho [precoUnitario=" + precoUnitario + ", quantidade=" + quantidade + ", precoTotal="
				+ precoTotal + ", produto=" + produto + "]";
	}
	
}
