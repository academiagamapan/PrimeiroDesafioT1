package estoque;

public class Produto {

		private int id_produto;
		private String nome_produto;
		private double preco_produto;
		
		public Produto () {};

		public Produto(int id_produto, String nome_produto, double preco_produto) {
			this.id_produto = id_produto;
			this.nome_produto = nome_produto;
			this.preco_produto = preco_produto;
		}
		public int getId_produto() {
			return id_produto;
		}
		public void setId_produto(int id_produto) {
			this.id_produto = id_produto;
		}
		public double getPreco_produto() {
			return preco_produto;
		}
		public void setPreco_produto(double preco_produto) {
			this.preco_produto = preco_produto;
		}
		public String getNome_produto() {
			return nome_produto;
		}
		public void setNome_produto(String nome_produto) {
			this.nome_produto = nome_produto;
		}
		@Override
		public String toString() {
			return "\n Id: " + id_produto + " Nome: " + nome_produto;
		}	
		
		
}
