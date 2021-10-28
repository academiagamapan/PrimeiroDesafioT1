package LojaVirtual;

public class Pagamento {
	
	private double novoValor;

	public void setPixDinheiro(double total) {
		this.novoValor = total * 0.8;
	}

	public void setCredito(double total, char r) {
		
		
		if( r == 's' || r == 'S' ) {
			this.novoValor = total * 0.85;
		}else {
			this.novoValor = total * 0.9;
		}
		
	}
	
	public void setParcelado(double total, int parcelas) {
		if (parcelas < 3) {
			this.novoValor = total;
		} else {
			this.novoValor = 1.1 * parcelas * total;
		}
		
	}

	public double getNovoValor() {
		return novoValor;
	}

}
