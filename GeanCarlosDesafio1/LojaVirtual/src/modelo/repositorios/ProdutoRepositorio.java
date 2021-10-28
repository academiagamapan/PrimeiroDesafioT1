package modelo.repositorios;

import java.util.Arrays;
import java.util.List;

import modelo.Produto;

public class ProdutoRepositorio {

	private static List<Produto> produtos = Arrays.asList(
			new Produto(1l, "Macarrão", 99l, 3.79d),
			new Produto(2l, "Arroz", 50l, 18.79d),
			new Produto(3l, "Leite", 49l, 3.79d),			
			new Produto(4l, "Margarina", 60l, 5.79d)
			);
	
	public static List<Produto> getAll() {		
		return produtos;	
	}
}
