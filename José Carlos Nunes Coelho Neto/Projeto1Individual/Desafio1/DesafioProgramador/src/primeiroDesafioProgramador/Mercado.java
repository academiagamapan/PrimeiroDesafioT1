package primeiroDesafioProgramador;

import java.util.ArrayList;

public class Mercado {

	public static void main(String[] args) {
		
		ArrayList<Produtos> produtosMenu = new ArrayList<Produtos>();
		produtosMenu.add(new Produtos("Pendrive 8gb",25.0,10));
		produtosMenu.add(new Produtos("Pendrive 16gb",35.0,5));
		produtosMenu.add(new Produtos("WebCam",100.0,4));
		produtosMenu.add(new Produtos("Teclado USB",40.0,10));
		produtosMenu.add(new Produtos("Mouse USB",25.0,10));
		produtosMenu.add(new Produtos("Fone de ouvido",20.0,10));
		produtosMenu.add(new Produtos("Pelicula Celular",15.0,50));
		produtosMenu.add(new Produtos("Cabo HDMI",10.0,8));
		produtosMenu.add(new Produtos("Cabo de Força",8.5,5));
		produtosMenu.add(new Produtos("Caixa de som",50.0,6));
		produtosMenu.add(new Produtos("Adaptador WIFI",40.0,7));
		
		MenuCompras menu = new MenuCompras();
		
		menu.setProdutoMenu(produtosMenu);
		menu.menu();

	}

}
